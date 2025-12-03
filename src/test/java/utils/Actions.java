package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.driver.DriverFactory.driver;

public class Actions {

    public static void click(WebElement element) {
        try {
            // Cria o WebDriverWait local (10 segundos)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera até que o elemento esteja clicável
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Tenta clicar
            element.click();

            System.out.println("[ElementActions] Click action performed on element: " + element);

        } catch (Exception e) {
            System.out.println("[ElementActions] Click action failed on element: " + element + " - " + e.getMessage());
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

            System.out.println("[ElementActions] Text input completed on element: " + element);

        } catch (Exception e) {
            System.out.println("[ElementActions] Text input failed on element: " + element + " - " + e.getMessage());
            throw e;
        }
    }
}
