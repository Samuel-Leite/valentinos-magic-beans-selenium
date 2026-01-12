package steps;

import core.data.DataYaml;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import lombok.extern.log4j.Log4j2;
import pages.LoginPage;

import java.util.LinkedHashMap;

@Log4j2
public class LoginSteps {

    LinkedHashMap<String, String> credenciais = DataYaml.getMapYamlValues("credencial", "usuario_valido");
    LoginPage loginPage = new LoginPage();

    @Dado("^que acesse a pagina de login$")
    public void queAcesseAPaginaDeLogin() {
        loginPage.acessaAplicacao();
    }

    @Quando("informar as credenciais validas")
    public void informarAsCredenciaisValidas() {
        loginPage.realizarLogin(credenciais.get("email"), credenciais.get("password"));
    }
}