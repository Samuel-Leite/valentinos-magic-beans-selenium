package core.lighthouse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.driver.DriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

@Log4j2
public class Lighthouse {

    /**
     * Executa o Lighthouse para a URL atual do WebDriver e gera relatórios HTML + JSON.
     * Em seguida, valida os resultados contra os thresholds definidos no arquivo YAML.
     *
     * @param reportName Nome do arquivo de relatório (ex.: "login.html")
     */
    public static void runLighthouse(String reportName) {
        try {
            // Verifica se a execução do Lighthouse está habilitada via propriedade do sistema
            String runLighthouse = System.getProperty("lighthouse");
            if (!runLighthouse.equalsIgnoreCase("true")) {
                return;
            }

            // Obtém a URL atual do navegador em execução
            WebDriver driver = DriverFactory.getDriver();
            String currentUrl = driver.getCurrentUrl();
            log.info("Executando Lighthouse na URL atual: {}", currentUrl);

            // Cria diretório para armazenar relatórios, se não existir
            File reportsDir = new File("target/lighthouse-reports");
            if (!reportsDir.exists()) reportsDir.mkdirs();

            // Define caminhos para os relatórios HTML e JSON
            String reportPathHtml = new File(reportsDir, reportName).getPath();
            String reportPathJson = reportPathHtml.replace(".html", ".report.json");

            // Determina o comando correto do Lighthouse conforme o sistema operacional
            String os = System.getProperty("os.name").toLowerCase();
            String lighthouseCmd = os.contains("win") ? "lighthouse.cmd" : "lighthouse";

            // Monta o comando para executar o Lighthouse em modo headless
            String command = String.format(
                    "%s %s --output html --output json --output-path=%s --quiet --chrome-flags=\"--headless\"",
                    lighthouseCmd, currentUrl, reportPathHtml
            );

            log.debug("Comando Lighthouse: {}", command);

            // Executa o comando Lighthouse via Runtime
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            log.info("Relatórios Lighthouse gerados em: {} e {}", reportPathHtml, reportPathJson);

            // Carrega thresholds definidos no arquivo YAML (categorias e audits)
            LighthouseConfig config = new LighthouseConfig();

            // Lê o relatório JSON gerado pelo Lighthouse
            File jsonFile = new File(reportPathJson);
            if (!jsonFile.exists())
                throw new IllegalStateException("Arquivo JSON não encontrado: " + jsonFile.getPath());

            String json = new String(Files.readAllBytes(jsonFile.toPath()));
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();

            // Valida os resultados do relatório contra os thresholds
            LighthouseValidator.validate(root, config.getCategories(), config.getAudits());

            log.info("Validação Lighthouse concluída com sucesso.");
        } catch (Exception e) {
            // Captura qualquer erro durante execução ou validação
            log.error("Erro ao executar Lighthouse", e);
            throw new RuntimeException(e);
        }
    }
}