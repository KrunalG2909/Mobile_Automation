package main.java.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.Assert;


/**
 * Created by Nitin D on 2020.
 */
public class UtilLib {

    /**
     * This function will put a hard wait condition inthread execution for defined time
     *
     * @param seconds
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            LoggerClass.println("Time out exception occured in sleep method");
            e.printStackTrace();
        }
    }

    /**
     * delete file from folder
     *
     * @param sFolderPath
     */
    public static void deleteFiles(String sFolderPath) {
        File folder = new File(sFolderPath);
        try {
            for (File file : folder.listFiles()) {
                file.delete();
            }
        } catch (Exception e) {

            LoggerClass.println("##########     No files to delete     ##########");
        }
    }

    /**
     * Generate random string for name, address etc.
     *
     * @param noOfChar
     * @return random string
     * <p>
     * e.g. getRandomString(5) will create string as MGNHF
     */
    public static String generateRandomString(int noOfChar) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // define character array of alphabets
        char[] chr = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String name = "";

        // Traverse through the array for noOfChar times and append random char to
        // string builder's reference
        for (int i = 0; i < noOfChar; i++) {
            char c = chr[random.nextInt(chr.length)];
            sb.append(c);
        }
        name = sb.toString().toUpperCase();
        return name;
    }

    /**
     * Get system date and format it in given format
     *
     * @return sTimeStamp in format 20180311180912
     */
    public static String getTimeStamp() {
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String sTimeStamp = date.format(cal.getTime());
        sTimeStamp = sTimeStamp.replaceAll("\\W", "");
        return sTimeStamp;
    }

    /**
     * Generate random number for mobile/aadhaar etc. e.g. For Mobile number enter
     * num as 9 and prefix it with some digit greater then 0. otherwise if first
     * digit is 0 that would be wrong phone number or aadhaar number.
     *
     * @param num
     * @return randomly generated number
     */
    public static String generateRandomNumber(int num) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < num; i++) {
            number.append(Integer.toString(random.nextInt(9)));
        }
        return number.toString();
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static void saveTextLog(String attachName, String message) {
        Allure.addAttachment(attachName, message);
    }

    /**
     * This will assert (exact match) the two text(Strings) provided
     *
     * @param actual
     * @param expected
     */
    public static void assertText(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            Assert.fail("text compare failed\n actual: " + actual + "\nExpected: " + expected);
        }
    }

    /**
     * This will assert (exact match) the two number(integers) provided
     *
     * @param actual
     * @param expected
     */
    public static void assertNumber(int actual, int expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            Assert.fail("text compare failed\n actual: " + actual + "\nExpected: " + expected);
        }
    }
}
