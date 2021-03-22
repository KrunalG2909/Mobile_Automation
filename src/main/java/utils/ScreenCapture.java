package main.java.utils;

import io.qameta.allure.Attachment;
import main.java.core.BaseTestClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;


/**
 * Created by Nitin D on 2020.
 */
public class ScreenCapture {

    @Attachment(value = "Screen Shot", type = "image/png")
    public byte[] captureScreenShot() {
        return ((TakesScreenshot) BaseTestClass.driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Capture screenshot of page wherever this method be called and will save screenshot with testcase
     * name and time stamp
     *
     * @param sTestCaseName
     * @return
     * @throws Exception
     */
    public String captureScreenShot(String sTestCaseName) throws Exception {

        LoggerClass.println("##########     Taking failure screen shot for Testcase: " +
                sTestCaseName + "     ############");
        captureScreenShot();
        File srcFile = ((TakesScreenshot) BaseTestClass.driver).getScreenshotAs(OutputType.FILE);
        String sTimeStamp = UtilLib.getTimeStamp();
        String destFile = System.getProperty("user.dir") + File.separator + "temp" + File.separator + sTestCaseName
                + "_" + sTimeStamp + ".png";
        FileUtils.copyFile(srcFile, new File(destFile));
        LoggerClass.println("##########     Screen shot is captured at " + destFile + "     ############");
        return destFile;
    }

}
