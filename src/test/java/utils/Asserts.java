package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import core.driver.DriverFactory;

@Log4j2
public class Asserts {

    public static boolean verifyElementIsClickable(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            log.debug("Verificando se elemento é clicável: {}", element);

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

    public static boolean verifyElementIsVisible(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
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
                    highlightElement(driver, element, null);
                }
            } catch (Exception e) {
                log.error("Tentativa {} falhou: elemento não visível. Motivo: {}", attempt, e.getMessage());
                if (attempt < maxAttempts) {
                    waitSeconds(1);
                }
            }
        }
        return isVisible;
    }

    /**
     * Aplica um destaque visual ao elemento.
     * Útil para debugging, evidência e visibilidade durante execução.
     * @param driver WebDriver ativo
     * @param element Elemento alvo
     * @param color Cor em RGBA ou HEX (se null usa azul padrão)
     */
    public static void highlightElement(WebDriver driver, WebElement element, String color) {
        String env = System.getProperty("env", "qa"); // default qa
        log.info("Carregando o highlight no elemento do ambiente de {}", env);

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
     * Espera pelo número de segundos informado.
     * @param seconds quantidade de segundos (ex: 1 ou 2)
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