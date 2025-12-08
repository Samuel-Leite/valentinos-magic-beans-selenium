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

  <!-- Qualidade e Padronização -->
  <img src="https://img.shields.io/badge/Checkstyle-code%20quality-blueviolet?style=for-the-badge&logo=checkstyle&logoColor=white" alt="Checkstyle" />
  <img src="https://img.shields.io/badge/SpotBugs-static%20analysis-critical?style=for-the-badge&logo=java&logoColor=white" alt="SpotBugs" />
  <img src="https://img.shields.io/badge/Conventional%20Commits-commitlint-yellow?style=for-the-badge&logo=git&logoColor=white" alt="Commitlint" />

  <!-- Integrações e Observabilidade -->
  <img src="https://img.shields.io/badge/BrowserStack-integrated-orange?style=for-the-badge&logo=browserstack&logoColor=white" alt="BrowserStack" />
  <img src="https://img.shields.io/badge/Azure%20DevOps-Test%20Plans-0078D7?style=for-the-badge&logo=azuredevops&logoColor=white" alt="Azure DevOps Test Plans" />
  <img src="https://img.shields.io/badge/Percy-Visual%20Testing-8c4eff?style=for-the-badge&logo=percy&logoColor=white" alt="Percy" />
  <img src="https://img.shields.io/badge/Lighthouse-Performance%20Audit-00bcd4?style=for-the-badge&logo=lighthouse&logoColor=white" alt="Lighthouse" />
  <img src="https://img.shields.io/badge/Prometheus-Metrics-e6522c?style=for-the-badge&logo=prometheus&logoColor=white" alt="Prometheus" />
  <img src="https://img.shields.io/badge/Grafana-Dashboard-f46800?style=for-the-badge&logo=grafana&logoColor=white" alt="Grafana" />

  <!-- CI/CD e Relatórios -->
  <img src="https://img.shields.io/badge/CI-GitHub%20Actions-blue?style=for-the-badge&logo=githubactions&logoColor=white" alt="GitHub Actions" />
  <a href="https://samuel-leite.github.io/valentinos-magic-beans-selenium/">
    <img src="https://img.shields.io/badge/Allure-Report-blue?style=for-the-badge&logo=allure&logoColor=white" alt="Allure Report" />
  </a>

  <!-- Cobertura e Licença -->
  <img src="https://img.shields.io/badge/Coverage-100%25-success?style=for-the-badge" alt="Coverage" />
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="MIT License" />
	@FindBy(css="")
	private WebElement webElement;
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
Este repositório contém uma suíte robusta de automação de testes de ponta a ponta desenvolvida com o framework <a href="https://playwright.dev/">Playwright</a>. Seu propósito é validar funcionalidades críticas de aplicações web modernas por meio de testes confiáveis, organizados e escaláveis, integrados a pipelines de CI/CD e Grafana com Prometheus, e Allure report.

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

Para detalhes completos como executar auditorias de performance em páginas web usando o Lighthouse integrado ao Playwright, consulte o [Guia de Auditoria Lighthouse](../portuguese/lighthouse-pt.md).

---

## 📸 Integração e execução com o Percy

Este projeto utiliza o [Percy](https://percy.io/) para testes visuais automatizados, permitindo identificar mudanças inesperadas na interface da aplicação durante a execução dos testes com Playwright. Para mais detalhes sobre a configuração e uso do Percy, consulte o [Guia de Execução Percy](../portuguese/percy-pt.md).

---

## 📂 Estrutura do Projeto

```bash
valentino-magic-beans/

```

## 🔗 Links Úteis
- [Percy Dashboard](https://percy.io/)
- [Allure Report para Playwright](https://github.com/allure-framework/allure-playwright)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Hub Docker](https://hub.docker.com/)