package pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //кнопка Заказать верхняя
    private By topOrderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and not (contains(@class, 'Button_Middle__1CSJM'))]");

    //кнопка Заказать в середине
    private By middleOrderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");

    //окно с куками
    //private By cookieBanner = By.className("App_CookieConsent__1yUIN");
    private By cookieAcceptButton = By.id("rcc-confirm-button");

    //Описание выпадающих полей внизу страницы
    //Сколько стоит и как оплатить?
    public static By priceQuestionHeader = By.id("accordion__heading-0");
    public static By priceQuestionPanel = By.id("accordion__panel-0");

    //Хочу несколько самокатов. Так можно?
    public static By multipleScootersQuestionHeader = By.id("accordion__heading-1");
    public static By multipleScootersQuestionPanel = By.id("accordion__panel-1");

    //Как рассчитывается время аренды?
    public static By timeQuestionHeader = By.id("accordion__heading-2");
    public static By timeQuestionPanel = By.id("accordion__panel-2");

    //Можно ли заказать самокат прямо на сегодня?
    public static By todayQuestionHeader = By.id("accordion__heading-3");
    public static By todayQuestionPanel = By.id("accordion__panel-3");

    //Можно ли продлить заказ или вернуть самокат раньше?
    public static By extensionQuestionHeader = By.id("accordion__heading-4");
    public static By extensionQuestionPanel = By.id("accordion__panel-4");

    //Вы привозите зарядку вместе с самокатом?
    public static By chargerQuestionHeader = By.id("accordion__heading-5");
    public static By chargerQuestionPanel = By.id("accordion__panel-5");

    //Можно ли отменить заказ?
    public static By cancelQuestionHeader = By.id("accordion__heading-6");
    public static By cancelQuestionPanel = By.id("accordion__panel-6");

    //Я живу за МКАДом, привезёте?
    public static By noMoscowQuestionHeader = By.id("accordion__heading-7");
    public static By noMoscowQuestionPanel = By.id("accordion__panel-7");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //метод клика по кнопке Заказать
    public void clickOrderButton(String orderButtonPosition) {
        if (orderButtonPosition.equalsIgnoreCase("top")) {
            driver.findElement(topOrderButton).click();
        } else if (orderButtonPosition.equalsIgnoreCase("middle")) {
            driver.findElement(middleOrderButton).click();
        }
    }

    //метод клика по кнопке принять куки
    public void acceptCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));

            // прокручиваем к элементу
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // пробуем обычный клик
            try {
                element.click();
            } catch (ElementClickInterceptedException e) {
                // если элемент перекрыт, кликаем через JS
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }

        } catch (TimeoutException e) {
            // если баннер не появился — ничего не делаем
            System.out.println("Баннер куки не появился, продолжаем тест");
        }
    }
    //метод находим поле с вопросом и кликаем по нему
    public boolean isQuestionHeaderExpanded (By questionLocator) {
        // нахожу секцию с вопросом и создаю элемент
        WebElement questionHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(questionLocator));

        //проскроллить до него
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionHeader);
        //получаю состояние его
        String expanded = questionHeader.getAttribute("aria-expanded");
        return Boolean.parseBoolean(expanded);

    }

    //метод где мы кликнем на вопрос
    public boolean clickOnQuestionHeaderExpandsPanel (By questionLocator) {
        // снова создаю элемент секции с вопросом и ожидаю его

        WebElement questionHeader = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));        //клик
        questionHeader.click();
        //возвращаю значение, раскрылось ли поле
        return Boolean.parseBoolean(questionHeader.getAttribute("aria-expanded"));
    }

    //метод получения текста у раскрывшегося ответа
    public String getTextFromTheAnswerPanel(By answerLocator) {
        //определяю элемент и жду
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        //get text from the element
        return answer.getText();

    }

}
