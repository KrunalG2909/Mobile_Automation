package main.java.utils;

import org.apache.log4j.*;

/**
 * Created by Nitin D on 2020.
 */
public class LoggerClass {

    // Initialize Log4j logs
    private static Logger Log = Logger.getLogger(LoggerClass.class.getName());//

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void startTestCase(String sTestCaseName) {

        Log.info("****************************************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");
        Log.info("****************************************************************************************");
    }

    //This is to print log for the ending of the test case
    public static void endTestCase(String sTestCaseName) {

        Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
    }

    public static void println(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}