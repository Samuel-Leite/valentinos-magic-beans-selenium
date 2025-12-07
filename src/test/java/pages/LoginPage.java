package pages;

import attributes.LoginAttributes;
import core.data.DataYaml;

import static core.percy.Percy.percySnapshot;
import static utils.Actions.click;
import static utils.Actions.sendKeys;
import static utils.Asserts.verifyElementIsClickable;

/**
 * Page Object da página de Login.
 *
 * Utiliza os elementos definidos em {@link LoginAttributes}.
 */
public class LoginPage extends LoginAttributes {

    // URL base da aplicação, carregada a partir do arquivo YAML de configuração
    private final String url = DataYaml.getUrlBase();

    /**
     * Acessa a aplicação utilizando a URL base definida para o ambiente.
     */
    public void acessaAplicacao() {
        driver.get(url);
        percySnapshot("Página de Login");
    }

    /**
     * Realiza o login do usuário.
     *
     * @param email    E-mail do usuário
     * @param password Senha do usuário
     */
    public void realizarLogin(String email, String password) {
        verifyElementIsClickable(btnLogin);
        click(btnLogin);
        percySnapshot("Página das credenciais");
        verifyElementIsClickable(txtEmail);
        sendKeys(txtEmail, email);
        verifyElementIsClickable(txtPassword);
        sendKeys(txtPassword, password);
        verifyElementIsClickable(btnSubmit);
        click(btnSubmit);
    }
}