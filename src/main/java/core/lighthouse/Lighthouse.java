package core.lighthouse;

import core.driver.DriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Log4j2
public class Lighthouse {

    /**
     * Executa o Lighthouse para a URL atual do WebDriver e gera um relatório HTML
     * dentro da pasta target/lighthouse-reports, somente se a variável de controle estiver habilitada.
     *
     * @param reportName Nome do arquivo de relatório (ex.: "login.html")
     */
    public static void runLighthouse(String reportName) {
        try {
            // Verifica se o Lighthouse está habilitado via System Property
            String runLighthouse = System.getProperty("lighthouse", "false");
            if (!runLighthouse.equalsIgnoreCase("true")) {
                log.info("Execução do Lighthouse desativada por configuração (lighthouse=false).");
                return;
            }

            WebDriver driver = DriverFactory.getDriver();
            String currentUrl = driver.getCurrentUrl();

            log.info("Executando Lighthouse na URL atual: {}", currentUrl);

            // Cria a pasta target/lighthouse-reports se não existir
            File reportsDir = new File("target/lighthouse-reports");
            if (!reportsDir.exists()) {
                boolean created = reportsDir.mkdirs();
                if (created) {
                    log.info("Pasta criada: {}", reportsDir.getAbsolutePath());
                }
            }

            // Caminho padronizado para salvar o relatório
            String reportPath = new File(reportsDir, reportName).getPath();

            // Detecta o sistema operacional
            String os = System.getProperty("os.name").toLowerCase();
            String lighthouseCmd = "lighthouse"; // padrão Linux/Mac
            if (os.contains("win")) {
                lighthouseCmd = "lighthouse.cmd"; // executável no Windows
            }

            // Monta o comando
            String command = String.format(
                    "%s %s --output html --output-path=%s --quiet --chrome-flags=\"--headless\"",
                    lighthouseCmd, currentUrl, reportPath
            );

            log.debug("Comando Lighthouse: {}", command);

            Process process = Runtime.getRuntime().exec(command);

            // Captura saída do processo (logs do Lighthouse)
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                log.debug(line);
            }

            process.waitFor();
            log.info("Relatório Lighthouse gerado em: {}", reportPath);

        } catch (Exception e) {
            log.error("Erro ao executar Lighthouse", e);
        }
    }
}