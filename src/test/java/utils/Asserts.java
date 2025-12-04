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
            log.debug("Verificando se elemento é clicável: {}", element);

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(element));

            boolean isClickable = element.isDisplayed() && element.isEnabled();

            if (isClickable) {
                log.info("Elemento está clicável: {}", element);
            } else {
                log.error("Elemento não está clicável: {}", element);
            }

            return isClickable;
        } catch (Exception e) {
            log.error("Erro ao verificar se elemento é clicável: {}. Detalhes: {}", element, e.getMessage(), e);
            return false;
        }
    }

    public static boolean verifyElementIsVisible(WebElement element) {
        int maxAttempts = 3;
        boolean isVisible = false;

        for (int attempt = 1; attempt <= maxAttempts && !isVisible; attempt++) {
            try {
                log.debug("Tentativa {} de verificar visibilidade do elemento: {}", attempt, element);

                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOf(element));

                if (element.isDisplayed()) {
                    isVisible = true;
                    log.info("Elemento visível na tentativa {}", attempt);
                }
            } catch (Exception e) {
                log.error("Tentativa {} falhou: elemento não visível. Motivo: {}", attempt, e.getMessage());
                if (attempt < maxAttempts) {
                    try {
                        Thread.sleep(1000); // espera curta antes da próxima tentativa
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        if (!isVisible) {
            log.error("Elemento não ficou visível após {} tentativas.", maxAttempts);
        }
        return isVisible;
    }
}