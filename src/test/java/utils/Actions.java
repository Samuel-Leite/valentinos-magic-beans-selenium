package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.driver.DriverFactory.driver;

@Log4j2
public class Actions {

    public static void click(WebElement element) {
        try {
            // Cria o WebDriverWait local (10 segundos)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera até que o elemento esteja clicável
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Tenta clicar
            element.click();

            log.info("Click action performed on element: " + element);

        } catch (Exception e) {
            log.error("Click action failed on element: " + element + " - " + e.getMessage());
            throw e;
        }
    }

    public static void sendKeys(WebElement element, String value) {
        try {
            // Cria o WebDriverWait local (10 segundos)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera até que o elemento esteja visível
            wait.until(ExpectedConditions.visibilityOf(element));

            // Limpa o campo antes de digitar
            element.clear();

            // Digita o valor
            element.sendKeys(value);

            log.info("Text input completed on element: " + element);

        } catch (Exception e) {
            log.error("Text input failed on element: " + element + " - " + e.getMessage());
            throw e;
        }
    }
}
