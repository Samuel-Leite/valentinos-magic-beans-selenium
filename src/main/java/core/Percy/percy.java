package core.Percy;

import core.driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class percy {

    // A instância deve ser da SUA classe utilitária, não do Percy oficial
    private static percy instance;

    // Percy oficial
    private final io.percy.selenium.Percy percy;

    /**
     * Construtor: recebe o WebDriver e inicializa o Percy oficial.
     */
    public percy(WebDriver driver) {
        this.percy = new io.percy.selenium.Percy(driver);
    }

    /**
     * Inicializa Percy com o WebDriver atual.
     * Esse método deve ser chamado no Hook @Before.
     */
    public static void init() {
        WebDriver driver = DriverFactory.getDriver();
        if (instance == null) {
            instance = new percy(driver); // instancia a SUA classe
        }
    }

    /**
     * Captura um snapshot visual da página atual.
     *
     * @param name Nome da captura (aparece no dashboard do Percy)
     */
    public static void percySnapshot(String name) {
        if (instance != null) {
            instance.percy.snapshot(name); // agora compila
        }
    }
}