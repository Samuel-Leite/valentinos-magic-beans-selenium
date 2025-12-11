# 📦 Integração Docker + Prometheus + Grafana com execução através da Pipeline do Jenkins

Este projeto integra o **monitoramento com Prometheus** à **automação de testes com Selenium** usando Docker.  
Ele expõe métricas personalizadas das execuções de testes e as visualiza em painéis do Grafana.

---

## 🎯 Propósito

- Executar testes através da pipeline, Jenkins, em um ambiente containerizado
- Coletar métricas com Prometheus
- Visualizar resultados em painéis do Grafana
- Habilitar observabilidade sobre desempenho e confiabilidade dos testes

---

## ⚙️ Como Funciona

1. O serviço `tests` executa os testes Playwright e expõe métricas via `prom-client`.
2. O Prometheus coleta métricas de `http://tests:9464/metrics` a cada 5 segundos.
3. O Grafana se conecta ao Prometheus e exibe os painéis.
4. As métricas incluem duração dos testes, status, tentativas, falhas, ambiente e grupo.

---

## 🛠️ Componentes Principais

### `Dockerfile`

Constrói o container que executa os testes Playwright e expõe métricas.

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

## 🧯 Comandos Docker

### 🔨 Construir imagem

```bash
docker-compose build
```

### ▶️ Iniciar containers
```bash
docker-compose up -d
```

### 🛑 Derrubar os containers
```bash
docker-compose down
```

---

## 📄 Arquivos Fonte

- [`docker-compose.yml`](../../infra/monitoring/docker-compose.yml)
- [`Dockerfile`](../../infra/monitoring/Dockerfile)
- [`metricsInstance.ts`](../../infra/monitoring/metricsInstance.ts)
- [`metricsServer.ts`](../../infra/monitoring/metricsServer.ts)
- [`prometheus.yml`](../../infra/monitoring/prometheus.yml)
- [`startMetrics.ts`](../../infra/monitoring/startMetrics.ts)
- [`grafana-playwright.json`](../../infra/dashboards/grafana-playwright.json)
- [Guia das métricas do Grafana](../portuguese/dashboard-metrics-pt.md)
- [Métricas no Grafana](../../docs/img/grafana.png)