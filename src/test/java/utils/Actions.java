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
            element.click();
            log.debug("Clique realizado com sucesso no elemento: {}", element);
        } catch (Exception e) {
            log.error("Falha ao clicar no elemento: {}. Detalhes: {}", element, e.getMessage(), e);
            throw e;
        }
    }

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