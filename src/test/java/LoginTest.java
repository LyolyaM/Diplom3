import page.MainPage;
import page.LoginPage;
import page.RegisterPage;
import page.ForgotPasswordPage;
import utils.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.qameta.allure.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Тесты")
@Feature("Вход")
public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    void prepare() {
        // Открываем главную страницу перед каждым тестом
        driver.get(AppConfig.BASE_URL);
        mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Story("Вход через кнопку «Личный кабинет»")
    @Severity(SeverityLevel.CRITICAL)
    void loginThroughPersonalAccount() {
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        // Берем email и password созданного в BaseTest юзера
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Безопасное ожидание: проверяем, что ушли со страницы авторизации
        boolean isLoginFinished = wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
        assertTrue(isLoginFinished, "Вход не выполнен. Пользователь остался на странице логина.");
    }

    @Test
    @Story("Вход через кнопку «Войти в аккаунт» на главной")
    @Severity(SeverityLevel.CRITICAL)
    void loginThroughMainButton() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        boolean isLoginFinished = wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
        assertTrue(isLoginFinished, "Вход не выполнен. Пользователь остался на странице логина.");
    }

    @Test
    @Story("Вход через кнопку в форме восстановления пароля")
    @Severity(SeverityLevel.CRITICAL)
    void loginThroughForgotPasswordForm() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Ждем, пока URL перестанет содержать и "login", и "forgot"
        boolean isLoginFinished = wait.until(ExpectedConditions.and(
                ExpectedConditions.not(ExpectedConditions.urlContains("login")),
                ExpectedConditions.not(ExpectedConditions.urlContains("forgot"))
        ));
        assertTrue(isLoginFinished, "Вход через форму восстановления не удался.");
    }

    @Test
    @Story("Вход через кнопку в форме регистрации")
    @Severity(SeverityLevel.CRITICAL)
    void loginThroughRegisterForm() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // Ждем, пока URL перестанет содержать и "login", и "register"
        boolean isLoginFinished = wait.until(ExpectedConditions.and(
                ExpectedConditions.not(ExpectedConditions.urlContains("login")),
                ExpectedConditions.not(ExpectedConditions.urlContains("register"))
        ));
        assertTrue(isLoginFinished, "Вход через форму регистрации не удался.");
    }
}