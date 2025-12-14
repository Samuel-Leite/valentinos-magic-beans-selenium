# 📦 Integração Docker + Prometheus + Grafana com execução através da Pipeline do Jenkins

Este projeto integra a pipeline do Jenkins com o **monitoramento com Prometheus** à **automação de testes com Selenium** usando Docker.  
Ele expõe métricas personalizadas das execuções de testes e as visualiza em painéis do Grafana.

---

## 🎯 Propósito

- Executar os testes através da pipeline, Jenkins, em um ambiente containerizado
- Coletar métricas com Prometheus
- Visualizar resultados em painéis do Grafana
- Habilitar observabilidade sobre desempenho e confiabilidade dos testes

---

## ⚙️ Como Funciona

1. Constrói o container com as configurações necessárias do Jenkins, Prometheus e Grafana,
   1.1. Precisa configurar o Docker Desktop (https://www.docker.com/products/docker-desktop/),
2. Executar os testes da pipeline no Jenkins através da URL 'http://localhost:8080/',
3. Prometheus coleta as métricas através do plugin 'Prometheus metrics' durante a execução,
   3.2. Para confirmar se o Prometheus está funcionando através da URL 'http://localhost:9090/targets'
   3.2. Para confirmar se as métricas estão sendo coletadas através da URL 'http://localhost:8080/prometheus/'
4. O Grafana se conecta ao Prometheus e exibe os painéis.
5. As métricas incluem duração dos testes, status, tentativas, falhas, ambiente e grupo.

---

## 🛠️ Componentes Principais

### `Dockerfile`

Constrói o container que executa os testes e expõe métricas.

```dockerfile
# Jenkins LTS já vem com Java 11
FROM jenkins/jenkins:lts

USER root

# Instala dependências básicas + Maven + Firefox + libs necessárias para Chrome
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    unzip \
    git \
    xvfb \
    gnupg \
    maven \
    firefox-esr \
    libnss3 \
    libxi6 \
    libxrandr2 \
    libasound2 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libgtk-3-0 \
    && rm -rf /var/lib/apt/lists/*

# Adiciona repositório oficial do Google Chrome e instala
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" \
       > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable

# Instala ChromeDriver (última versão estável disponível)
RUN DRIVER_VERSION=$(curl -s https://chromedriver.storage.googleapis.com/LATEST_RELEASE) \
    && wget -q https://chromedriver.storage.googleapis.com/${DRIVER_VERSION}/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip -d /usr/local/bin \
    && rm chromedriver_linux64.zip \
    && chmod +x /usr/local/bin/chromedriver

# Instala GeckoDriver (fixando versão estável para evitar falhas)
ENV GECKO_VERSION v0.34.0
RUN wget -q https://github.com/mozilla/geckodriver/releases/download/${GECKO_VERSION}/geckodriver-${GECKO_VERSION}-linux64.tar.gz \
    && tar -xzf geckodriver-${GECKO_VERSION}-linux64.tar.gz -C /usr/local/bin \
    && rm geckodriver-${GECKO_VERSION}-linux64.tar.gz \
    && chmod +x /usr/local/bin/geckodriver

USER jenkins
```

### `docker-compose.yml`

Define os serviços Pipeline no Jenkins, Prometheus e Grafana.

```yaml
version: '3.8'

services:
  jenkins:
    build: .
    container_name: jenkins-selenium
    restart: unless-stopped
    ports:
      - "8080:8080"   # UI Jenkins
      - "50000:50000" # Conexão com agentes
    volumes:
      - jenkins_home:/var/jenkins_home
      - /var/run/docker.sock:/var/run/docker.sock
    environment:
      - JAVA_OPTS=-Djenkins.install.runSetupWizard=false
    networks:
      - jenkins_net

  prometheus:
    image: prom/prometheus
    container_name: prometheus
    restart: unless-stopped
    ports:
      - "9090:9090"
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml
    networks:
      - jenkins_net

  grafana:
    image: grafana/grafana
    container_name: grafana
    restart: unless-stopped
    ports:
      - "3000:3000"
    networks:
      - jenkins_net

volumes:
  jenkins_home:

networks:
  jenkins_net:
    driver: bridge
```

---

### `prometheus.yml`

Define onde coletar métricas.

```yaml
scrape_configs:
  - job_name: 'jenkins'
    metrics_path: '/prometheus'
    static_configs:
      - targets: ['jenkins-selenium:8080']
```

---

## 🧯 Comandos Docker

### 🔨 Construir imagem

```bash
docker-compose build
```

### ▶️ Iniciar containers
```bash
docker-compose up -d
```

### 🛑 Encerrar os containers
```bash
docker-compose down
```

---

## Plugins necessários para configurar no Jenkins

Seguem os plugins necessários para configurar no Jenkins:
1. Docker Pipeline, 
2. Docker, 
3. Pipeline: Stage View, 
4. Blue Ocean, 
5. Prometheus metrics
6. Dark Theme
7. Allure

---

## Configurações necessárias no Jenkins

### Cadastrar as credenciais do Github para acessar o repositório através do Jenkins

Deve acessar o seguinte path: Gerenciar Jenkins > Credencials > System > Global credentials (unrestricted) > Add Credentials > preencher as informações > Create
- Kind: Username with password
- Scope: Global (Jenkins, nodes, items, all child items, etc)
- Username: nome do usuário no repositório
- Password: senha do Github

### Configurar o Allure Report

Deve acessar o seguinte path: Gerenciar Jenkins > Tools > Allure Commandline instalações > Allure Commandline > preencher as informações > Save
- Nome: informar o nome para identificar
- Versão (From Maven Central): selecionar a versão mais recente

### Configurar a pipeline

Deve acessar o seguinte path: Home > Nova tarefa > preencher nome > Pipeline > Tudo certo > Pipeline > Pipeline script from SCM > SCM: Git > informar a URL do repositório > selecionar credencial > Branch Specifier (main / developer) > Script Path (Jenkinsfile)

Para configurar o publishHTML > 'Pipeline Syntax' > preencher as informações > Generate Pipeline Script > incluir no Jenkinsfile
- Sample Step: publishHTML: Publish HTML reports
- HTML directory to archive: report.html
- Report title: <titulo_relatorio>

Após efetuar as etapas supracitadas poderá executar a pipeline.

---

## Configurações necessárias no Grafana

### Configuração do Grafana

- **Configuração:** deve acessar o seguinte path: Home > Connections > Data sources > Add data source > Prometheus > Prometheus server URL (http://prometheus:9090) > Save & test
- **Dashboards:** deve acessar o seguinte path: Home > Dashboards > New > Import > [carregar os dados json](docs/grafana-dashboard.json) > Load > Import

---

## 📄 Arquivos Fonte

- [`docker-compose.yml`](docker-compose.yml)
- [`Dockerfile`](Dockerfile)
- [Guia das métricas do Grafana](docs/dashboard-metricas.md)