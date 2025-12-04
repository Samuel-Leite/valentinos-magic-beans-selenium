package attributes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeAttributes {

    @FindBy(css = "div.text-sm.font-semibold")
    public WebElement toastLoginSuccess;
    @FindBy(css = "button:has(svg.lucide-user)")
    public WebElement btnUserMenu;
    @FindBy(css = "div[role='menuitem'] span")
    public WebElement btnLogOut;
}