package main.java.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import main.java.utils.XLSReader;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import main.java.utils.LoggerClass;
import main.java.utils.UtilLib;

/**
 * Created by Nitin D on 2020.
 */
public class BaseTestClass {

    public static AndroidDriver<AndroidElement> driver;
    public static AppiumDriverLocalService appiumService;
    public static String appiumServiceURL;
    public static XLSReader excelDataFile;
    public static AppiumServiceBuilder builder;
    public static DesiredCapabilities cap;

    /**
     * This function will be executed before all test method and classes
     * This will deletes files inside temp directory, delete allure-results folder
     * Load the config properties file
     * Start the appium server
     *
     * @throws Exception
     */
    @BeforeSuite
    public static void init() {

        LoggerClass.println("##########     Executing before suite method     ###########");
        // Check if temp folder is present , if not, create one.
        File file = new File(System.getProperty("user.dir") + File.separator + "temp");
        if (!file.exists()) {
            if (file.mkdirs())
                LoggerClass.println("#########     Temp folder is created     #########");
        } else {
            LoggerClass.println("#########     Temp folder already exist     #########");
        }

        LoggerClass.println("#########     Delete all existing files from temp folder     ##########");
        UtilLib.deleteFiles(System.getProperty("user.dir") + File.separator + "temp");

        LoggerClass.println("#########     Delete all existing allure-results      ##########");
        UtilLib.deleteFiles(System.getProperty("user.dir") + File.separator + "allure-results");

        // This will launch the local appium server(not driver)
        launchLocalAppiumServer();

//        deviceFarm();
    }

    public static void launchLocalAppiumServer() {

        //Build the Appium service
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        //Start the server with the builder
        appiumService = AppiumDriverLocalService.buildService(builder);
        appiumService.start();
        appiumServiceURL = appiumService.getUrl().toString();
        LoggerClass.println("##########     Appium server URL is " + appiumServiceURL + "      ##########");
    }

    public static void localDeviceSetup() throws Exception {

        cap = new DesiredCapabilities();
        LoggerClass.println("###########     Executing before test      ###########");

        File appDir = new File("app");
//        File app = new File(appDir, "Tata CLiQ.apk");
        File app = new File(appDir, "mkp-16-dec.apk");

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2");
        cap.setCapability("platformName", "Android");
        cap.setCapability("autoGrantPermissions", true);
        cap.setCapability("unicodeKeyboard", true);
        cap.setCapability("resetKeyboard", true);
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        cap.setCapability("appPackage", "com.tul.tatacliq");
//        cap.setCapability("appActivity", "com.tul.tatacliq.view.EnvironmentActivity");
//        cap.setCapability("appPackage", "com.android.chrome");
//        cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        driver = new AndroidDriver<AndroidElement>(new URL(appiumServiceURL), cap);

    }

    public static void deviceFarm() {

        try {
            LoggerClass.println("Appium Server Started");

            cap = new DesiredCapabilities();
            cap.setCapability("Capability_Username", "l1.qa@tataunistore.com");
            cap.setCapability("Capability_ApiKey", "g82t5ymgw899dnfygwcwq74d");
//			 cap.setCapability("Capability_Username", "ajeetc@dewsolutions.in");
//			 cap.setCapability("Capability_ApiKey", "sdcgswgzz4hfwd3hmdvkphbv");
            cap.setCapability("Capability_DurationInMinutes", 60);
            cap.setCapability("newCommandTimeout", 600);
            cap.setCapability("launchTimeout", 90000);

            cap.setCapability("Capability_DeviceFullName", "XIAOMI_MiA2_android_8.1.0");
//          cap.setCapability("Capability_DeviceFullName", "XIAOMI_MiA3_android_9.0.0");
//			cap.setCapability("Capability_DeviceFullName","ONEPLUS_7_Android_9.0.0");
//          cap.setCapability("Capability_DeviceFullName","ONEPLUS_5T_android_9.0.0");
//			cap.setCapability("Capability_DeviceFullName","SAMSUNG_GalaxyC9Pro_android_8.0.0");
//          cap.setCapability("Capability_DeviceFullName","LAVA_Z60_android_7.0.0");
//			cap.setCapability("Capability_DeviceFullName", "GOOGLE_Pixel2XL_android_10.0.0");

            cap.setCapability("platformVersion", "9.0.0");
            cap.setCapability("platformName", "Android");
            cap.setCapability("automationName", "uiautomator2");
            cap.setCapability("Capability_ApplicationName", "mkp-16-dec.apk");

            cap.setCapability("appPackage", "com.tul.tatacliq");
            cap.setCapability("appActivity", "com.tul.tatacliq.view.EnvironmentActivity");
            cap.setCapability("resetKeyboard", "true");
            driver = new AndroidDriver(new URL("https://qkm1.qualitykiosk.com/appiumcloud/wd/hub"), cap);

        } catch (Exception e) {
            LoggerClass.println(e.getMessage());
        }
        LoggerClass.println("Application Launched");
    }

    /**
     * This function will be executed after all test method and classes are done
     * This will stop the appium server
     */
    @AfterSuite
    public static void shutDown() {

        LoggerClass.println("###########     Executing after suite method     ##########");
        if (!appiumServiceURL.contains("qkm1.qualitykiosk.com")) {
            String[] command = {"/usr/bin/killall", "-KILL", "node"};
            appiumService.stop();
            try {
                Runtime.getRuntime().exec(command);
                LoggerClass.println("Appium server stopped.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
