package main.java.core;

import main.java.utils.LoggerClass;
import main.java.utils.ScreenCapture;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitin D on 2020.
 */
public class TestResultListener implements ITestListener, IReporter {

    int nbExceptions = 0;

    List<ITestNGMethod> pCount = new ArrayList<ITestNGMethod>();
    List<ITestNGMethod> sCount = new ArrayList<ITestNGMethod>();
    List<ITestNGMethod> fCount = new ArrayList<ITestNGMethod>();

    static ScreenCapture scrLib = new ScreenCapture();

    public void onTestStart(ITestResult result) {

        LoggerClass.println("<<<<<<<<<<<  Test Started  " + result.getName() + "  >>>>>>>>>>>>>");
    }

    public void onTestSuccess(ITestResult result) {

        LoggerClass.println("<<<<<<<<<<<  Test passed " + result.getName() + "  >>>>>>>>>>>>>");

        // add passed testcases to list
        pCount.add(result.getMethod());
    }

    public void onTestFailure(ITestResult result) {

        LoggerClass.println("<<<<<<<<<<<  Test failed  " + result.getName() + "  >>>>>>>>>>>>>");
        try {
            scrLib.captureScreenShot(result.getName());
        } catch (Exception e) {

            e.printStackTrace();
        }
        // add failed testcases to list
        fCount.add(result.getMethod());
    }

    public void onTestSkipped(ITestResult result) {

        LoggerClass.println("<<<<<<<<<<<  Test skipped  " + result.getName() + "  >>>>>>>>>>>>>");

        // add skipped testcases to list
        sCount.add(result.getMethod());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //  Auto-generated method stub
    }

    public void onStart(ITestContext context) {

        LoggerClass.println("<<<<<<<<<<<  Start Of Execution(TEST) " + context.getName() + "  >>>>>>>>>>>>>");
    }

    public void onFinish(ITestContext context) {

        LoggerClass.println("<<<<<<<<<<<  End Of Execution(TEST) " + context.getName() + "  >>>>>>>>>>>>>");
        LoggerClass.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>");
        LoggerClass.println("");
        LoggerClass.println("");
        LoggerClass.println("<<<<<<<<<<<  Total No. of testcases passed are::" + pCount.size() + "  >>>>>>>>>>>>>");
        LoggerClass.println("<<<<<<<<<<<  Total No. of testcases skipped are::" + sCount.size() + "  >>>>>>>>>>>>>");
        LoggerClass.println("<<<<<<<<<<<  Total No. of testcases failed are::" + fCount.size() + "  >>>>>>>>>>>>>");
        LoggerClass.println("");
        LoggerClass.println("");
        LoggerClass.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {
        // TODO Auto-generated method stub
    }
}
