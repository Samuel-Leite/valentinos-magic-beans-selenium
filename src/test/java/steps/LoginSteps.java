package steps;

import core.driver.DriverFactory;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Dado("^que acesse a pagina de login$")
    public void queAcesseAPaginaDeLogin() {
        loginPage.acessaAplicacao();
    }

    @Quando("informar as credencias validas")
    public void informarAsCredenciasValidas() {
        loginPage.realizarLogin("francisco-palheta@uorak.com", "Cafe1234");
    }
}
