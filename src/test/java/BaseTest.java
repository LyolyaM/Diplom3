import utils.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;



public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = BrowserFactory.getDriver();
       // driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
