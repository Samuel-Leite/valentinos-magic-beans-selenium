package utils;

import core.driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class Percy {

    private static Percy instance;            // instância da utilitária
    private final io.percy.selenium.Percy percy;  // Percy oficial

    /**
     * Construtor: recebe o WebDriver e inicializa o Percy oficial.
     */
    public Percy(WebDriver driver) {
        this.percy = new io.percy.selenium.Percy(driver);
    }

    /**
     * Inicializa Percy com o WebDriver atual.
     * Esse método deve ser chamado no Hook @Before.
     */
    public static void init() {
        WebDriver driver = DriverFactory.getDriver();
        if (instance == null) {
            instance = new Percy(driver);
        }
    }

    /**
     * Captura um snapshot visual da página atual.
     *
     * @param name Nome da captura (aparece no dashboard do Percy)
     */
    public static void percySnapshot(String name) {
        if (instance != null) {
            instance.percy.snapshot(name);
        }
    }
}