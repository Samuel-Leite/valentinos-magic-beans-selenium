package steps;

import core.data.DataYaml;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import pages.LoginPage;

import java.util.LinkedHashMap;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Dado("que acesse a pagina de login")
    public void queAcesseAPaginaDeLogin() {
        loginPage.acessarAplicacao();
    }

    @Quando("informar as credenciais {string}")
    public void informarAsCredenciais(String tipoUsuario) {
        LinkedHashMap<String, String> credenciais = DataYaml.getMapYamlValues("credencial", tipoUsuario);

        loginPage.realizarLogin(
                credenciais.get("email"),
                credenciais.get("password")
        );
    }
}