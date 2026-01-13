package core.lighthouse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.driver.DriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

/**
 * Classe responsável por executar o Lighthouse na URL atual do WebDriver.
 *
 * - Gera relatórios HTML e JSON.
 * - Valida os resultados contra thresholds definidos no YAML.
 */
@Log4j2
public class Lighthouse {

    /**
     * Executa o Lighthouse para a URL atual e valida os resultados.
     *
     * @param reportName Nome do arquivo de relatório (ex.: "login.html")
     */
    public static void runLighthouse(String reportName) {
        try {
            // Verifica se execução está habilitada via propriedade do sistema
            if (!"true".equalsIgnoreCase(System.getProperty("lighthouse"))) {
                return;
            }

            // Obtém a URL atual do navegador
            WebDriver driver = DriverFactory.getDriver();
            String currentUrl = driver.getCurrentUrl();
            log.info("Executando Lighthouse na URL: {}", currentUrl);

            // Cria diretório para relatórios
            File reportsDir = new File("target/lighthouse-reports");
            if (!reportsDir.exists()) reportsDir.mkdirs();

            // Caminhos dos relatórios
            String reportPathHtml = new File(reportsDir, reportName).getPath();
            String reportPathJson = reportPathHtml.replace(".html", ".report.json");

            // Comando Lighthouse (Windows usa .cmd)
            String os = System.getProperty("os.name").toLowerCase();
            String lighthouseCmd = os.contains("win") ? "lighthouse.cmd" : "lighthouse";

            String command = String.format(
                    "%s %s --output html --output json --output-path=%s --quiet --chrome-flags=\"--headless\"",
                    lighthouseCmd, currentUrl, reportPathHtml
            );

            // Executa comando
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            log.info("Relatórios gerados em: {} e {}", reportPathHtml, reportPathJson);

            // Carrega thresholds do YAML
            LighthouseConfig config = new LighthouseConfig();

            // Lê relatório JSON
            File jsonFile = new File(reportPathJson);
            if (!jsonFile.exists()) {
                throw new IllegalStateException("Arquivo JSON não encontrado: " + jsonFile.getPath());
            }

            String json = new String(Files.readAllBytes(jsonFile.toPath()));
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();

            // Valida resultados
            LighthouseValidator.validate(root, config.getCategories(), config.getAudits());

            log.info("Validação Lighthouse concluída com sucesso.");
        } catch (Exception e) {
            log.error("Erro ao executar Lighthouse", e);
            throw new RuntimeException(e);
        }
    }
}