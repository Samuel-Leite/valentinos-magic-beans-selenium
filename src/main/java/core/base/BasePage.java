package core.base;

import core.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe base para todas as páginas do projeto.
 *
 * Responsável por inicializar o WebDriver e os elementos da página
 * usando o padrão PageFactory. Todas as Page Objects devem herdar desta classe.
 */
public abstract class BasePage {

    // Instância do WebDriver utilizada pelas páginas
    protected WebDriver driver;

    /**
     * Construtor da BasePage.
     *
     * Obtém a instância atual do WebDriver através da DriverFactory
     * e inicializa os elementos da página com o PageFactory.
     */
    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    /**
     * Retorna a instância atual do WebDriver.
     *
     * @return WebDriver em uso
     */
    public WebDriver getDriver() {
        return driver;
    }
}