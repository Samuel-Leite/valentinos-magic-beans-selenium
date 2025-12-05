package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import core.driver.DriverFactory;

/**
 * Classe utilitária para verificações (asserts) de elementos no Selenium.
 *
 * Contém métodos para validar se elementos estão visíveis ou clicáveis,
 * além de aplicar destaque visual durante a execução para facilitar evidências.
 */
@Log4j2
public class Asserts {

    /**
     * Verifica se um elemento está clicável.
     *
     * - Aguarda até 10 segundos pela condição de "elementToBeClickable".
     * - Confirma se o elemento está visível e habilitado.
     * - Aplica highlight visual se o elemento estiver clicável.
     * - Registra logs em nível INFO/ERROR conforme o resultado.
     *
     * @param element Elemento WebElement a ser verificado
     * @return true se o elemento estiver clicável, false caso contrário
     */
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

    /**
     * Verifica se um elemento está visível na tela.
     *
     * - Tenta até 3 vezes verificar a visibilidade do elemento.
     * - Em cada tentativa aguarda até 5 segundos pela condição "visibilityOf".
     * - Aplica highlight visual se o elemento estiver visível.
     * - Registra logs detalhando tentativas e falhas.
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
     * Aplica um destaque visual (highlight) ao elemento.
     *
     * - Usa Javascript para aplicar um box-shadow temporário.
     * - Útil para debugging, evidência visual e acompanhamento da execução.
     * - Cor padrão: azul semitransparente (rgba(0,0,255,0.5)).
     *
     * @param driver  WebDriver ativo
     * @param element Elemento alvo
     * @param color   Cor em RGBA ou HEX (se null usa azul padrão)
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
     * Aguarda pelo número de segundos informado.
     *
     * - Utiliza Thread.sleep para pausar a execução.
     * - Se ocorrer InterruptedException, restaura o estado da thread.
     *
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