package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class OrderModalWindow {
    private WebDriver driver;
    //кнопка Да
    private By yesButton = By.xpath("//button[text()='Да']");
    //private By noButton = By.xpath(); возможно эта кнопка вообще не нужна

    public OrderModalWindow(WebDriver driver){
        this.driver = driver;
    }
    //ожидание загрузки окна
    public void waitForLoadModal(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_ModalHeader__3FDaJ")));
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
}
