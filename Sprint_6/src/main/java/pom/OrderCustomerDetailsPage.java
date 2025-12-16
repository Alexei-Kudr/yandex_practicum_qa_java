package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class OrderCustomerDetailsPage {
    private WebDriver driver;

    //поле Имя
    private By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    //поле Фамилия
    private By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    //поле Адрес доставки
    private By deliveryAddressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //выпадающее меню станция метро
    private By metroStationDropDown = By.xpath("//input[@placeholder='* Станция метро']");
    //поле телефон
    private By phoneInput = By.xpath("//input[starts-with(@placeholder, '* Телефон')]");
    //кнопка Далее
    private By nextButton = By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and text()='Далее']");

    public OrderCustomerDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы
    public void waitForLoadHeader(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='* Имя']")));
    }

    //заполняем поле с именем
    public void inputFirstName (String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    //заполняем поле с фамилией
    public void inputLastName (String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    //заполняем адрес
    public void inputDeliveryAddress(String deliveryAddress) {
        driver.findElement(deliveryAddressInput).sendKeys(deliveryAddress);
    }

    //выбираю станцию метро
    public void selectMetroStation(String stationName) {

        WebElement metroInput = driver.findElement(metroStationDropDown);
        metroInput.click();
        metroInput.clear();
        metroInput.sendKeys(stationName);
        metroInput.sendKeys(Keys.ARROW_DOWN);
        metroInput.sendKeys(Keys.ENTER);
    }

    //заполняю телефон

    public void inputPhoneNumber(String phoneNumber) {

        WebElement phone = driver.findElement(phoneInput);
        phone.click();
        phone.sendKeys(phoneNumber);
    }

    //нажимаем кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}
