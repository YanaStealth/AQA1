package io.ctdev.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty","html:target/htmlreport"}, //можно прикрутить аллюр репорт будет красиво
        monochrome = true,
        tags = "smoke",
        glue = "io.ctdev.cucumber",
        features = "classpath:io/ctdev/features"
)

public class CucumberBaseTest extends AbstractTestNGCucumberTests {
}
