package core.percy;

import core.driver.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper para o Percy oficial.
 * Responsável por inicializar e capturar snapshots visuais
 * durante a execução dos testes.
 * Benefício: centraliza o uso do Percy, evitando duplicação de código.
 */
public class Percy {

    private static Percy instance;
    private final io.percy.selenium.Percy percy;

    /**
     * Construtor: recebe o WebDriver e inicializa o Percy oficial.
     */
    private Percy(WebDriver driver) {
        this.percy = new io.percy.selenium.Percy(driver);
    }

    /**
     * Inicializa Percy com o WebDriver atual.
     * Deve ser chamado no Hook @Before.
     */
    public static void init() {
        WebDriver driver = DriverFactory.getDriver();
        if (instance == null && driver != null) {
            instance = new Percy(driver);
        }
    }

    /**
     * Captura um snapshot visual da página atual.
     *
     * @param name Nome da captura (aparece no dashboard do Percy).
     *             Use nomes descritivos, como "Login Page" ou "Dashboard Admin".
     */
    public static void percySnapshot(String name) {
        if (instance != null) {
            instance.percy.snapshot(name);
        }
    }

    /**
     * Captura um snapshot visual da página atual e agrupa sob um caso de teste.
     *
     * @param name     Nome da captura (aparece no dashboard do Percy).
     * @param testCase Nome do caso de teste para agrupar snapshots relacionados.
     */
    public static void percySnapshot(String name, String testCase) {
        if (instance != null) {
            Map<String, Object> options = new HashMap<>();
            options.put("testCase", testCase);
            instance.percy.snapshot(name, options);
        }
    }
}