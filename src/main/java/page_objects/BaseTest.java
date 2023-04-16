package page_objects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static page_objects.MainPage.PAGE_URL;

public class BaseTest {

    public WebDriver driver;

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
        driver.get(PAGE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
