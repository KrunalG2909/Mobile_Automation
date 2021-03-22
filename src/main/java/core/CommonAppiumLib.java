package main.java.core;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import main.java.utils.UtilLib;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import main.java.utils.LoggerClass;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

/**
 * Created by Nitin D on 2020.
 */
public class CommonAppiumLib extends BaseTestClass {

    /**
     * This will wait for 30 seconds to load the element on screen
     *
     * @param elementRef
     * @param timeInSeconds
     */
    public static void waitForElementToLoad(MobileElement elementRef, int timeInSeconds) {
        try {
            WebDriverWait w = new WebDriverWait(driver, timeInSeconds);
            w.until(ExpectedConditions.visibilityOf(elementRef));
        } catch (NoSuchElementException | NullPointerException e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail(elementRef.toString() + "not valid");
        }
    }

    /**
     * Enter given string or generate random
     *
     * @param elementRef
     * @param sValue
     * @return
     */
    public static String sendInputData(MobileElement elementRef, String sValue) {
        String sString = null;
        if (!sValue.equals("")) {
            typeTextAndroid(elementRef, sValue);
            sString = sValue;
        } else {
            sString = UtilLib.generateRandomString(10);
            typeTextAndroid(elementRef, sString);
        }
        return sString;
    }

    /**
     * This method will first clear any input field and then type the text
     *
     * @param mElement
     * @param sText
     */
    private static void typeTextAndroid(MobileElement mElement, String sText) {

        try {
            mElement.click();
            mElement.clear();
            mElement.setValue(sText);
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail(mElement.toString() + "not valid");
        }
    }

    /**
     * This will return element is displayed or not
     *
     * @param elementRef
     * @return
     */
    public static boolean elementIsDisplayed(MobileElement elementRef) {
        try {
            return elementRef.isDisplayed();
        } catch (Exception e) {
//            UtilLib.saveTextLog("Element is not displayed", e.getMessage());
            return false;
        }
    }

    /**
     * This will return element is displayed or not by seleniumhq library
     *
     * @param xpath
     * @return
     */
    public static boolean elementIsDisplayedByXpath_seleniumhq(String xpath) {
        try {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (Exception e) {
            UtilLib.saveTextLog("Element is not displayed", e.getMessage());
            return false;
        }
    }

    /**
     * This will return element is enabled or not
     *
     * @param elementRef
     * @return
     */
    public static boolean elementIsEnabled(MobileElement elementRef) {
        try {
            elementRef.isEnabled();
            return true;
        } catch (Exception e) {
            UtilLib.saveTextLog("Element is not enabled", e.getMessage());
            return false;
        }
    }

    /**
     * Capture Image
     *
     * @param sImageElementName
     * @param liveTabApplicable
     * @throws Exception
     */
    public static void captureImage(MobileElement sImageElementName, String liveTabApplicable) throws Exception {

        // click on image element to open camera
        sImageElementName.click();

        Thread.sleep(10000);

        if (liveTabApplicable.equalsIgnoreCase("Yes")) {

            // This is only for live TAB
            // click physical volume down button which click image
            driver.pressKey(new KeyEvent(AndroidKey.VOLUME_DOWN));
            Thread.sleep(8000);
            // click on OK button to save clicked image
            clickElementByClassAsTextView("OK");
            Thread.sleep(5000);

        } else {

            // only for emulators
            // defined shutter Button & Capture Image done button as mobile element
            MobileElement shutterButton = driver.findElement(
                    By.xpath("//android.widget.ImageView[contains(@resource-id,'shutter_button') and @index='0']"));
            shutterButton.click(); // click on shutter button which will take picture
            Thread.sleep(8000);
            MobileElement captureImageDone = driver.findElement(
                    By.xpath("//android.widget.ImageButton[contains(@resource-id,'done_button') and @index='0']"));
            captureImageDone.click(); // click on done option for selecting taken picture
            Thread.sleep(5000);

        }

        LoggerClass.println("Image captured");
    }

    /**
     * This will search the value in search field and then select the visible option from suggested dropdown
     *
     * @param elementRef
     * @param sText
     */
    public static void searchAndSelectFromDropDownWithClassAsTextView(MobileElement elementRef, String sText) {
        try {
            elementRef.sendKeys(sText);
            Thread.sleep(2000);
            driver.findElement(By.xpath(getXpathByClassAsTextView(sText))).click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * This will click the drop down field and select the value provided with class
     * as text view
     *
     * @param sDDLocator
     * @param sText
     * @throws InterruptedException
     */
    public static void clickAndSelectFromDropDownWithClassAsTextView(MobileElement sDDLocator, String sText) {
        try {
            waitForElementToLoad(sDDLocator, 30);
            sDDLocator.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(getXpathByClassAsTextView(sText))).click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * This will click the drop down field and select the value provided with its
     * class as checked text view
     *
     * @param elementRef
     * @param sText
     * @throws InterruptedException
     */
    public static void clickAndSelectFromDropDownWithCheckedTextView(MobileElement elementRef, String sText) {
        try {
            elementRef.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(getXpathByClassAsCheckedTextView(sText))).click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * Temporary solution for selecting dropdown values for autocomplete dropdowns in TW.
     * Below function will take
     * location of element in x & y coordinates and increase x & y with some value
     * and click on modified x & y coordinates
     *
     * @param elementRef
     * @param increasedXvalue
     * @param increasedYvalue
     * @throws InterruptedException
     */
    public static void selectAutoCompleteDropdownOptionByTouchAction(MobileElement elementRef, int increasedXvalue, int increasedYvalue) throws InterruptedException {

        boolean value = elementIsDisplayed(elementRef);
        Assert.assertTrue(value, "Element is not present");
        Point point = ((RemoteWebElement) elementRef).getLocation();
        int xPoint = point.getX();
        int yPoint = point.getY();
        LoggerClass.println(xPoint + ", " + yPoint);
        int xPoint1 = point.getX() + increasedXvalue;
        int yPoint1 = point.getY() + increasedYvalue;
        LoggerClass.println(xPoint1 + ", " + yPoint1);
        Thread.sleep(1500);
        new TouchAction(BaseTestClass.driver).tap(PointOption.point(xPoint1, yPoint1)).release().perform();
    }

    /**
     * Temporary solution for selecting dropdown value Below function will take
     * location of element in x & y coordinates and increase x & y with some value
     * and click on modified x & y coordinates
     *
     * @param elementRef
     * @param increasedXvalue
     * @param increasedYvalue
     * @throws InterruptedException
     */
    public static void selectDropdownOptionByTouchAction(MobileElement elementRef, int increasedXvalue,
                                                         int increasedYvalue) throws InterruptedException {

        boolean value = elementIsDisplayed(elementRef);
        Assert.assertTrue(value, "Element is not present");
        elementRef.click();
        Point point = ((RemoteWebElement) elementRef).getLocation();
        int xPoint = point.getX();
        int yPoint = point.getY();
        LoggerClass.println(xPoint + ", " + yPoint);
        int xPoint1 = point.getX() + increasedXvalue;
        int yPoint1 = point.getY() + increasedYvalue;
        LoggerClass.println(xPoint1 + ", " + yPoint1);
        Thread.sleep(1500);
        new TouchAction(driver).tap(PointOption.point(xPoint1, yPoint1)).release().perform();

    }

    /**
     * This will click/ perform the mobile device back button click action
     */
    public static void clickBackButton() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    /**
     * This will click the mobile element
     *
     * @param elementRef
     */
    public static void clickElement(MobileElement elementRef) {
        try {
            boolean value = elementIsDisplayed(elementRef);
            Assert.assertTrue(value, "Element is not valid");
            elementIsEnabled(elementRef);
            elementRef.click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid: " + elementRef.toString());
        }
    }

    /**
     * Click element with provided text and class is textView
     *
     * @param sText
     * @throws InterruptedException
     */
    public static void clickElementByClassAsTextView(String sText) {
        try {
            driver.findElement(By.xpath(getXpathByClassAsTextView(sText))).click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * Click element with provided text and class is checkedTextView
     *
     * @param sText
     * @throws InterruptedException
     */
    public static void clickElementByClassAsCheckedTextView(String sText) {
        try {
            driver.findElement(By.xpath(getXpathByClassAsCheckedTextView(sText))).click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * Click button with provided text and class is Button
     *
     * @param sText
     * @return
     * @throws InterruptedException
     */
    public static void clickElementByClassAsButton(String sText) {
        try {
            driver.findElement(By.xpath(getXpathByClassAsButton(sText))).click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * Click button with provided text and class is RadioButton
     *
     * @param sText
     * @throws InterruptedException
     */
    public static void clickElementByClassAsRadioButton(String sText) {
        try {
            driver.findElement(By.xpath(getXpathByClassAsRadioButton(sText))).click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * Click button with provided text and class is CheckBox
     *
     * @param sText
     * @throws InterruptedException
     */
    public static void clickElementByClassAsCheckBox(String sText) {
        try {
            driver.findElement(By.xpath(getXpathByClassAsCheckBox(sText))).click();
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * This will return the xpath values for text value provided with class as
     * TextView
     *
     * @param sValue
     * @return
     */
    public final static String getXpathByClassAsTextView(String sValue) {

        return "//android.widget.TextView[@text='" + sValue + "']";
    }

    /**
     * This will return the xpath values for text value provided with class as
     * checkedTextView
     *
     * @param sValue
     * @return
     */
    public final static String getXpathByClassAsCheckedTextView(String sValue) {

        return "//android.widget.CheckedTextView[@text='" + sValue + "']";
    }

    /**
     * This will return the xpath value for text value provided with class as button
     *
     * @param sValue
     * @return
     */
    public final static String getXpathByClassAsButton(String sValue) {

        return "//android.widget.Button[@text='" + sValue + "']";
    }

    /**
     * This will return the xpath value for text value provided with class as
     * RadioButton
     *
     * @param sValue
     * @return
     */
    public final static String getXpathByClassAsRadioButton(String sValue) {

        return "//android.widget.RadioButton[@text='" + sValue + "']";
    }

    /**
     * This will return the xpath value for text value provided with class as
     * CheckBox
     *
     * @param sValue
     * @return
     */
    public final static String getXpathByClassAsCheckBox(String sValue) {

        return "//android.widget.CheckBox[@text='" + sValue + "']";
    }

    /**
     * Scroll to element
     *
     * @param elementRef
     */
    public static void scrollToSpecificElement(MobileElement elementRef) {
        boolean flag = false;
        for (int i = 0; i <= 10; i++) {
            try {
                if (elementRef.isDisplayed()) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                scrollVerticallyByTouchAction(0.8, 0.3, 0.1);
                flag = false;
            }
        }
        if (!flag) {
            UtilLib.saveTextLog("Log", "Element is  not visible on screen" + elementRef.toString());
        }
    }

    /**
     * Scroll to specific element by using parent element id and child element class
     * and text
     *
     * @param sParentElementId
     * @param sChildClass
     * @param sChildText
     */
    public static void scrollToSpecificElementByParentChildRelation(String sParentElementId, String sChildClass,
                                                                    String sChildText) {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().resourceId(\"" + sParentElementId + "\")).getChildByText("
                            + "new UiSelector().className(\"" + sChildClass + "\"), \"" + sChildText + "\")"));
        } catch (Exception e) {
            UtilLib.saveTextLog("Log", e.getMessage());
            Assert.fail("Element is not valid");
        }
    }

    /**
     * This will scroll UI vertically based on percentage give
     * For UP startPercentage<endPercentage
     * For DOWN startPercentage>endPercentage
     *
     * @param startPercentage
     * @param endPercentage
     * @param anchorPercentage
     */
    public static void scrollVerticallyByTouchAction(double startPercentage, double endPercentage, double anchorPercentage) {

        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);

        new TouchAction(driver)
                .press(PointOption.point(anchor, startPoint))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(anchor, endPoint))
                .release().perform();
    }


    /**
     * Verify requested text on screen.
     *
     * @param elementRef
     * @param sText
     * @return
     */
    public static void verifyTextPresent(MobileElement elementRef, String sText) {
        boolean value = elementIsDisplayed(elementRef);
        Assert.assertTrue(value, "Element is not valid");
        try {
            Assert.assertEquals(elementRef.getText(), sText);
        } catch (Exception e) {
            UtilLib.saveTextLog("Text compare assertion failed", e.getMessage());
            Assert.fail("Text compare assertion failed");
        }
    }

    /**
     * Verify requested number on screen.
     *
     * @param elementRef
     * @param iText
     * @return
     */
    public static void verifyNumberTextPresent(MobileElement elementRef, int iText) {
        boolean value = elementIsDisplayed(elementRef);
        Assert.assertTrue(value, "Element is not present");
        String text = elementRef.getText();
        text = text.replaceAll("[^0-9\\.]", "");
        double d = Double.parseDouble(text);
        int iValue = (int) (d);
        try {
            Assert.assertEquals(iValue, iText);
        } catch (Exception e) {
            UtilLib.saveTextLog("Text compare assertion failed", e.getMessage());
            Assert.fail("Text compare assertion failed");
        }
    }

    /**
     * This will return the text on screen.
     *
     * @param elementRef
     * @return
     */
    public static String getTextPresent(MobileElement elementRef) {
        boolean value = elementIsDisplayed(elementRef);
        Assert.assertTrue(value, "Element is not present");
        return elementRef.getText();
    }

    /**
     * This will return the number on screen.
     *
     * @param elementRef
     * @return
     */
    public static int getNumberPresentOnScreen(MobileElement elementRef) {
        boolean value = elementIsDisplayed(elementRef);
        Assert.assertTrue(value, "Element is not valid");
        String text = elementRef.getText();
        text = text.replaceAll("[^0-9\\.]", "");
        double d = Double.parseDouble(text);
        int iValue = (int) (d);
        return iValue;
    }

    public static void hitEnter() {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
