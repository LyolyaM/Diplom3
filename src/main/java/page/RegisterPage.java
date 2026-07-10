package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;


public class RegisterPage {
    private final WebDriver driver;

    private final By nameField = By.xpath("(//input[@name='name'])[1]");
    private final By emailField = By.xpath("(//input[@name='name'])[2]");
    private final By passwordField = By.xpath("(//input[@name='Пароль'])[1]");
    private final By registerButton = By.xpath("(//button[contains(text(),'Зарегистрироваться')])[1]");
    private final By errorMessage = By.xpath(".//p[@class='input__error text_type_main-default']");
    private final By loginLink = By.xpath("(//a[contains(text(),'Войти')])[1]");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step("Нажатие кнопки 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
    @Step("Нажатие на 'Войти' на странице регистрации")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Регистрация пользователя: {name}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();

        // Ждём пару секунд, чтобы проверить результат
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
