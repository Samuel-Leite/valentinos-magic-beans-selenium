package hooks;

import core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.log4j.Log4j2;

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

    /**
     * Executado antes de cada cenário.
     *
     * Inicializa o WebDriver através da DriverFactory
     * e registra no log o início do teste.
     */
    @Before
    public void setUp() {
        DriverFactory.getDriver();
        log.info("Iniciando o teste");
    }

    /**
     * Executado após cada cenário.
     *
     * Finaliza o WebDriver através da DriverFactory
     * e registra no log o fechamento do navegador.
     */
    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        log.info("Navegador fechado");
    }
}