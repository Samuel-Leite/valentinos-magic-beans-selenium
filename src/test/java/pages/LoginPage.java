package pages;

import attributes.LoginAttributes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static utils.Actions.click;
import static utils.Actions.sendKeys;
import static utils.Asserts.verifyElementIsClickable;

public class LoginPage extends LoginAttributes {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acessaAplicacao() {
        driver.get("https://valentinos-magic-beans.click/");
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