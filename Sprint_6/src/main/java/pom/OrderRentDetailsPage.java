package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class OrderRentDetailsPage {
    private WebDriver driver;

    //календарь выбор даты
    private By datePicker = By.cssSelector(".react-datepicker__input-container input");

    //выпадающее меню со сроком аренды
    private By rentTermDropDown = By.className("Dropdown-placeholder");

    //кнопка Заказать
    private By orderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    public OrderRentDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    //передаем дату в календарь
    public void setDate(String date) {
        WebElement dateInput = driver.findElement(datePicker);
        dateInput.clear();
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);
    }

    //выбираем срок аренды
    public void setRentTerm(String termValue) {
        driver.findElement(rentTermDropDown).click();
        String xpath = String.format("//div[@class='Dropdown-option' and text()='%s']", termValue);// открыть
        driver.findElement(By.xpath(xpath)).click(); // выбрать
    }

    //нажимаем кнопку заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
}
