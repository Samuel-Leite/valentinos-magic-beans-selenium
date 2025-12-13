# 🚦 Integração com Lighthouse via BrowserStack

Este projeto oferece suporte à execução de auditorias de performance com **Lighthouse** durante os testes automatizados.  
A integração permite validar métricas de desempenho e boas práticas da aplicação em tempo real, diretamente a partir da URL aberta pelo Selenium WebDriver.

---

## 🎯 Propósito

- Executar auditorias de performance com Lighthouse durante os testes automatizados
- Validar métricas como FCP, LCP, TBT e CLS diretamente no fluxo de testes
- Gerar relatórios HTML para análise posterior
- Garantir que cada execução de teste também avalie a experiência do usuário em termos de velocidade, estabilidade e acessibilidade

---

## ⚙️ Como Funciona

- O teste automatizado abre a página desejada com Selenium WebDriver
- A classe Lighthouse captura a URL atual do navegador
- Se a propriedade -Dlighthouse=true estiver habilitada, o código dispara o Lighthouse CLI como processo externo. 
- O relatório é gerado em formato HTML dentro da pasta target/lighthouse-reports.

---

## 📊 Indicadores do Lighthouse

Os indicadores abaixo explicam como o Lighthouse avalia diferentes aspectos de qualidade de uma aplicação web:

- **Performance** ⚡  
  Mede a velocidade e eficiência de carregamento da página.  
  Inclui métricas como *First Contentful Paint (FCP)*, *Largest Contentful Paint (LCP)*, *Total Blocking Time (TBT)* e *Cumulative Layout Shift (CLS)*.  
  Em resumo: indica o quão rápido e estável o site é para o usuário.

- **Accessibility** ♿  
  Avalia se o site é utilizável por pessoas com deficiência.  
  Checa contraste de cores, nomes de botões/links, estrutura de títulos e navegação por teclado.  
  Em resumo: mostra se o site é inclusivo e acessível para todos.

- **Best Practices** ✅  
  Verifica se o site segue boas práticas de desenvolvimento e segurança.  
  Exemplos: uso correto de HTTPS, proteção contra ataques XSS, evitar recursos obsoletos.  
  Em resumo: garante que o site está construído de forma moderna e segura.

- **SEO (Search Engine Optimization)** 🔍  
  Mede se o site está otimizado para aparecer em buscadores como Google.  
  Checa indexação, meta tags e recomendações básicas de SEO.  
  Em resumo: indica se o site tem boas chances de ser encontrado em pesquisas.

- **PWA (Progressive Web App)** 📱  
  Avalia se o site pode funcionar como um aplicativo instalável.  
  Checa manifest.json, service worker, splash screen e configuração de tema.  
  Em resumo: mostra se o site pode ser usado como um app no celular ou desktop.

---

## 🛠️ Componentes Principais

- Execução via Selenium + Java: dispara auditorias após navegação automatizada
- Controle por System Property: só roda se -Dlighthouse=true for definido
- Detecção de SO: usa lighthouse em Linux/Mac e lighthouse.cmd no Windows
- Relatórios HTML: salvos em target/lighthouse-reports/<nome>.html
- Logs detalhados: saída do processo capturada e exibida via log4j

---

## 🎨 Como interpretar a pontuação
- 0 a 49 (vermelho): ruim
- 50 a 89 (laranja): precisa de melhorias
- 90 a 100 (verde): bom

Uma pontuação perfeita de 100 é rara e não esperada. Melhorias incrementais (ex.: de 99 para 100) exigem grandes otimizações.

---

## 📄 Arquivos Fonte
- [Documentação do Lighthouse](https://developer.chrome.com/docs/lighthouse/overview?hl=pt-br)
- [Pontuação de desempenho do Lighthouse](https://developer.chrome.com/docs/lighthouse/performance/performance-scoring?hl=pt-br)