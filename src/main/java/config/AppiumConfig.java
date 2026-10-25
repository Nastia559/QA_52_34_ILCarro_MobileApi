package config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.PropertiesReader;

import java.net.MalformedURLException;
import java.net.URL;
import static utils.PropertiesReader.*;

public class AppiumConfig {
    public static AppiumDriver createAppiumDriver(){
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(getProperty("mobile.properties", "platformName"))
                .setAutomationName(getProperty("mobile.properties", "automationName"))
                .setDeviceName(getProperty("mobile.properties", "deviceName"))
                .setAppPackage(getProperty("mobile.properties", "appPackage"))
                .setAppActivity(getProperty("mobile.properties", "appActivity"));

        AppiumDriver appiumDriver = null;
        try {
            appiumDriver = new AppiumDriver(new URL(PropertiesReader.getProperty(
                    "mobile.properties", "appiumUrl")),options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return appiumDriver;
    }
}
