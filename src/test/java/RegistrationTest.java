import page.MainPage;
import page.LoginPage;
import page.RegisterPage;
import models.User;
import utils.TestDataGenerator;
import utils.UserApiClient;
import utils.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.qameta.allure.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Тесты")
@Feature("Регистрация")
public class RegistrationTest extends BaseTest {


    private MainPage mainPage;
    private WebDriverWait wait;
    private User registeredUser; // Сюда сохраняем пользователя для последующего удаления

    @BeforeEach
    void prepare() {
        driver.get(AppConfig.BASE_URL);
        mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Story("Успешная регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка успешной регистрации с валидными данными")
    void successfulRegistration() {
        // Генерируем случайного валидного пользователя
        registeredUser = TestDataGenerator.generateValidUser();

        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(registeredUser.getName(), registeredUser.getEmail(), registeredUser.getPassword());

        // Ждем, пока URL изменится на страницу логина
        boolean isRedirected = wait.until(ExpectedConditions.urlContains(AppConfig.LOGIN_ENDPOINT));
        assertTrue(isRedirected, "Регистрация не удалась. Пользователь не перенаправлен на страницу входа.");
    }

    @Test
    @Story("Ошибка при коротком пароле")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка ошибки при пароле меньше 6 символов")
    void unsuccessfulRegistrationWithShortPassword() {
        // Здесь пользователь НЕ зарегистрируется, поэтому удалять его в конце не придется
        User shortPasswordUser = TestDataGenerator.generateUserWithShortPassword();

        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(shortPasswordUser.getName(), shortPasswordUser.getEmail(), shortPasswordUser.getPassword());

        // Проверяем ошибку
        String error = registerPage.getErrorMessage();
        assertEquals("Некорректный пароль", error, "Текст ошибки не совпадает с ожидаемым");
    }

    @AfterEach
    void deleteRegisteredUser() {
        // Если тест успешной регистрации прошел и объект пользователя был создан
        if (registeredUser != null) {
            System.out.println("Запуск очистки данных после теста регистрации...");
            // Чтобы удалить пользователя через API, нам нужен его токен.
            // Быстро логинимся под ним через API-клиент, чтобы этот токен получить.
            String token = UserApiClient.registerUser(registeredUser);
            // Удаляем пользователя
            UserApiClient.deleteUser(token);
        }
    }
}