import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Keys;
import page_objects.*;

@RunWith(Parameterized.class)
public class OrderTestParametrized extends BaseTest {

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
    public static String[][] getData() {
        return new String[][]{
                {"Петр", "Петров", "Невский проспект 10", "+79111111111", "Комментарий 1", "Сокольники", "01.04.2023\n", "сутки"},
                {"Иван", "Иванов", "Ленина 67", "89117409387", "Комментарий 2", "Лубянка", "05.05.2023\n", "двое суток"}
        };
    }


    @Test
    public void checkCreateNewOrderWithButtonTopCheckWindowAboutASuccessfulOrder() { //Позитивный кейс по формированию заказа по кнопке "Заказать" в шапке сайта

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
        orderPage.clickNextButton();
        aboutRentPage.setData(data);
        aboutRentPage.chooseRentalDay(day);
        aboutRentPage.chooseColourBlack();
        aboutRentPage.setComment(comment);
        aboutRentPage.clickMakeOrderButton();
        approvalOrderPage.clickApproveButton();
        approvalOrderPage.checkIfOrderComplete();
    }

    @Test
    public void checkCreateNewOrderWithButtonSecondaryCheckWindowAboutASuccessfulOrder() { //Позитивный кейс по формированию заказа по кнопке "Заказать" на странице

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
        orderPage.clickNextButton();
        aboutRentPage.setData(data);
        aboutRentPage.chooseRentalDay(day);
        aboutRentPage.chooseColourGrey();
        aboutRentPage.setComment(comment);
        aboutRentPage.clickMakeOrderButton();
        approvalOrderPage.clickApproveButton();
        approvalOrderPage.checkIfOrderComplete();

    }

}
