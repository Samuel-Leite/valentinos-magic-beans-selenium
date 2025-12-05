package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
            boolean headless = Boolean.parseBoolean(System.getProperty("headless"));

            log.info("Inicializando navegador={} | headless={}", browser, headless);

            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    firefoxOptions.addArguments("--start-maximized");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless=new"); // headless moderno
                    }
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;

                default:
                    throw new IllegalArgumentException("Navegador inválido: " + browser);
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