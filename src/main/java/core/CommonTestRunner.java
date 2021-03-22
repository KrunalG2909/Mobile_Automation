package main.java.core;

import main.java.utils.LoggerClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nitin D on 2020.
 */
public class CommonTestRunner extends BaseTestClass {

    /**
     * Before method
     *
     * @param method
     */
    @BeforeMethod
    public void beforeMethodSetUp(Method method) {

        LoggerClass.println("##########     Executing the before method     ##########");
        String sTestCaseName = method.getName();
        LoggerClass.println("##########     Testcase Name is " + sTestCaseName + "     ##########");
    }

    /**
     * After method
     *
     * @param results
     * @param method
     * @throws Exception
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethodTearDown(ITestResult results, Method method) {
        LoggerClass.println("######################  Executing the after method ##########################");
        if (results.getStatus() == ITestResult.SUCCESS) {
            LoggerClass.println("##########     Test case status is passed     ##########");
        } else if (results.getStatus() == ITestResult.SKIP) {
            LoggerClass.println("##########     Test case status is skipped     ##########");
        } else {
            LoggerClass.println("##########      Test case status is Failed      ##########");
        }

        long minutes = TimeUnit.MILLISECONDS.toMinutes(results.getEndMillis() - results.getStartMillis());
        long seconds = TimeUnit.MILLISECONDS.toSeconds(results.getEndMillis() - results.getStartMillis());
        LoggerClass.println(
                "##########         Time taken to execute this case in seconds is " + seconds + "      ##########");
        LoggerClass.println(
                "##########         Time taken to execute this case in minutes is " + minutes + "      ##########");
    }
}
