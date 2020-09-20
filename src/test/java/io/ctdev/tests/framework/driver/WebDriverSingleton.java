package io.ctdev.tests.framework.driver;
import io.ctdev.tests.framework.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
    private static WebDriver driver;
    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {                              //если драйвер не проинициализирован, те.==null
            switch (TestConfig.cfg.browser()) { //при его вызове всегда будет возвращаться один и тот же WebDriver который мы создали, не будут создаваться еще instances
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }

        //чтобы у нас был солдан только 1 инстанс объекта, а не несколько и потом можно вызвать не тот что нужно
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
    }
}
