package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MainPage { //Главная страница сайта

    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By BUTTON_TO_ORDER_IN_HEAD = By.xpath(".//button[text()='Заказать']");
    private static final By BUTTON_TO_ORDER_IN_PAGE = By.xpath(".//button[text()='Заказать' and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By QUESTIONS = By.xpath(".//div[text()='Вопросы о важном']");


    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }

    public void clickCreateOrderButtonInHead() {
        driver.findElement(BUTTON_TO_ORDER_IN_HEAD).click();
    }

    public void clickCreateOrderButtonInPage() {
        driver.findElement(BUTTON_TO_ORDER_IN_PAGE).click();
    }

    public void scrollToTheSectionQuestionsAboutImportant() {
        WebElement element = driver.findElement(QUESTIONS);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToTheButtonToOrderInPage() {
        WebElement element = driver.findElement(BUTTON_TO_ORDER_IN_PAGE);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void checkAnswerToQuestion(String index, String expectedAnswer) {
        By question = By.xpath(".//div[@id='accordion__heading-" + index +"']"); //внесла переменные в метод, не знаю верно или нет, но по-другому не получилось
        By answer = By.xpath(".//div[@id='accordion__panel-" + index + "']/p");
        driver.findElement(question).click();
        String actualAnswer = driver.findElement(answer).getText();
        if (!expectedAnswer.equals(actualAnswer)) {
            try {
                throw new Exception(String.format("Текст ответа не совпадает с ожидаемым:\nОжидаем: %s\nАктуальное: %s",expectedAnswer,actualAnswer));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

