<div align="center">
  <h1>Valentino's Magic Beans</h1>
  <p><strong>Framework de automação de testes E2E com arquitetura escalável e integração contínua</strong><br>Projetado para validar funcionalidades críticas de aplicações web modernas, com foco em rastreabilidade, qualidade de código e integração com plataformas corporativas.</p><br>
</div>

<div align="center">
  <!-- Linguagem e Framework -->
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium" />
  <img src="https://img.shields.io/badge/TestNG-FF8C00?style=for-the-badge&logo=testng&logoColor=white" alt="TestNG" />
  <img src="https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white" alt="Cucumber" />
  <img src="https://img.shields.io/badge/Gherkin-5A9BD5?style=for-the-badge&logoColor=white" alt="Gherkin" />

  <!-- Integrações e Observabilidade -->
  <img src="https://img.shields.io/badge/Percy-Visual%20Testing-8c4eff?style=for-the-badge&logo=percy&logoColor=white" alt="Percy" />
  <img src="https://img.shields.io/badge/Lighthouse-Performance%20Audit-00bcd4?style=for-the-badge&logo=lighthouse&logoColor=white" alt="Lighthouse" />

  <!-- Cobertura e Licença -->
  <img src="https://img.shields.io/badge/Coverage-100%25-success?style=for-the-badge" alt="Coverage" />
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="MIT License" />
</div>

## 📦 Requisitos
- Selenium
- Testng
- Java
- Gherkin e Cucumber
- Percy (teste visual)

## 🚀 Propósito
Este projeto tem como objetivo validar funcionalidades críticas da aplicação web [**Valentino's Magic Beans**](https://valentinos-magic-beans.click) através de testes automatizados robustos, rastreáveis e escaláveis, com foco em boas práticas de desenvolvimento e qualidade de código.

## 📄 Licença
Este projeto está licenciado sob a **Licença MIT**.

---

## 🚀 Sobre o Projeto
Este repositório contém uma suíte robusta de automação de testes de ponta a ponta desenvolvida com o framework Selenium. Seu propósito é validar funcionalidades críticas de aplicações web modernas por meio de testes confiáveis, organizados e escaláveis, integrados a pipelines de CI/CD e Grafana com Prometheus, e Allure report.

## 📚 Principais Funcionalidades
- End-to-end testing com Selenium, Testng e Java
- Arquitetura de testes modular e escalável
- Geração de screenshots em todos os testes
- Auditoria de performance com Lighthouse
- Testes visuais com Percy integrados ao fluxo funcional
- Configuração estruturada com YAML para ambientes e credenciais

## 🛠️ Como Executar
```bash
# Instalar dependências

```
---

## 🚦 Auditoria de Performance com Lighthouse

Para detalhes completos como executar auditorias de performance em páginas web usando o Lighthouse integrado ao Selenium, consulte o [Guia de Auditoria Lighthouse](docs/lighthouse.md).

---

## 📸 Integração e execução com o Percy

Este projeto utiliza o [Percy](https://percy.io/) para testes visuais automatizados, permitindo identificar mudanças inesperadas na interface da aplicação durante a execução dos testes com Selenium. Para mais detalhes sobre a configuração e uso do Percy, consulte o [Guia de Execução Percy](docs/percy.md).

---

---

## 📦 Integração Grafana + Prometheus

Este projeto utiliza o [Docker](https://www.docker.com/) para orquestrar uma stack de monitoramento que inclui o [Prometheus](https://prometheus.io/) para coleta de métricas dos testes e o [Grafana](https://grafana.com/) para visualização em tempo real.  
A coleta das métricas ocorrem durante as execuções da Pipeline através do Jenkins que faz a captura das métricas da duração, número de tentativas, falhas e etc - são coletadas pelo Prometheus e exibidas em painéis do Grafana.  
Para instruções detalhadas de configuração e uso, consulte o [Guia do Jenkins e Grafana](docs/jenkins-grafana.md) e a [Guia das Métricas do Grafana](docs/dashboard-metricas.md)

---

## 📂 Estrutura do Projeto

```bash
valentino-magic-beans/
├───docs
├───src
│   ├───main
│   │   ├───java
│   │   │   └───core
│   │   │       ├───base
│   │   │       │   └───BasePage.java
│   │   │       ├───data
│   │   │       │   └───DataYaml.java
│   │   │       ├───driver
│   │   │       │   └───DriverFactory.java
│   │   │       ├───lighthouse
│   │   │       │   └───Lighthouse.java
│   │   │       └───percy
│   │   │           └───Percy.java
│   │   └───resources
│   │       └───conf
│   │           ├───url-prod.yml
│   │           └───url-qa.yml
│   └───test
│       ├───java
│       │   ├───attributes
│       │   │   ├───HomeAttributes.java
│       │   │   └───LoginAttributes.java
│       │   ├───hooks
│       │   │   └───Hooks.java
│       │   ├───pages
│       │   │   ├───HomePage.java
│       │   │   └───LoginPage.java
│       │   ├───runner
│       │   │   └───Runner.java
│       │   ├───steps
│       │   │   ├───HomeSteps.java
│       │   │   └───LoginSteps.java
│       │   └───utils
│       │       ├───Actions.java
│       │       ├───Asserts.java
│       │       └───Screenshot.java
│       └───resources
│           ├───data
│           │   ├───prod
│           │   │   └───credencial.yml
│           │   └───qa
│           │       └───credencial.yml
│           ├───features
│           ├───log4j2.properties
│           └───testng.xml
├───target
├───.gitignore
├───pom.xml
├───README.md
```

## 🔗 Links Úteis
- [Percy Dashboard](https://percy.io/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Hub Docker](https://hub.docker.com/)