package hooks;

import core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.getDriver();
        log.info("Iniciando o teste");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        log.info("Navegador fechado");
    }
}