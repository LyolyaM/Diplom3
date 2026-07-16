package page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //  набор локаторов
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By eyeIcon = By.xpath("//div[contains(@class, 'input__icon-action')]");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);

    }

    @Step("Нажатие на глазик")
    public void clickEyeIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(eyeIcon)).click();
    }

    @Step("Нажатие кнопки 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажатие на 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Нажатие на 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();

    }

    //  ОДИН метод для входа
    @Step("Вход в систему с email: {email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);

        clickLoginButton();

        //  Ждём, что URL изменится
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
    }
}