package pomTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pom.MainPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageAccordionItemTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = startBrowser(browser);
    }


    @ParameterizedTest
    @MethodSource("testData")
        //передать параметры
    void clickOnAccordionItemExpandsPanelWithText (By  questionHeader, By questionPanel, String expectedText) {

        //переходим на главную страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //создаем объект класса стартовой страницы
        MainPage mainPage = new MainPage(driver);
        //принимаем куки
        mainPage.acceptCookies();

        //вызываем метод и результат записываем в переменную
        //mainPage.clickQuestionHeaderExpandsPanel(questionHeader, questionPanel, expectedText);
        boolean actualIsExpanded = mainPage.isQuestionHeaderExpanded(questionHeader);
        //проверяем что поле свернуто
        assertFalse(actualIsExpanded, "Секция вопроса не должна быть развернута без клика по нему!");
        //вызываю метод для клика по header
        boolean afterClickExpanded = mainPage.clickOnQuestionHeaderExpandsPanel(questionHeader);
        //проверяю что панель раскрылась
        assertTrue(afterClickExpanded, "Панель не раскрылась после клика!");
        //получаю текст
        String actualText = mainPage.getTextFromTheAnswerPanel(questionPanel);
        assertEquals(expectedText, actualText, "Текст не совпадает!");
    }


    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

    //настройки браузера
    private WebDriver startBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            return new ChromeDriver(new ChromeOptions());
        } else if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver(new FirefoxOptions());
        } else {
            throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }

    //тестовые данные
    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(MainPage.priceQuestionHeader, MainPage.priceQuestionPanel,
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of(MainPage.multipleScootersQuestionHeader, MainPage.multipleScootersQuestionPanel,
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of( MainPage.timeQuestionHeader, MainPage.timeQuestionPanel,
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of(MainPage.todayQuestionHeader, MainPage.todayQuestionPanel,
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of(MainPage.todayQuestionHeader, MainPage.todayQuestionPanel,
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of(MainPage.extensionQuestionHeader, MainPage.extensionQuestionPanel,
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of(MainPage.chargerQuestionHeader, MainPage.chargerQuestionPanel,
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of(MainPage.cancelQuestionHeader, MainPage.cancelQuestionPanel,
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of(MainPage.noMoscowQuestionHeader, MainPage.noMoscowQuestionPanel,
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }
}
