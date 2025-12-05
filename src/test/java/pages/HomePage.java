package pages;

import attributes.HomeAttributes;

import static utils.Actions.*;
import static utils.Asserts.*;

public class HomePage extends HomeAttributes {

    public void validarLoginSucesso() {
        verifyElementIsVisible(toastLoginSuccess);
    }

    public void realizarLogout() {
        verifyElementIsClickable(btnUserMenu);
        click(btnUserMenu);
        verifyElementIsClickable(btnLogOut);
        click(btnLogOut);
    }
}