package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
public class OrderConfirmationPopUpPage {
    private WebDriver driver;

    //заголовок об успешном создании заказа
    private By orderConfirmationHeader = By.cssSelector(".Order_ModalHeader__3FDaJ");

    public OrderConfirmationPopUpPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания появления попапа
    public void waitForLoadHeader(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationHeader));
    }

    //метод для получения текста об успешном создании заказа
    public String textInOrderConfirmationHeader() {
        return driver.findElement(orderConfirmationHeader).getText();
    }
}
