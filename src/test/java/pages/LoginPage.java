package pages;

import attributes.LoginAttributes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static utils.Actions.click;
import static utils.Actions.sendKeys;

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
        click(btnLogin);
        sendKeys(txtEmail, email);
        sendKeys(txtPassword, password);
        click(btnSubmit);
    }
}
