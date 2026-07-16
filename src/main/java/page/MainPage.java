package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By personalAccountButton = By.xpath("(//p[contains(text(),'Личный Кабинет')])[1]");
    private final By loginButton = By.xpath("(//button[contains(text(),'Войти в аккаунт')])[1]");

    // локаторы для вкладок конструктора
    private final By bunsTab = By.xpath("//div[span[text()='Булки']]");
    private final By saucesTab = By.xpath("//div[span[text()='Соусы']]");
    private final By fillingsTab = By.xpath("//div[span[text()='Начинки']]");

    // локаторы для заголовков разделов (проверка)
    private final By bunsHeader = By.xpath("//h2[contains(text(),'Булки')]");
    private final By saucesHeader = By.xpath("//h2[contains(text(),'Соусы')]");
    private final By fillingsHeader = By.xpath("//h2[contains(text(),'Начинки')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажатие на 'Личный кабинет'")
    public void clickPersonalAccount() {
        // Сначала ждем появления элемента в DOM (presenceOfElementLocated)
        org.openqa.selenium.WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(personalAccountButton)
        );

        // Кликаем по нему напрямую через JavaScript bypass
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    @Step("Нажатие на 'Войти в аккаунт'")
    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));  // для Яндекса
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    //  МЕТОДЫ ДЛЯ КОНСТРУКТОРА
    @Step("Нажатие на вкладку «Булки»")
    public void clickBunsTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //  Прокручиваем к вкладке
        js.executeScript("arguments[0].scrollIntoView(true);",
                wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab)));
        //  Кликаем через JavaScript
        js.executeScript("arguments[0].click();", driver.findElement(bunsTab));

    }

    @Step("Нажатие на вкладку «Соусы»")
    public void clickSaucesTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",
                wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab)));
        js.executeScript("arguments[0].click();", driver.findElement(saucesTab));

    }

    @Step("Нажатие на вкладку «Начинки»")
    public void clickFillingsTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",
                wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab)));
        js.executeScript("arguments[0].click();", driver.findElement(fillingsTab));

    }

    // МЕТОДЫ ДЛЯ СКРОЛЛИНГА
    @Step("Скролл до заголовка «Булки»")
    public void scrollToBunsHeader() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'start'});",
                wait.until(ExpectedConditions.visibilityOfElementLocated(bunsHeader)));

    }

    @Step("Скролл до раздела «Соусы»")
    public void scrollToSaucesHeader() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'start'});",
                wait.until(ExpectedConditions.visibilityOfElementLocated(saucesHeader)));

    }

    @Step("Скролл до раздела «Начинки»")
    public void scrollToFillingsHeader() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'start'});",
                wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader)));

    }
    // МЕТОДЫ ДЛЯ ПРОВЕРКИ АКТИВНОСТИ ВКЛАДОК
    @Step("Проверка, что вкладка «Булки» активна")
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверка, что вкладка «Соусы» активна")
    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверка, что вкладка «Начинки» активна")
    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }

    // Общий приватный метод для проверки наличия класса активности у таба
    private boolean isTabActive(By tabLocator) {
        try {

            return wait.until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
        } catch (Exception e) {
            return false;
        }
    }

    }
