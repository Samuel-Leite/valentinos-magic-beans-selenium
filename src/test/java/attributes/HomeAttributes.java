package attributes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeAttributes {

    @FindBy(css = "div.text-sm.font-semibold")
    public WebElement toastLoginSuccess;
}