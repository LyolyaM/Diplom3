import page.MainPage;
import page.LoginPage;
import page.RegisterPage;
import page.ForgotPasswordPage;
import utils.AppConfig;
import utils.TestData;
import org.junit.jupiter.api.Test;
import io.qameta.allure.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Тесты")
@Feature("Вход")
public class LoginTest extends BaseTest {

    @Test
    @Story("Вход через кнопку «Личный кабинет»")
    @Severity(SeverityLevel.CRITICAL)
    void loginThroughPersonalAccount() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.TEST_EMAIL, TestData.TEST_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(!currentUrl.contains("login"), " Вход не выполнен. URL: " + currentUrl);
    }

    @Test
    @Story("Вход через кнопку «Войти в аккаунт» на главной")
    @Severity(SeverityLevel.CRITICAL)
    void loginThroughMainButton() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.TEST_EMAIL, TestData.TEST_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(!currentUrl.contains("login"), " Вход не выполнен. URL: " + currentUrl);
    }

    @Test
    @Story("Вход через кнопку в форме восстановления пароля")
    @Severity(SeverityLevel.CRITICAL)
    void loginThroughForgotPasswordForm() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink();

        loginPage.login(TestData.TEST_EMAIL, TestData.TEST_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(!currentUrl.contains("login") && !currentUrl.contains("forgot"),
                " Вход не выполнен. URL: " + currentUrl);
    }

    @Test
    @Story("Вход через кнопку в форме регистрации")
    @Severity(SeverityLevel.CRITICAL)
    void loginThroughRegisterForm() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(TestData.TEST_EMAIL, TestData.TEST_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(!currentUrl.contains("login") && !currentUrl.contains("register"),
                " Вход не выполнен. URL: " + currentUrl);
    }
}
