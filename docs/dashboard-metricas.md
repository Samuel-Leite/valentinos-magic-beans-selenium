# 📊 Dashboard de Métricas — Infraestrutura e Execução de Testes Automatizados

Este documento organiza todas as métricas citadas no Dashboard do Grafana, separadas em painéis únicos e painéis múltiplos, para facilitar a configuração e interpretação:

---

## Documentação com as métricas:

### 1 - Tempo de Execução dos Builds (Percentis)

- **O que transmite:** Mostra quanto tempo os builds levam para rodar, usando percentis para indicar o comportamento típico e os casos raros.
- **Objetivo:** Avaliar se os builds são consistentes e identificar exceções que demoram mais que o esperado.
- **Exemplo prático:** No gráfico, quase todos os builds terminam em 24,8 segundos, mas existe um caso raro que levou 86 segundos, o que pode indicar um problema pontual ou um build mais pesado.
![tempo-execucao-build.png](img/tempo-execucao-build.png)

---

### 2 - Builds Instáveis

- **O que transmite:** Mostra o número de builds que concluíram com status instável.
- **Objetivo:** Garantir que os builds não apresentem falhas parciais e que os testes sejam confiáveis.
- **Exemplo prático:** No gráfico, vemos 0 builds instáveis, o que significa que todos os builds rodaram de forma consistente e confiável.
![builds-instaveis.png](img/builds-instaveis.png)

---

### 3 - Builds Abortados

- **O que transmite:** Mostra o número de builds que foram interrompidos antes de concluir.
- **Objetivo:** Garantir que os builds rodem até o fim sem cancelamentos inesperados.
- **Exemplo prático:** No gráfico, vemos 0 builds abortados, o que significa que todos os builds executaram até o final sem serem interrompidos.
![builds-abortados.png](img/builds-abortados.png)

---

### 4 - Fila de Execução

- **O que transmite:** Mostra se há jobs pendentes, bloqueados e quanto tempo eles esperam antes de iniciar.
- **Objetivo:** Identificar gargalos na fila de builds e avaliar se o Jenkins está conseguindo atender rapidamente.
- **Exemplo prático:** No gráfico, não houve jobs pendentes (0), nenhum bloqueado (0) e o tempo médio de espera foi praticamente 0 segundos (0.001s). Isso significa que os builds estão começando imediatamente, sem fila ou atraso.
![fila-execucao.png](img/fila-execucao.png)

---

### 5 - Uso de CPU Jenkins

- **O que transmite:** Percentual de uso da CPU do servidor e da JVM do Jenkins, indicando se há sobrecarga.
- **Objetivo:** Avaliar se o Jenkins está consumindo muitos recursos ou se o servidor está tranquilo.
- **Exemplo prático:** No gráfico, o uso da CPU do sistema variou de 0% até no máximo 9%, e a JVM do Jenkins consumiu menos de 1%. Isso mostra que o servidor está trabalhando leve, sem risco de saturação.
![uso-cpu-jenkins.png](img/uso-cpu-jenkins.png)

---

### 6 - Uso de Memória JVM

- **O que transmite:** Percentual de memória heap (objetos da aplicação) e non-heap (estruturas internas da JVM) usada.
- **Objetivo:** Detectar risco de estouro de memória (OutOfMemory) e avaliar se há espaço suficiente para builds e testes.
- **Exemplo prático:** No gráfico, a heap está tranquila (7–10%), mas a non-heap está quase cheia (95–97%). É como acompanhar a memória do celular: os aplicativos ocupam pouco espaço, mas o sistema está usando quase toda a memória interna, o que pode levar a travamentos se não for monitorado.
![uso-memoria-jvm.png](img/uso-memoria-jvm.png)

---

### 7 - Garbage Collection (GC)

- **O que transmite:** Mostra quanto tempo o Java gasta em coletas de lixo, separando por tipos de memória (Young, Old, Concurrent).
- **Objetivo:** Avaliar se o GC está impactando a performance do Jenkins.
- **Exemplo prático:** No gráfico, o GC gastou 7,16s limpando objetos temporários (Young Generation), 1,22s em limpezas paralelas (Concurrent GC) e 0s em objetos antigos (Old Generation). Isso significa que a memória está sendo gerenciada de forma eficiente, sem pausas pesadas.
![garbage-collection.png](img/garbage-collection.png)

---

## 🎯 Resumindo
As 7 métricas permitem responder perguntas essenciais:
- **Performance:** Quanto tempo os builds levam? (Tempo de Execução, Waiting Duration)
- **Estabilidade:** Os builds estão falhando ou instáveis? (Instáveis, Abortados)
- **Recursos:** O servidor aguenta a carga? (CPU, Memória, GC)
- **Eficiência:** Há gargalos na fila? (Fila de Execução)

## 🔗 Links Úteis
- [Construção do Dashboard](docs/grafana-dashboard.json)