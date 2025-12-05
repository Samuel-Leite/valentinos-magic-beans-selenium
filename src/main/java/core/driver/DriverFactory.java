package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Log4j2
public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser");
            if (browser == null || browser.isEmpty()) {
                throw new IllegalStateException("Parâmetro 'browser' não informado. Use chrome|firefox|edge");
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

    public static void quitDriver() {
        if (driver != null) {
            log.info("Encerrando navegador...");
            driver.quit();
            driver = null;
        }
    }
}