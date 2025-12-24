package core.lighthouse;

import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class LighthouseValidator {

    /**
     * Valida os resultados do relatório Lighthouse (JSON) contra os thresholds definidos no YAML.
     *
     * @param root       Objeto JSON raiz do relatório Lighthouse.
     * @param categories Thresholds das categorias principais (performance, accessibility, etc.).
     * @param audits     Thresholds das métricas específicas (FCP, LCP, CLS, TBT, etc.).
     */
    public static void validate(JsonObject root, Map<String, Double> categories, Map<String, Double> audits) {

        // -----------------------------
        // Validação das categorias
        // -----------------------------
        JsonObject cats = root.getAsJsonObject("categories");
        for (Map.Entry<String, Double> entry : categories.entrySet()) {
            String key = entry.getKey();       // Nome da categoria (ex.: performance, seo)
            double minValue = entry.getValue(); // Valor mínimo exigido para aprovação

            // Se a categoria não existir no JSON, apenas loga um aviso
            if (!cats.has(key)) {
                log.warn("Categoria '{}' não encontrada no relatório Lighthouse.", key);
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
            String auditKey = entry.getKey();     // Nome da métrica (ex.: first-contentful-paint)
            double threshold = entry.getValue(); // Valor limite definido no YAML

            // Se o audit não existir no JSON, apenas loga um aviso
            if (!auds.has(auditKey)) {
                log.warn("Audit '{}' não encontrado no relatório Lighthouse.", auditKey);
                continue;
            }

            JsonObject audit = auds.getAsJsonObject(auditKey);
            if (audit == null) continue;

            // Caso o audit tenha valor numérico (ex.: FCP em ms, CLS, TBT)
            if (audit.has("numericValue")) {
                double actual = audit.get("numericValue").getAsDouble();
                log.info("Audit {}: {} (máx permitido: {})", auditKey, actual, threshold);

                // Se o valor real for maior que o máximo permitido → reprova
                if (actual > threshold) {
                    throw new AssertionError(String.format("Falha: %s=%.2f (máx %.2f)", auditKey, actual, threshold));
                }
            }
            // Caso o audit seja baseado em score (0–1)
            else if (audit.has("score")) {
                double actual = audit.get("score").getAsDouble();
                log.info("Audit {}: {} (mín exigido: {})", auditKey, actual, threshold);

                // Se o score for menor que o mínimo exigido → reprova
                if (actual < threshold) {
                    throw new AssertionError(String.format("Falha: %s=%.2f (mín %.2f)", auditKey, actual, threshold));
                }
            }
        }
    }
}