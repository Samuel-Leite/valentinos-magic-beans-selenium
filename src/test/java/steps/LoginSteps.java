package steps;

import core.data.DataYaml;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import pages.LoginPage;

import java.util.LinkedHashMap;

public class LoginSteps {

    LinkedHashMap<String, String> credenciais = DataYaml.getMapYamlValues("credencial", "usuario_valido");
    LoginPage loginPage = new LoginPage();

    @Dado("^que acesse a pagina de login$")
    public void queAcesseAPaginaDeLogin() {
        loginPage.acessaAplicacao();
    }

    @Quando("informar as credencias validas")
    public void informarAsCredenciasValidas() {
        loginPage.realizarLogin(credenciais.get("email"), credenciais.get("password"));
    }
}