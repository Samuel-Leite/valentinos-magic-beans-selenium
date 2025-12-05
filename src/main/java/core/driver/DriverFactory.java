package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Classe responsável por gerenciar a instância única do WebDriver.
 *
 * Utiliza o padrão Singleton para garantir que apenas uma instância do driver
 * seja criada durante a execução dos testes.
 *
 * O navegador é definido pelo parâmetro de sistema "browser"
 * (chrome | firefox | edge).
 */
@Log4j2
public class DriverFactory {

    // Instância única do WebDriver
    private static WebDriver driver;

    /**
     * Retorna a instância atual do WebDriver.
     * Caso ainda não exista, cria uma nova instância com base no parâmetro "browser".
     *
     * @return WebDriver instanciado
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser");
            if (browser == null || browser.isEmpty()) {
                throw new IllegalStateException("Parâmetro 'browser' não informado. Use chrome|firefox");
            }

            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    log.info("Inicializando navegador: Firefox");
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    log.info("Inicializando navegador: Chrome");
                    break;
                default:
                    throw new IllegalArgumentException("Navegador informado está inválido: " + browser);
            }
        }
        return driver;
    }

    /**
     * Finaliza a instância atual do WebDriver.
     * Fecha o navegador e limpa a referência para permitir nova criação futura.
     */
    public static void quitDriver() {
        if (driver != null) {
            log.info("Encerrando navegador...");
            driver.quit();
            driver = null;
        }
    }
}