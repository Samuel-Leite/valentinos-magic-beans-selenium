package hooks;

import core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import utils.Screenshot;

/**
 * Classe de Hooks do Cucumber.
 *
 * Contém métodos anotados com @Before e @After que são executados
 * antes e depois de cada cenário de teste.
 *
 * Responsável por inicializar e encerrar o WebDriver.
 */
@Log4j2
public class Hooks {

    private Scenario scenario;

    /**
     * Executado antes de cada cenário.
     *
     */
    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        DriverFactory.getDriver();
        log.info("TESTE INICIADO: {}", scenario.getName());
    }

    /**
     * Executado depois de cada step.
     *
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        Screenshot.takeScreenshot(scenario);
    }

    /**
     * Executado após cada cenário.
     *
     */
    @After
    public void tearDown() {
        log.info("TESTE FINALIZADO: {}", scenario.getName());
        log.info("TESTE STATUS: {}", scenario.getStatus());
        DriverFactory.quitDriver();
        log.info("Navegador fechado");
    }
}