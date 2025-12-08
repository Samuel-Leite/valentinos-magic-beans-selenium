package pages;

import attributes.HomeAttributes;

import static core.lighthouse.Lighthouse.runLighthouse;
import static utils.Actions.*;
import static utils.Asserts.*;
import static core.percy.Percy.percySnapshot;

/**
 * Page Object da página inicial (Home).
 *
 * Contém ações e validações específicas da Home,
 * utilizando os elementos definidos em {@link HomeAttributes}.
 */
public class HomePage extends HomeAttributes {

    /**
     * Valida se o login foi realizado com sucesso.
     *
     */
    public void validarLoginSucesso() {
        verifyElementIsVisible(toastLoginSuccess);
        percySnapshot("Home");
        runLighthouse("lighthouse-home.html");
    }

    /**
     * Realiza o logout do usuário.
     *
     */
    public void realizarLogout() {
        verifyElementIsClickable(btnUserMenu);
        click(btnUserMenu);
        verifyElementIsClickable(btnLogOut);
        click(btnLogOut);
        percySnapshot("Apos realizar logout");
    }
}