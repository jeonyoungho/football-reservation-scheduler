package com.example.footballreservationscheduler.util;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by jyh1004 on 2023-04-19
 */

@UtilityClass
public class WebDriverUtil {

    public static WebDriver getChromeDriver() {
        Environment env = BeanUtil.getBean(Environment.class);
        String webdriverId = env.getProperty("webdriver.id");
        String webdriverPath = env.getProperty("webdriver.path");

        assert webdriverId != null;
        assert webdriverPath != null;
        System.setProperty(webdriverId, webdriverPath);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.setCapability("ignoreProtectedModeSettings", true);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return driver;
    }

    // 모든 탭 닫기
    public static void quit(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.quit();
        }
    }

    // 현재 탭 닫기
    public static void close(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.close();
        }
    }
}
