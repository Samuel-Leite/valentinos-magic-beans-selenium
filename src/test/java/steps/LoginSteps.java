package steps;

import core.data.DataYaml;
import core.driver.DriverFactory;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import java.util.LinkedHashMap;

public class LoginSteps {

    LinkedHashMap<String, String> credenciais = DataYaml.getMapYamlValues("login", "usuario_valido");

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Dado("^que acesse a pagina de login$")
    public void queAcesseAPaginaDeLogin() {
        loginPage.acessaAplicacao();
    }

    @Quando("informar as credencias validas")
    public void informarAsCredenciasValidas() {
        loginPage.realizarLogin(credenciais.get("email"), credenciais.get("password"));
    }
}
