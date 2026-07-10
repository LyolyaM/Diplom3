import page.MainPage;
import utils.AppConfig;
import org.junit.jupiter.api.Test;
import io.qameta.allure.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Тесты")
@Feature("Конструктор")

public class ConstructorTest extends BaseTest{

    @Test
    @Story("Переход к разделу «Булки»")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка перехода к разделу «Булки» в конструкторе")
    public void shouldSwitchToBunsSection() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);

        // 1. Нажать на Соусы (чтобы уйти с Булок)
        mainPage.clickSaucesTab();

        // 2. Нажать на Булки
        mainPage.clickBunsTab();

        // 3. Проверить, что виден заголовок Булки
        assertTrue(mainPage.isBunsHeaderVisible(), "Заголовок «Булки» не виден");
    }

    @Test
    @Story("Переход к разделу «Соусы»")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка перехода к разделу «Соусы» в конструкторе")
    public void shouldSwitchToSaucesSection() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);

        // 1. Нажать на Соусы
        mainPage.clickSaucesTab();

        // 2. Проверить, что виден заголовок Соусы
        assertTrue(mainPage.isSaucesHeaderVisible(), " Заголовок «Соусы» не виден");
    }

    @Test
    @Story("Переход к разделу «Начинки»")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка перехода к разделу «Начинки» в конструкторе")
    public void shouldSwitchToFillingsSection() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);

        // 1. Нажать на Начинки
        mainPage.clickFillingsTab();

        // 2. Проверить, что виден заголовок Начинки
        assertTrue(mainPage.isFillingsHeaderVisible(), " Заголовок «Начинки» не виден");
    }

    @Test
    @Story("Скролл до раздела «Соусы»")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка, что скроллом можно дойти до раздела «Соусы»")
    public void shouldScrollToSaucesSection() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);

        // Скроллим до заголовка Соусы
        mainPage.scrollToSaucesHeader();

        // Проверяем, что заголовок виден
        assertTrue(mainPage.isSaucesHeaderVisible(), " Заголовок «Соусы» не виден после скролла");
    }

    @Test
    @Story("Скролл до раздела «Начинки»")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка, что скроллом можно дойти до раздела «Начинки»")
    public void shouldScrollToFillingsSection() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);

        // Скроллим до заголовка Начинки
        mainPage.scrollToFillingsHeader();

        // Проверяем, что заголовок виден
        assertTrue(mainPage.isFillingsHeaderVisible(), " Заголовок «Начинки» не виден после скролла");
    }

    @Test
    @Story("Скролл до раздела «Булки»")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка, что скроллом можно дойти до раздела «Булки»")
    public void shouldScrollToBunsSection() {
        driver.get(AppConfig.BASE_URL);
        MainPage mainPage = new MainPage(driver);

        // 1. Сначала скроллим вниз (чтобы уйти от начала)
        mainPage.scrollToSaucesHeader();

        // 2. Скроллим обратно к заголовку Булки
        mainPage.scrollToBunsHeader();

        // 3. Проверяем, что заголовок виден
        assertTrue(mainPage.isBunsHeaderVisible(), " Заголовок «Булки» не виден после скролла");
    }
}



