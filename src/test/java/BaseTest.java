import models.User;
import utils.BrowserFactory;
import utils.UserApiClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.TestDataGenerator;




public class BaseTest {
    protected WebDriver driver;

    // Доступны во всех тестовых классах
    protected User testUser;
    protected String userToken;

    @BeforeEach
    void setUp() {
        // 1. Генерируем случайные данные
        testUser = TestDataGenerator.generateValidUser();

        // 2. Мгновенно регистрируем пользователя через бэкенд и запоминаем токен
        userToken = UserApiClient.registerUser(testUser);
        // 3. Запускаем браузер
        driver = BrowserFactory.getDriver();
       // driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        // 2. Чищу за собой базу данных
        if (userToken != null) {
            UserApiClient.deleteUser(userToken);
        }
    }

}
