package core.lighthouse;

import core.data.DataYaml;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por carregar thresholds do Lighthouse a partir do YAML.
 *
 * - Categorias principais (performance, accessibility, etc.)
 * - Métricas específicas (FCP, LCP, CLS, TBT, etc.)
 */
@Log4j2
public class LighthouseConfig {

    private final Map<String, Double> categories;
    private final Map<String, Double> audits;

    /**
     * Construtor: carrega configuração do YAML e converte valores para Double.
     */
    public LighthouseConfig() {
        Map<String, Object> config = DataYaml.getLighthouseConfig();

        Map<String, Object> categoriesRaw =
                (Map<String, Object>) ((Map<String, Object>) config.get("lighthouse")).get("categories");
        categories = convertToDoubleMap(categoriesRaw);

        Map<String, Object> auditsRaw =
                (Map<String, Object>) ((Map<String, Object>) config.get("lighthouse")).get("audits");
        audits = convertToDoubleMap(auditsRaw);

        log.info("Thresholds carregados - Categorias: {}", categories);
        log.info("Thresholds carregados - Audits: {}", audits);
    }

    /**
     * Converte valores genéricos para Double.
     *
     * @param raw mapa original carregado do YAML
     * @return mapa convertido com valores Double
     */
    private Map<String, Double> convertToDoubleMap(Map<String, Object> raw) {
        Map<String, Double> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : raw.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Number) {
                result.put(entry.getKey(), ((Number) value).doubleValue());
            } else if (value instanceof String) {
                try {
                    result.put(entry.getKey(), Double.parseDouble(((String) value).replace(",", ".")));
                } catch (NumberFormatException e) {
                    log.warn("Valor inválido para '{}': {}", entry.getKey(), value);
                }
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