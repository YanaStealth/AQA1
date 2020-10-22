package io.ctdev.tests.signup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class BaseTest
{
    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
       // assertTrue(true);
      //  System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\WEBDrivers");
        System.setProperty("webdriver.chrome.driver", "C://Program Files//WEBDrivers//chromedriver.exe");
        //IdeaProjects\AQA1\.idea
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.quit();
    }
    }
    /*
    System.setProperty("webdriver.chrome.driver", "Program Files/chromedriver");

    WebDriver driver = new ChromeDriver();
    driver.get("https://google.com");
    driver.quit();
    }*/

