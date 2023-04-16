package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static page_objects.MainPage.PAGE_URL;

public class ApprovalOrderPage { //Окно с подтверждением заказа
    private static final By BUTTON_CONFIRM_ORDER = By.xpath(".//button[text()='Да']");
    private static final By SUCCESS_HEADER = By.xpath(".//div[text()='Заказ оформлен']");

    public void clickApproveButton() {
        driver.findElement(BUTTON_CONFIRM_ORDER).click();
    }

    private final WebDriver driver;
    public ApprovalOrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get(PAGE_URL);
    }

    public void checkIfOrderComplete() {
        if (!driver.findElement(SUCCESS_HEADER).isDisplayed()) {
            try {
                throw new Exception("Заголовок \"Заказ оформлен\" не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
