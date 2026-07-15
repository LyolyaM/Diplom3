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
    private final By bunsTab = By.xpath("(//span[contains(text(),'Булки')])[1]");
    private final By saucesTab = By.xpath("(//span[contains(text(),'Соусы')])[1]");
    private final By fillingsTab = By.xpath("(//span[contains(text(),'Начинки')])[1]");

    // локаторы для заголовков разделов (проверка)
    private final By bunsHeader = By.xpath("(//h2[contains(text(),'Булки')])[1]");
    private final By saucesHeader = By.xpath("(//h2[contains(text(),'Соусы')])[1]");
    private final By fillingsHeader = By.xpath("(//h2[contains(text(),'Начинки')])[1]");

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
        waitForAnimation();
    }

    @Step("Нажатие на вкладку «Соусы»")
    public void clickSaucesTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",
                wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab)));
        js.executeScript("arguments[0].click();", driver.findElement(saucesTab));
        waitForAnimation();
    }

    @Step("Нажатие на вкладку «Начинки»")
    public void clickFillingsTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",
                wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab)));
        js.executeScript("arguments[0].click();", driver.findElement(fillingsTab));
        waitForAnimation();
    }

    // МЕТОДЫ ДЛЯ СКРОЛЛИНГА
    @Step("Скролл до заголовка «Булки»")
    public void scrollToBunsHeader() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                wait.until(ExpectedConditions.visibilityOfElementLocated(bunsHeader)));
        waitForAnimation();
    }

    @Step("Скролл до раздела «Соусы»")
    public void scrollToSaucesHeader() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                wait.until(ExpectedConditions.visibilityOfElementLocated(saucesHeader)));
        waitForAnimation();
    }

    @Step("Скролл до раздела «Начинки»")
    public void scrollToFillingsHeader() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader)));
        waitForAnimation();
    }

    //  МЕТОДЫ ДЛЯ ПРОВЕРКИ
    @Step("Проверка, что виден заголовок «Булки»")
    public boolean isBunsHeaderVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка, что виден заголовок «Соусы»")
    public boolean isSaucesHeaderVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(saucesHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка, что виден заголовок «Начинки»")
    public boolean isFillingsHeaderVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //  ВСПОМОГАТЕЛЬНЫЙ МЕТОД
    private void waitForAnimation() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}