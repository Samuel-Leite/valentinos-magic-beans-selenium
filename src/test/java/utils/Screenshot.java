package utils;

import core.driver.DriverFactory;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Log4j2
public class Screenshot {

    public static void takeScreenshot(Scenario scenario) {
        try {
            // Anexa ao relatório
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");

            // Salva em disco
            File file = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.FILE);
            Path dest = Paths.get("target/screenshots",
                    scenario.getName() + "-" + System.currentTimeMillis() + ".png");
            Files.createDirectories(dest.getParent());
            Files.copy(file.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);

            log.debug("Screenshot salvo em '{}'", dest);
        } catch (Exception e) {
            log.error("Falha ao capturar screenshot: '{}'", e.getMessage());
        }
    }
}
