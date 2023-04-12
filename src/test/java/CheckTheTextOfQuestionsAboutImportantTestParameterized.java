import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObjects.MainPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@RunWith(Parameterized.class)
public class CheckTheTextOfQuestionsAboutImportantTestParameterized {

    private WebDriver driver;
    private final String index;
    private final String answer;

    public CheckTheTextOfQuestionsAboutImportantTestParameterized(String index, String answer) {
        this.index = index;
        this.answer = answer;

    }

    @Parameters
    public static Object[][] getData () {
        return new Object[][] {
                {"0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"2","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." },
                {"3", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"4", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"5", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "Да, обязательно. Всем самокатов! И Москве, и Московской области." },
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
    public void checkAnswersOnQuestions() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollToTheSectionQuestionsAboutImportant();
        mainPage.checkAnswerToQuestion(index, answer);
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
