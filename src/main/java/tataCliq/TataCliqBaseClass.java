package main.java.tataCliq;

import java.io.File;

import main.java.utils.XLSReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import main.java.core.CommonTestRunner;
import main.java.utils.LoggerClass;

/**
 * Created by Nitin D on 2020.
 */
public class TataCliqBaseClass extends CommonTestRunner {

    public static int iRowNumber;

    @BeforeMethod
    public static void beforeTestSetUp() throws Exception {

        localDeviceSetup();
//        deviceFarm();

//        // Data excel load
        excelDataFile = new XLSReader(
                System.getProperty("user.dir") + File.separator + "src/test/resources/Data.xlsx");
        LoggerClass.println("Excel data file loaded successfully");
    }

    @AfterMethod
    public void quitDriver() {
        if (!appiumServiceURL.contains("qkm1.qualitykiosk.com")) {
            driver.quit();
            LoggerClass.println("----> Driver quit");
        }
    }
}
