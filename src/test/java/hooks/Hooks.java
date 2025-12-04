package hooks;

import core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.getDriver();
        System.out.println("[Hooks] Navegador iniciado.");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        System.out.println("[Hooks] Navegador fechado.");
    }
}