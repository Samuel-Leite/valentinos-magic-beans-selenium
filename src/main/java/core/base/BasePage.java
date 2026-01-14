package core.base;

import core.driver.DriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe base para todas as páginas do projeto.
 *
 * Responsável por inicializar o WebDriver e os elementos da página
 * usando o padrão PageFactory. Todas as Page Objects devem herdar desta classe.
 */
@Log4j2
public abstract class BasePage {

    // Instância do WebDriver utilizada pelas páginas
    private final WebDriver driver;

    /**
     * Construtor da BasePage.
     *
     * Obtém a instância atual do WebDriver através da DriverFactory
     * e inicializa os elementos da página com o PageFactory.
     */
    public BasePage() {
        this.driver = DriverFactory.getDriver();
        if (driver == null) {
            throw new WebDriverException("WebDriver não foi inicializado");
        }
        PageFactory.initElements(driver, this);
    }

    /**
     * Navega para uma URL específica.
     *
     * @param url endereço da página
     */
    protected void navigateTo(String url) {
        driver.get(url);
    }
}