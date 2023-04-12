import org.junit.runners.Parameterized.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObjects.AboutRentPage;
import pageObjects.ApprovalOrderPage;
import pageObjects.MainPage;
import pageObjects.OrderPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@RunWith(Parameterized.class)
public class OrderTestParametrized {

    private WebDriver driver;
    private final String name;
    private final String lastname;
    private final String address;
    private final String numberPhone;
    private final String comment;
    private final String station;
    private final String data;
    private final String day;

    public OrderTestParametrized(String name, String lastname, String address, String numberPhone, String comment, String station, String data, String day) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.numberPhone = numberPhone;
        this.comment = comment;
        this.station = station;
        this.data = data;
        this.day = day;
    }

    @Parameters
    public static String[][] getData () {
        return new String[][] {
                {"Петр", "Петров", "Невский проспект 10", "+79111111111", "Комментарий 1", "Сокольники", "01.04.2023\n", "сутки"},
                {"Иван", "Иванов", "Ленина 67", "89117409387", "Комментарий 2", "Лубянка", "05.05.2023\n", "двое суток"}
        };
    }

    @Before
    public void setUp() {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String webDriver = prop.getProperty("webdriver");
        if (webDriver.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
        } else if (webDriver.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkCreateNewOrder_withButtonTop_checkWindowAboutASuccessfulOrder() { //Позитивный кейс по формированию заказа по кнопке "Заказать" в шапке сайта

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        ApprovalOrderPage approvalOrderPage = new ApprovalOrderPage(driver);
        AboutRentPage aboutRentPage = new AboutRentPage(driver);

        mainPage.open();
        mainPage.clickCreateOrderButtonInHead();
        orderPage.setName(name);
        orderPage.setLastname(lastname);
        orderPage.setAddress(address);
        orderPage.setNumberPhone(numberPhone);
        orderPage.chooseMetroStation(station);
        OrderPage.wait(1);
        orderPage.clickNextButton();
        OrderPage.wait(3);
        aboutRentPage.setData(data);
        aboutRentPage.chooseRentalDay(day);
        aboutRentPage.chooseColourBlack();
        aboutRentPage.setComment(comment);
        OrderPage.wait(5);
        aboutRentPage.clickMakeOrderButton();
        OrderPage.wait(2);
        approvalOrderPage.clickApproveButton();
        OrderPage.wait(2);
        approvalOrderPage.checkIfOrderComplete();
    }

    @Test
    public void checkCreateNewOrder_withButtonSecondary_checkWindowAboutASuccessfulOrder() { //Позитивный кейс по формированию заказа по кнопке "Заказать" на странице

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        ApprovalOrderPage approvalOrderPage = new ApprovalOrderPage(driver);
        AboutRentPage aboutRentPage = new AboutRentPage(driver);

        mainPage.open();
        mainPage.scrollToTheButtonToOrderInPage();
        mainPage.clickCreateOrderButtonInPage();
        orderPage.setName(name);
        orderPage.setLastname(lastname);
        orderPage.setAddress(address);
        orderPage.setNumberPhone(numberPhone);
        orderPage.chooseMetroStation(station);
        OrderPage.wait(1);
        orderPage.clickNextButton();
        OrderPage.wait(3);
        aboutRentPage.setData(data);
        aboutRentPage.chooseRentalDay(day);
        aboutRentPage.chooseColourGrey();
        aboutRentPage.setComment(comment);
        aboutRentPage.clickMakeOrderButton();
        OrderPage.wait(2);
        approvalOrderPage.clickApproveButton();
        OrderPage.wait(2);
        approvalOrderPage.checkIfOrderComplete();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
