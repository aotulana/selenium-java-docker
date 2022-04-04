package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.ExtentReporter;
import web.components.Navigation;

import java.time.Duration;

@Listeners(ExtentReporter.class)

public class ItemDetailsTest {

    private WebDriver driver;
    private Navigation navigation;

    static final String HOMEPAGE_URL = "https://www.amazon.in";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.get(HOMEPAGE_URL);
        navigation = new Navigation(driver);
    }

    @Test
    public void validate_ProductDetailsCanBeRetrieved() {
        navigation.openHamburgerMenu()
                .selectDepartment("TV, Appliances, Electronics")
                .selectCategory("Televisions")
                .filterBy("Brands", "Samsung")
                .sortResultBy("Price: High to Low")
                .selectItemFromResult(2)
                .printAboutItemText();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
