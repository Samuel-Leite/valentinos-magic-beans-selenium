package steps;

import io.cucumber.java.pt.Entao;
import pages.HomePage;

public class HomeSteps {

    HomePage homePage = new HomePage();

    @Entao("sera efetuado o login com sucesso")
    public void seraEfetuadoOLoginComSucesso(){
        homePage.validarLoginSucesso();
        homePage.realizarLogout();
    }
}