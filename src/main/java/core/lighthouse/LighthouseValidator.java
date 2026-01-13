package core.lighthouse;

import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class LighthouseValidator {

    /**
     * Valida os resultados do relatório Lighthouse (JSON) contra os thresholds definidos no YAML.
     *
     * @param root       JSON raiz do relatório Lighthouse
     * @param categories Thresholds das categorias principais (performance, accessibility, etc.)
     * @param audits     Thresholds das métricas específicas (FCP, LCP, CLS, TBT, etc.)
     */
    public static void validate(JsonObject root, Map<String, Double> categories, Map<String, Double> audits) {

        // -----------------------------
        // Validação das categorias
        // -----------------------------
        JsonObject cats = root.getAsJsonObject("categories");
        for (Map.Entry<String, Double> entry : categories.entrySet()) {
            String key = entry.getKey();
            double minValue = entry.getValue();

            if (!cats.has(key)) {
                log.warn("Categoria '{}' não encontrada no relatório.", key);
                continue;
            }

            // Obtém o score real da categoria (0–1)
            double actual = cats.getAsJsonObject(key).get("score").getAsDouble();
            log.info("Categoria {}: {} (mínimo exigido: {})", key, actual, minValue);

            // Se o score for menor que o mínimo exigido → reprova
            if (actual < minValue) {
                throw new AssertionError(String.format("Falha: %s=%.2f (mín %.2f)", key, actual, minValue));
            }
        }

        // -----------------------------
        // Validação dos audits
        // -----------------------------
        JsonObject auds = root.getAsJsonObject("audits");
        for (Map.Entry<String, Double> entry : audits.entrySet()) {
            String auditKey = entry.getKey();
            double threshold = entry.getValue();

            if (!auds.has(auditKey)) {
                log.warn("Audit '{}' não encontrado no relatório.", auditKey);
                continue;
            }

            JsonObject audit = auds.getAsJsonObject(auditKey);

            // Métricas numéricas (ex.: FCP em ms, CLS, TBT)
            if (audit.has("numericValue")) {
                double actual = audit.get("numericValue").getAsDouble();
                log.info("Audit {}: {} (máx permitido: {})", auditKey, actual, threshold);

                if (actual > threshold) {
                    throw new AssertionError(String.format("Falha: %s=%.2f (máx %.2f)", auditKey, actual, threshold));
                }
            }
            // Métricas baseadas em score (0–1)
            else if (audit.has("score")) {
                double actual = audit.get("score").getAsDouble();
                log.info("Audit {}: {} (mín exigido: {})", auditKey, actual, threshold);

                if (actual < threshold) {
                    throw new AssertionError(String.format("Falha: %s=%.2f (mín %.2f)", auditKey, actual, threshold));
                }
            }
        }
    }
}