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

    // Только ОДИН набор локаторов
    private final By emailField = By.xpath("(//input[@name='name'])[1]");
    private final By passwordField = By.xpath("(//input[@name='Пароль'])[1]");
    private final By eyeIcon = By.xpath("//div[@class='input__icon input__icon-action']//*[name()='svg']");
    private final By loginButton = By.xpath("(//button[contains(text(),'Войти')])[1]");
    private final By registerLink = By.xpath("(//a[contains(text(),'Зарегистрироваться')])[1]");
    private final By forgotPasswordLink = By.xpath("(//a[contains(text(),'Восстановить пароль')])[1]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажатие на глазик")
    public void clickEyeIcon() {
        driver.findElement(eyeIcon).click();
    }

    @Step("Нажатие кнопки 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на 'Зарегистрироваться'")
    public void clickRegisterLink() {
        org.openqa.selenium.WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(registerLink)
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Нажатие на 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    //  ОДИН метод для входа
    @Step("Вход в систему с email: {email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickEyeIcon();
        clickLoginButton();

        //  Ждём, что URL изменится
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
    }
}