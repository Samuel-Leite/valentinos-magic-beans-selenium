package pages;

import attributes.HomeAttributes;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static utils.Asserts.verifyElementIsVisible;

@Log4j2
public class HomePage extends HomeAttributes {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void validarLoginSucesso() {
        verifyElementIsVisible(toastLoginSuccess);
    }
}