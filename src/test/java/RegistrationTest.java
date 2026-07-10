import page.MainPage;
import page.LoginPage;
import page.RegisterPage;
import models.User;
import utils.TestDataGenerator;
import utils.AppConfig;
import org.junit.jupiter.api.Test;
import io.qameta.allure.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("UI Тесты")
@Feature("Регистрация")
public class RegistrationTest extends BaseTest {


    @Test
    @Story("Успешная регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка успешной регистрации с валидными данными")
    void successfulRegistration() {
        User user = TestDataGenerator.generateValidUser();

        // Регистрация
        driver.get(AppConfig.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        // Проверка: перешли на страницу входа
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(AppConfig.LOGIN_ENDPOINT),
                "Регистрация не удалась. Текущий URL: " + currentUrl);

    }


    @Test
    @Story("Ошибка при коротком пароле")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка ошибки при пароле меньше 6 символов")
    void unsuccessfulRegistrationWithShortPassword() {
        User user = TestDataGenerator.generateUserWithShortPassword();

        // Регистрация с коротким паролем
        driver.get(AppConfig.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        // Проверка: появилась ошибка
        String error = registerPage.getErrorMessage();
        assertNotNull(error, " Сообщение об ошибке не появилось");
        assertTrue(error.contains("Некорректный пароль"),
                " Текст ошибки неверный. Ожидается: 'Некорректный пароль'. Фактически: " + error);

    }
}

