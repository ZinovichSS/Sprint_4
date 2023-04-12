package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class OrderPage { //Страница оформления нового заказа
    private static final By NAME_INPUT_FIELD = By.xpath(".//input[@placeholder='* Имя']");
    private static final By LASTNAME_INPUT_FIELD = By.xpath(".//input[@placeholder='* Фамилия']");
    private static final By ADDRESS_INPUT_FIELD = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By NUMBER_PHONE_INPUT_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By METRO_STATION_SELECT = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By BUTTON_NEXT = By.xpath(".//button[text()='Далее']");


    private final WebDriver driver;
    public OrderPage(WebDriver driver) {

        this.driver = driver;
    }


    public void setName(String name) {

        driver.findElement(NAME_INPUT_FIELD).sendKeys(name);
    }

    public void setLastname(String lastname) {

        driver.findElement(LASTNAME_INPUT_FIELD).sendKeys(lastname);
    }

    public void setAddress(String address) {

        driver.findElement(ADDRESS_INPUT_FIELD).sendKeys(address);
    }

    public void setNumberPhone(String numberPhone) {
        driver.findElement(NUMBER_PHONE_INPUT_FIELD).sendKeys(numberPhone);
    }

    public void clickNextButton() {
        driver.findElement(BUTTON_NEXT).click();
    }

    public void chooseMetroStation(String station) {
        driver.findElement(METRO_STATION_SELECT).click();
        driver.findElement(By.xpath(".//div[@class='select-search__select']//*[text()='"+station+"']")).click();
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}



