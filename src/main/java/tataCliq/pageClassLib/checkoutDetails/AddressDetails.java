/**
 * Created by Nitin D on 2020
 */
package main.java.tataCliq.pageClassLib.checkoutDetails;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.core.CommonAppiumLib;
import main.java.tataCliq.TataCliqBaseClass;
import main.java.tataCliq.pageClassLib.TataCliqObjects;
import main.java.utils.ExcelFunctions;
import main.java.utils.LoggerClass;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;

public class AddressDetails extends TataCliqBaseClass implements TataCliqObjects {
    private String sheetName = "Address_Details";

    public AddressDetails() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@index='1']")
    MobileElement popUp_Accept;

    @AndroidFindBy(id = "edtfirstName")
    MobileElement first_name_input;

    @AndroidFindBy(id = "edtLastName")
    MobileElement last_name_input;

    @AndroidFindBy(id = "edtAddress")
    MobileElement address_input;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Landmark']")
    MobileElement select_landmark_dd;//Shivaji Nagar

    @AndroidFindBy(id = "edtPhoneNumber")
    MobileElement phone_number_input;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Office']")
    MobileElement office_rbtn;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Home']")
    MobileElement home_rbtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Save and Continue']")
    MobileElement save_and_continue_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Checkout']")
    MobileElement checkout_btn;

    /**
     * This will verify the address details screen
     *
     * @return
     */
    public AddressDetails verifyAddressScreen() {
        Assert.assertTrue(CommonAppiumLib.elementIsDisplayed(first_name_input), "Address details screen verified");
        return this;
    }

    /**
     * This will accept the continue with same pin code pop up
     */
    private void continueWithSamePincode() {
        int count = 1;
        while (!CommonAppiumLib.elementIsDisplayed(popUp_Accept)) {
            CommonAppiumLib.clickElement(checkout_btn);
            if (count == 10) {
                break;
            }
            count++;
        }
        CommonAppiumLib.clickElement(popUp_Accept);
        LoggerClass.println("----> Continue with same pin code is accepted");
    }

    /**
     * This will enter the address details as provided from input excel file
     * First name, last name, address, landmark, phone number, address type
     *
     * @return
     */
    public AddressDetails enterAddressDetails() {
        continueWithSamePincode();
        verifyAddressScreen();
        CommonAppiumLib.sendInputData(first_name_input, ExcelFunctions.getValueFromExcel(sheetName, "C", iRowNumber));
        CommonAppiumLib.sendInputData(last_name_input, ExcelFunctions.getValueFromExcel(sheetName, "D", iRowNumber));
        CommonAppiumLib.sendInputData(address_input, ExcelFunctions.getValueFromExcel(sheetName, "E", iRowNumber));
        CommonAppiumLib.clickAndSelectFromDropDownWithClassAsTextView(select_landmark_dd, ExcelFunctions.getValueFromExcel(sheetName, "F", iRowNumber));

        CommonAppiumLib.scrollToSpecificElement(office_rbtn);
        String phone_input = CommonAppiumLib.sendInputData(phone_number_input, ExcelFunctions.getValueFromExcel(sheetName, "B", iRowNumber));
        LoggerClass.println("----> Phone number is :" + phone_input);
        String addType = ExcelFunctions.getValueFromExcel(sheetName, "B", iRowNumber);
        if (addType.equalsIgnoreCase("office")) {
            CommonAppiumLib.clickElement(office_rbtn);
        }
        CommonAppiumLib.clickElement(save_and_continue_btn);
        return this;
    }
}
