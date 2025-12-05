package pages;

import attributes.LoginAttributes;
import core.data.DataYaml;

import static utils.Actions.click;
import static utils.Actions.sendKeys;
import static utils.Asserts.verifyElementIsClickable;

public class LoginPage extends LoginAttributes {

    private final String url = DataYaml.getUrlBase();

    public void acessaAplicacao() {
        driver.get(url);
    }

    public void realizarLogin(String email, String password) {
        verifyElementIsClickable(btnLogin);
        click(btnLogin);

        verifyElementIsClickable(txtEmail);
        sendKeys(txtEmail, email);

        verifyElementIsClickable(txtPassword);
        sendKeys(txtPassword, password);

        verifyElementIsClickable(btnSubmit);
        click(btnSubmit);
    }
}