package pages;

import attributes.HomeAttributes;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Log4j2
public class HomePage extends HomeAttributes {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void validarLoginSucesso() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.text-sm.font-semibold")
        ));

        // Log que encontrou o elemento
        log.info("Elemento encontrado: " + toast.getText());

        Assert.assertEquals(toast.getText(), "Login Successful",
                "Falha: esperado 'Login Successful' mas recebido '" + toast.getText() + "'");
    }
}