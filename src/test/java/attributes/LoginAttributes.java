package attributes;

import core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginAttributes extends BasePage {

    @FindBy(css = "[data-test-id='header-login-button-desktop']")
    public WebElement btnLogin;
    @FindBy(css = "[data-test-id='login-email-input']")
    public WebElement txtEmail;
    @FindBy(css = "[data-test-id='login-password-input']")
    public WebElement txtPassword;
    @FindBy(css = "[data-test-id='login-submit-button']")
    public WebElement btnSubmit;
}
