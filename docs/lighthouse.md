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
- Se a propriedade `-Dlighthouse=true` estiver habilitada, o código dispara o Lighthouse CLI como processo externo. 
- O relatório é gerado em formato HTML dentro da pasta `target/lighthouse-reports`.

---

## ⚙️ Como executar o Lighthouse

Precisa trocar a variável do lighthouse para 'true' no arquivo testng.xml:

```Xml
<parameter name="lighthouse" value="true"/> <!-- true ou false -->
```

ou no comando 'mvn'

```bash
mvn clean test allure:report allure:serve -Denvironment=qa -Dbrowser=chrome -Dheadless=false -Dlighthouse=false
```

---

## 📊 Indicadores do Lighthouse

Os indicadores abaixo explicam como o Lighthouse avalia diferentes aspectos de qualidade de uma aplicação web:

- **Performance** ⚡  
  Mede a velocidade e eficiência de carregamento da página.
  Inclui métricas como *First Contentful Paint (FCP)*, *Largest Contentful Paint (LCP)*, *Total Blocking Time (TBT)* e *Cumulative Layout Shift (CLS)*.  
  **Em resumo:** indica o quão rápido e estável o site é para o usuário.

- **Accessibility** ♿  
  Avalia se o site é inclusivo e acessível para o usuário.
  Checa contraste de cores, nomes de botões/links, estrutura de títulos e navegação por teclado.  
  **Em resumo:** mostra se o site é inclusivo e acessível para todos.

- **Best Practices** ✅  
  Verifica se o site segue boas práticas de desenvolvimento e segurança.  
  Exemplos: uso correto de HTTPS, proteção contra ataques XSS, evitar recursos obsoletos.  
  **Em resumo:** garante que o site está construído de forma moderna e segura.

- **SEO (Search Engine Optimization)** 🔍  
  Mede se o site está otimizado para aparecer em buscadores como Google.  
  Checa indexação, meta tags e recomendações básicas de SEO.  
  **Em resumo:** indica se o site tem boas chances de ser encontrado em pesquisas.

### 🎯 Pontuação dos resultados:

As pontuações são codificadas por cores:
- 0 a 49 (vermelho): ruim
- 50 a 89 (laranja): precisa de melhorias
- 90 a 100 (verde): bom

---

## 📊 Métricas de Performance

- [First Contentful Paint (FCP)](https://developer.chrome.com/docs/lighthouse/performance/first-contentful-paint?hl=pt-br): mede quanto tempo o navegador leva para renderizar o primeiro conteúdo do DOM depois que um usuário navega até a página
- [Largest Contentful Paint (LCP)](https://developer.chrome.com/docs/lighthouse/performance/lighthouse-largest-contentful-paint?hl=pt-br): mede quanto tempo o maior elemento de conteúdo na janela de visualização é renderizado na tela, conteúdo principal da página fica visível para os usuários
- [Total Blocking Time (TBT)](https://developer.chrome.com/docs/lighthouse/performance/lighthouse-total-blocking-time?hl=pt-br): mede o tempo total em que uma página fica bloqueada para responder à entrada do usuário, como cliques do mouse, toques na tela ou pressionamentos do teclado
- [Cumulative Layout Shift (CLS)](https://web.dev/articles/cls?hl=pt-br): quantifica o quanto os elementos da página mudam de posição de forma inesperada enquanto o usuário interage ou a página carrega
- [Speed Index (SI)](https://developer.chrome.com/docs/lighthouse/performance/speed-index?hl=pt-br): mede a rapidez com que o conteúdo é exibido visualmente durante o carregamento da página

---

## 🛠️ Componentes Principais

- Execução via Selenium + Java: dispara auditorias após navegação automatizada
- Controle por System Property: só roda se -Dlighthouse=true for definido
- Detecção de SO: usa lighthouse em Linux/Mac e lighthouse.cmd no Windows
- Relatórios HTML: salvos em `target/lighthouse-reports/<nome>.html`
- Logs detalhados: saída do processo capturada e exibida via log4j

---

## 🎨 Como interpretar a pontuação
- 0 a 49 (vermelho): ruim
- 50 a 89 (laranja): precisa de melhorias
- 90 a 100 (verde): bom

Uma pontuação perfeita de 100 é rara e não esperada. Melhorias incrementais (ex.: de 99 para 100) exigem grandes otimizações.

### Observação

Com relação a variabilidade nos valores da pontuação de desempenho e das métricas não ocorre por conta do Lighthouse, geralmente acontece devido a alterações nas condições subjacentes. Problemas comuns incluem:
- Testes A/B ou mudanças nos anúncios veiculados
- Mudanças no roteamento de tráfego da Internet
- Teste em dispositivos diferentes, como um desktop de alto desempenho e um laptop de baixo desempenho
- Extensões de navegador que injetam JavaScript e adicionam/modificam solicitações de rede
- Software antivírus

Para obter resultado assertivo,  recomenda-se executar várias vezes, usar hardware estável, isolar fatores externos e considerar valores medianos em vez de execuções únicas.

---

## 📄 Arquivos Fonte
- [Documentação do Lighthouse](https://developer.chrome.com/docs/lighthouse/overview?hl=pt-br)
- [Pontuação de desempenho do Lighthouse](https://developer.chrome.com/docs/lighthouse/performance/performance-scoring?hl=pt-br)
- [Documentação sobre variabilidade da pontuação](https://github.com/GoogleChrome/lighthouse/blob/main/docs/variability.md)