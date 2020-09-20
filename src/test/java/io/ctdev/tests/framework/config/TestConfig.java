package io.ctdev.tests.framework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
@Config.Sources({"classpath:testng.properties" })

public interface TestConfig extends Config{

    TestConfig cfg = ConfigFactory.create(TestConfig.class); // инициализирует

    @DefaultValue("chrome")
    String browser();
}
