package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.driver.DriverFactory.driver;

@Log4j2
public class Asserts {

    public static boolean verifyElementIsClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            boolean isClickable = element.isDisplayed() && element.isEnabled();

            log.info("Element is clickable: " + isClickable);
            return isClickable;
        } catch (Exception e) {
            log.error("Element is not clickable: " + e.getMessage());
            return false;
        }
    }

    public static boolean verifyElementIsVisible(WebElement element) {
        int maxAttempts = 5; // número máximo de tentativas
        int attempt = 0;
        boolean isVisible = false;

        while (attempt < maxAttempts && !isVisible) {
            try {
                attempt++;
                log.info("Tentativa " + attempt + " de verificar visibilidade...");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOf(element));

                if (element.isDisplayed()) {
                    isVisible = true;
                    log.info("Elemento visível na tentativa " + attempt);
                }
            } catch (Exception e) {
                log.error("Tentativa " + attempt + " falhou: elemento não visível ainda. Motivo: " + e.getMessage());
                if (attempt < maxAttempts) {
                    try {
                        Thread.sleep(2000); // espera 2 segundos antes da próxima tentativa
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        if (!isVisible) {
            log.error("Elemento não ficou visível após " + maxAttempts + " tentativas.");
        }

        return isVisible;
    }
}