package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;

@Log4j2
public class Actions {

    /**
     * Realiza um clique em um elemento da página.
     *
     * - Se o clique for bem-sucedido, registra log em nível DEBUG.
     * - Se ocorrer falha, registra log em nível ERROR e relança a exceção.
     *
     * @param element Elemento WebElement que será clicado
     */
    public static void click(WebElement element) {
        try {
            element.click();
            log.debug("Clique realizado com sucesso no elemento: {}", element);
        } catch (Exception e) {
            log.error("Falha ao clicar no elemento: {}. Detalhes: {}", element, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Preenche um campo de texto com o valor informado.
     *
     * - Primeiro limpa o campo com `clear()`.
     * - Em seguida insere o valor com `sendKeys()`.
     * - Se for bem-sucedido, registra log em nível DEBUG.
     * - Se ocorrer falha, registra log em nível ERROR e relança a exceção.
     *
     * @param element Elemento WebElement que será preenchido
     * @param value   Valor a ser inserido no campo
     */
    public static void sendKeys(WebElement element, String value) {
        try {
            element.clear();
            element.sendKeys(value);
            log.debug("Valor preenchido com sucesso no elemento: {}", element);
        } catch (Exception e) {
            log.error("Falha ao preencher o elemento: {}. Detalhes: {}", element, e.getMessage(), e);
            throw e;
        }
    }
}