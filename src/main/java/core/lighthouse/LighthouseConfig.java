package core.lighthouse;

import core.data.DataYaml;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class LighthouseConfig {

    // Mapa com os thresholds das categorias principais (performance, accessibility, etc.)
    private final Map<String, Double> categories;

    // Mapa com os thresholds dos audits específicos (FCP, LCP, CLS, TBT, etc.)
    private final Map<String, Double> audits;

    /**
     * Construtor da classe.
     * Carrega as configurações do arquivo YAML usando DataYaml
     * e converte os valores para Double, armazenando em dois mapas:
     * - categories: thresholds das categorias principais
     * - audits: thresholds das métricas específicas
     */
    public LighthouseConfig() {
        // Carrega configuração completa do YAML
        Map<String, Object> config = DataYaml.getLighthouseConfig();

        // Extrai e converte thresholds das categorias
        Map<String, Object> categoriesRaw = (Map<String, Object>) ((Map<String, Object>) config.get("lighthouse")).get("categories");
        categories = convertToDoubleMap(categoriesRaw);

        // Extrai e converte thresholds dos audits
        Map<String, Object> auditsRaw = (Map<String, Object>) ((Map<String, Object>) config.get("lighthouse")).get("audits");
        audits = convertToDoubleMap(auditsRaw);

        // Loga os thresholds carregados para conferência
        log.info("Thresholds carregados - Categorias: {}", categories);
        log.info("Thresholds carregados - Audits: {}", audits);
    }

    /**
     * Converte um mapa de valores genéricos (Object) para Double.
     * Apenas valores que são instâncias de Number são aceitos.
     *
     * @param raw mapa original carregado do YAML
     * @return mapa convertido com valores Double
     */
    private Map<String, Double> convertToDoubleMap(Map<String, Object> raw) {
        Map<String, Double> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : raw.entrySet()) {
            if (entry.getValue() instanceof Number) {
                result.put(entry.getKey(), ((Number) entry.getValue()).doubleValue());
            }
        }
        return result;
    }

    /**
     * Retorna os thresholds das categorias principais.
     *
     * @return mapa de categorias e seus valores mínimos exigidos
     */
    public Map<String, Double> getCategories() {
        return categories;
    }

    /**
     * Retorna os thresholds dos audits específicos.
     *
     * @return mapa de audits e seus valores máximos permitidos
     */
    public Map<String, Double> getAudits() {
        return audits;
    }
}