# 📊 Dashboard de Métricas — Infraestrutura e Execução de Testes Automatizados

Este documento organiza todas as métricas discutidas, separadas em painéis únicos e painéis múltiplos, para facilitar a configuração e interpretação no Grafana.

---

## Documentação das 10 Métricas — Jenkins + Selenium

### 1 - Tempo de Execução dos Builds (Percentis)

- **O que transmite:** Mede quanto tempo os builds levam para rodar, em diferentes percentis (P50, P75, P95, etc.).
- **Objetivo:** Identificar se os builds estão rápidos ou se há builds que demoram demais.
- **Exemplo prático:** É como medir quanto tempo seus colegas levam para terminar uma tarefa. A maioria termina em 30s (mediana), mas alguns demoram 1min (P95).

---

### 2 - Builds Instáveis

- **O que transmite:** Conta quantos builds terminaram com status “instável” (não falharam totalmente, mas tiveram problemas).
- **Objetivo:** Detectar instabilidade nos testes automatizados.
- **Exemplo prático:** É como um carro que funciona, mas com o freio falhando. Ele não quebrou, mas não está confiável.

---

### 3 - Builds Abortados

- **O que transmite:** Número de builds que foram interrompidos antes de terminar.
- **Objetivo:** Monitorar cancelamentos e entender se há problemas de configuração ou recursos insuficientes.
- **Exemplo prático:** É como começar uma corrida e desistir no meio do caminho.

---

### 4 - Fila de Execução

- **O que transmite:** Mostra quantos jobs estão pendentes, bloqueados e quanto tempo esperam antes de iniciar.
- **Objetivo:** Identificar gargalos na fila de builds.
- **Exemplo prático:** É como uma fila no supermercado: se há muitos carrinhos esperando, o caixa está sobrecarregado.

---

### 5 - Uso de CPU Jenkins

- **O que transmite:** Percentual de uso da CPU do servidor e da JVM do Jenkins.
- **Objetivo:** Avaliar se o servidor está sobrecarregado.
- **Exemplo prático:** É como ver se o motor do carro está trabalhando leve (10%) ou no limite (90%).

---

### 6 - Uso de Memória JVM

- **O que transmite:** Percentual de memória heap e non-heap usada pela JVM.
- **Objetivo:** Detectar risco de estouro de memória (OutOfMemory).
- **Exemplo prático:** É como acompanhar a memória do celular: se está quase cheia, o sistema pode travar.

---

### 7 - Garbage Collection (GC)

- **O que transmite:** Tempo gasto pelo Java em coletas de lixo (limpeza de memória).
- **Objetivo:** Avaliar se o GC está impactando a performance.
- **Exemplo prático:** É como parar várias vezes para arrumar a mesa. Se demora muito, atrapalha o trabalho.

---

## 🎯 Resumindo
As 7 métricas permitem responder perguntas essenciais:
- **Performance:** Quanto tempo os builds levam? (Tempo de Execução, Waiting Duration)
- **Estabilidade:** Os builds estão falhando ou instáveis? (Instáveis, Abortados)
- **Recursos:** O servidor aguenta a carga? (CPU, Memória, GC)
- **Eficiência:** Há gargalos na fila? (Fila de Execução)