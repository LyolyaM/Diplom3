package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        System.out.println(" Выбран браузер: " + browser);

        switch (browser) {
            case "chrome":
                return createChromeDriver();
            case "yandex":
                return createYandexDriver();
            default:
                throw new IllegalArgumentException("Браузер не поддерживается: " + browser);
        }
    }

    private static WebDriver createChromeDriver() {
        System.out.println(" Запуск Chrome...");
        // Очищаем кэш перед установкой
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        System.out.println("Насильно ставим ChromeDriver версии 148...");
        // Говорим  скачать именно 148-ю версию драйвера Google
        WebDriverManager.chromedriver().browserVersion("148").setup();

        ChromeOptions options = new ChromeOptions();

        // Путь к  Яндекс.Браузеру
        options.setBinary("C:/Program Files/Yandex/YandexBrowser/Application/browser.exe");

        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        // Просто создаем и возвращаем драйвер, не дергая окно вручную
        return new ChromeDriver(options);
    }
}