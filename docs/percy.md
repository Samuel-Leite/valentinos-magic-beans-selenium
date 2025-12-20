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