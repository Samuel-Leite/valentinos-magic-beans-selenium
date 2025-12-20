# 📸 Integração com Percy

Este projeto oferece suporte à captura de snapshots visuais com **Percy** durante a execução de testes.  
A integração permite detectar alterações inesperadas na interface da aplicação e revisar visualmente o impacto de cada mudança diretamente no **dashboard do Percy**.

---

## 🎯 Propósito

- Capturar snapshots visuais durante o fluxo de testes automatizados
- Detectar regressões visuais entre execuções
- Integrar com o dashboard do Percy para revisão e aprovação de mudanças
- Permitir controle condicional da captura via variável de configuração

---

## ⚙️ Como Funciona

- O teste automatizado abre a página desejada com Selenium WebDriver
- A classe Percy inicializa o cliente oficial io.percy.selenium.Percy com o WebDriver atual
- Durante o fluxo de testes, o método percySnapshot(name) é chamado para capturar a tela
- O snapshot é enviado para o Percy Dashboard, onde pode ser comparado com execuções anteriores
- Alterações visuais são destacadas para revisão e aprovação

---

## ⚙️ Como executar o Percy

- Inserir o token no terminal:
```Powershel
$env:PERCY_TOKEN="<seu-token>"
```

- Comando para executar o percy:

```bash
npx percy exec -- mvn test
```

---

#### 🎯 Propósito
- Capturar imagens da interface durante o teste
- Registrar logs de execução para rastreabilidade

---

## 🔗 Links Úteis
- [Percy Dashboard](https://percy.io/)
- [Apresentação das funcionalidades](https://www.youtube.com/watch?v=XOlZ6y24wDQ)
- [Como funciona a captura dos snapshot](https://www.browserstack.com/docs/percy/integrate/percy-sdk-workflow)
- [Como funciona a baseline](https://www.browserstack.com/docs/percy/baseline-management/overview)
- [Painel de resultado](https://www.browserstack.com/docs/percy/build-results/overview)
- [Como lidar com dados dinâmicos](https://www.browserstack.com/docs/percy/stabilize-screenshots/overview)
- [Ignorar áreas e impedir que sejam renderizadas](https://www.browserstack.com/docs/percy/advanced-snapshots/percy-css)
- [Direcionar áreas específicas da UI para testes visuais focados](https://www.browserstack.com/docs/percy/set-regions/overview)