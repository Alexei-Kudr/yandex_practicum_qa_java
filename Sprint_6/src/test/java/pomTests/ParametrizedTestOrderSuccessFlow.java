package pomTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pom.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class ParametrizedTestOrderSuccessFlow {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = startBrowser(browser);
    }

    @ParameterizedTest
    @MethodSource("testData")
    void checkSuccessOrderCreation(String orderButtonPosition,
                                   String firstName, String lastName,
                                   String deliveryAddress, String metroStation,
                                   String phoneNumber, String date, String termValue) {

        //переходим на главную страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //создаем объект класса стартовой страницы
        MainPage mainPage = new MainPage(driver);
        //принимаем куки
        mainPage.acceptCookies();
        //нажимаем на кнопку Заказать
        mainPage.clickOrderButton(orderButtonPosition);

        //создаем объект класса страницы с данными заказчика
        OrderCustomerDetailsPage orderCustomerDetailsPage = new OrderCustomerDetailsPage(driver);
        //дожидаемся загрузки страницы
        orderCustomerDetailsPage.waitForLoadHeader();
        //передаем данные заказчика
        //имя
        orderCustomerDetailsPage.inputFirstName(firstName);
        //фамилия
        orderCustomerDetailsPage.inputLastName(lastName);
        //адрес доставки
        orderCustomerDetailsPage.inputDeliveryAddress(deliveryAddress);
        //номер телефона
        orderCustomerDetailsPage.inputPhoneNumber(phoneNumber);
        //станцию метро
        orderCustomerDetailsPage.selectMetroStation(metroStation);
        //нажимаем на кнопку Далее
        orderCustomerDetailsPage.clickNextButton();

        //создаем объект класса страницы с данными аренды
        OrderRentDetailsPage orderRentDetailsPage = new OrderRentDetailsPage(driver);
        //выбираем дату в календаре
        orderRentDetailsPage.setDate(date);
        //устанавливаем срок аренды
        orderRentDetailsPage.setRentTerm(termValue);
        //нажимаем на кнопку Заказать
        orderRentDetailsPage.clickOrderButton();

        //создаем объект класса Модальное окно
        OrderModalWindow orderModalWindow = new OrderModalWindow(driver);
        //кликаем на кнопку Да
        orderModalWindow.clickYesButton();

        //создаем объект класса страницы Подтверждение заказа
        OrderConfirmationPopUpPage orderConfirmationPopUpPage = new OrderConfirmationPopUpPage(driver);
        //получаю текст в заголовке
        String actualOrderConfirmationText = orderConfirmationPopUpPage.textInOrderConfirmationHeader();
        //сверка текста должна быть здесь
        assertTrue(actualOrderConfirmationText.contains("Заказ оформлен"), "В попапе нет фразы Заказ оформлен");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private WebDriver startBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriver(new ChromeOptions());
        } else if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver(new FirefoxOptions());
        } else {
            throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("top",
                        " Хагрид", "Иванович", "Косой переулок", "Лубянка",
                        "79268288888", "31.12.2001", "сутки"),
                Arguments.of("middle",
                        "Гарри", "Поттер", "Тисовая улица", "Театральная",
                        "79857876440", "01.01.2002", "трое суток")
        );
    }
}
