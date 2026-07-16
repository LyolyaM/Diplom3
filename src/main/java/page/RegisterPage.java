package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait; // Поле для ожидания

    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By errorMessage = By.xpath(".//p[@class='input__error text_type_main-default']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инициализируем ожидание
}
    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        // Ждем, пока поле станет доступно для клика, и вводим текст
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField)).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
    }

    @Step("Нажатие кнопки 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    @Step("Нажатие на 'Войти' на странице регистрации")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Регистрация пользователя: {name}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();


           }
}
