package io.ctdev.framework.driver;
import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.listeners.EventListener;
import io.ctdev.framework.listeners.Highlighter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {//
     //private static ThreadLocal<EventFiringWebDriver>  driver = new ThreadLocal<>(); // для EventFiringWebDriver Listener
    // private static WebDriver driver;  //до EventFiringWebDriver
    //// private static ThreadLocal<WebDriver>  driver = new ThreadLocal<>();//до EventFiringWebDriver
     private static ThreadLocal<WebDriver>  driver = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

   static public WebDriver getDriver()  {

        if (driver.get() == null) {                              //если драйвер не проинициализирован, те.==null
            switch (TestConfig.cfg.browser()) { //при его вызове всегда будет возвращаться один и тот же WebDriver который мы создали, не будут создаваться еще instances
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                   // driver = new FirefoxDriver();
                    driver.set(new EventFiringWebDriver(new FirefoxDriver()));//
                    break;
                }
                default: {
                    if (TestConfig.cfg.remote()) {
                        try {
                          //  driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()));
                            driver.set(new RemoteWebDriver(new URL("http://192.168.0.101:4444/wd/hub"), DesiredCapabilities.chrome()));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        WebDriverManager.chromedriver().setup();
                        driver.set(new EventFiringWebDriver(new ChromeDriver()));
                    } //
                   // driver = new ChromeDriver();//до EventFiringWebDriver
                }
            }
            //driver.manage().window().maximize();//до EventFiringWebDriver
           // driver.get().register(new EventListener());//// для EventFiringWebDriver Listener
          //  driver.get().register(new Highlighter());//// для EventFiringWebDriver Listener
            driver.get().manage().window().maximize();//
        }

        //чтобы у нас был солдан только 1 инстанс объекта, а не несколько и потом можно вызвать не тот что нужно
       //return driver;//до EventFiringWebDriver
        return driver.get();//
    }

public static void closeDriver() {
      //  driver.quit();//до EventFiringWebDriver
    //public void closeDriver() {
        if (driver.get() !=null) { //
            driver.get().close();//
            driver.remove();//
        }
    }
}
