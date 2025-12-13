package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Classe responsável por gerenciar a instância do WebDriver.
 *
 * Utiliza ThreadLocal para garantir que cada thread de execução
 * (em testes paralelos) tenha sua própria instância de driver.
 *
 * O navegador é definido pelo parâmetro de sistema "browser"
 * (chrome | firefox).
 */
@Log4j2
public class DriverFactory {

    // Cada thread terá sua própria instância de WebDriver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Inicializa o WebDriver da thread atual.
     * Caso ainda não exista, cria uma nova instância com base no parâmetro "browser".
     */
    public static void initDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome");
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

            log.info("Inicializando navegador={} | headless={}", browser, headless);

            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                        firefoxOptions.addArguments("--window-size=1920,1080");
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless || System.getenv("JENKINS_HOME") != null) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }
                    // flags essenciais para rodar em Docker/Jenkins
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--start-maximized");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                default:
                    throw new IllegalArgumentException("Navegador inválido: " + browser);
            }
        }
    }

    /**
     * Retorna a instância atual do WebDriver da thread.
     *
     * @return WebDriver instanciado para a thread atual
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Finaliza a instância atual do WebDriver da thread.
     * Fecha o navegador e limpa a referência para permitir nova criação futura.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Encerrando navegador...");
            driver.get().quit();
            driver.remove();
        }
    }
}