package core.report;

import com.google.common.collect.ImmutableMap;
import lombok.extern.log4j.Log4j2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

@Log4j2
public class Report {

    /**
     * Cria o arquivo environment.xml com as variáveis do Runner
     */
    public void setEnv() {
        // Diretório padrão do Allure
        String resultsDir = System.getProperty("allure.results.directory", "allure-results");
        new File(resultsDir).mkdirs();

        // Monta o mapa de variáveis
        ImmutableMap<String, String> envVars = ImmutableMap.<String, String>builder()
                .put("Ambiente", System.getProperty("environment", "qa"))
                .put("Browser", System.getProperty("browser", "chrome"))
                .put("Headless", System.getProperty("headless", "false"))
                .put("Lighthouse", System.getProperty("lighthouse", "false"))
                .put("Sistema Operacional", System.getProperty("os.name"))
                .put("Versão Java", System.getProperty("java.version"))
                .put("Usuário", System.getProperty("user.name"))
                .build();

        // Escreve o environment.xml
        writeEnvironmentFile(envVars, resultsDir + "/");
    }

    private static void writeEnvironmentFile(ImmutableMap<String, String> environmentValuesSet, String customResultsPath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            var doc = docBuilder.newDocument();
            var environment = doc.createElement("environment");
            doc.appendChild(environment);

            environmentValuesSet.forEach((k, v) -> {
                var parameter = doc.createElement("parameter");
                var key = doc.createElement("key");
                var value = doc.createElement("value");
                key.appendChild(doc.createTextNode(k));
                value.appendChild(doc.createTextNode(v));
                parameter.appendChild(key);
                parameter.appendChild(value);
                environment.appendChild(parameter);
            });

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);

            File allureResultsDir = new File(customResultsPath);
            if (!allureResultsDir.exists()) {
                allureResultsDir.mkdirs();
            }

            StreamResult result = new StreamResult(new File(customResultsPath + "environment.xml"));
            transformer.transform(source, result);
            log.info("Variaveis da automação salvo no Allure Report em {}", customResultsPath);
        } catch (ParserConfigurationException | TransformerException e) {
            log.error("Erro ao escrever o arquivo environment.xml", e);
        }
    }
}