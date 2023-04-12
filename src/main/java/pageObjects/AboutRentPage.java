package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutRentPage { //Страница "О заказе"

    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    private static final By DATA_DATA_PICKER = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By RENTAL_DAY_SELECT = By.xpath(".//div[@class='Dropdown-placeholder']");
    private static final By COLOUR_CHECKBOX_BLACK = By.xpath(".//input[@id='black']");
    private static final By COLOUR_CHECKBOX_GREY = By.xpath(".//input[@id='grey']");
    private static final By BUTTON_MAKE_ORDER = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By COMMENT_INPUT_FIELD = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final WebDriver driver;
    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get(PAGE_URL);
    }

    public void chooseRentalDay(String day) {
        driver.findElement(RENTAL_DAY_SELECT).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']//*[text()='"+day+"']")).click();
    }

    public void clickMakeOrderButton() {
        driver.findElement(BUTTON_MAKE_ORDER).click();
    }

    public void chooseColourBlack() {
        driver.findElement(COLOUR_CHECKBOX_BLACK).click();
    }

    public void chooseColourGrey() {
        driver.findElement(COLOUR_CHECKBOX_GREY).click();
    }

    public void setComment(String comment) {
        driver.findElement(COMMENT_INPUT_FIELD).sendKeys(comment);
    }

    public void setData(String data) {
        driver.findElement(DATA_DATA_PICKER).sendKeys(data);
    }

}
