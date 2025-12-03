package steps;

import io.cucumber.java.pt.Entao;
import pages.HomePage;

import static core.driver.DriverFactory.driver;

public class HomeSteps {

    HomePage homePage = new HomePage(driver);

    @Entao("sera efetuado o login com sucesso")
    public void seraEfetuadoOLoginComSucesso(){
        homePage.validarLoginSucesso();
    }
}
