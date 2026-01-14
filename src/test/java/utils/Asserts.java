package utils;

import core.driver.DriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Classe utilitária para verificações de elementos no Selenium.
 *
 * Fornece métodos para validar se elementos estão visíveis ou clicáveis,
 * aplicando destaque visual para facilitar evidências.
 */
@Log4j2
public class Asserts {

    /**
     * Verifica se um elemento está clicável.
     *
     * @param element Elemento WebElement a ser verificado
     * @return true se o elemento estiver clicável, false caso contrário
     */
    public static boolean verifyElementIsClickable(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(element));

            boolean isClickable = element.isDisplayed() && element.isEnabled();

            if (isClickable) {
                log.info("Elemento está clicável: {}", element);
                highlightElement(driver, element, null);
            } else {
                log.error("Elemento não está clicável: {}", element);
            }

            return isClickable;
        } catch (Exception e) {
            log.error("Erro ao verificar se elemento é clicável: {}. Detalhes: {}", element, e.getMessage(), e);
            return false;
        }
    }

    /**
     * Verifica se um elemento está visível na tela.
     *
     * @param element Elemento WebElement a ser verificado
     * @return true se o elemento estiver visível, false caso contrário
     */
    public static boolean verifyElementIsVisible(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        int maxAttempts = 3;
        boolean isVisible = false;

        for (int attempt = 1; attempt <= maxAttempts && !isVisible; attempt++) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOf(element));

                if (element.isDisplayed()) {
                    isVisible = true;
                    log.info("Elemento visível na tentativa {}", attempt);
                    highlightElement(driver, element, null);
                }
            } catch (Exception e) {
                log.warn("Tentativa {} falhou: elemento não visível. Motivo: {}", attempt, e.getMessage());
                if (attempt < maxAttempts) {
                    waitSeconds(1);
                }
            }
        }
        return isVisible;
    }

    /**
     * Aplica um destaque visual (highlight) ao elemento.
     *
     * @param driver  WebDriver ativo
     * @param element Elemento alvo
     * @param color   Cor em RGBA ou HEX (se null usa azul padrão)
     */
    public static void highlightElement(WebDriver driver, WebElement element, String color) {
        try {
            String highlightColor = (color == null || color.isEmpty())
                    ? "rgba(0,0,255,0.5)" // azul padrão
                    : color;

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].style.boxShadow = 'inset 0 0 0 1000px " + highlightColor + "';" +
                            "arguments[0].style.transition = 'box-shadow 0.3s ease-in-out';",
                    element
            );

            waitSeconds(1);
            js.executeScript("arguments[0].style.boxShadow = '';", element);

        } catch (Exception e) {
            log.error("Erro ao aplicar highlight: {}", e.getMessage());
        }
    }

    /**
     * Aguarda pelo número de segundos informado.
     *
     * @param seconds quantidade de segundos
     */
    public static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Erro no wait: {}", e.getMessage());
        }
    }
}