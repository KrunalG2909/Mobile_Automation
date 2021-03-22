/**
 * Created by Nitin D on 2020
 */
package main.java.tataCliq.pageClassLib.myAccount;

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

public class LoginScreen extends TataCliqBaseClass implements TataCliqObjects {
    private String sheetName = "Login_Details";

    public LoginScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(id = "edtPhone")
    MobileElement email_address_input;

    @AndroidFindBy(id = "edtPassword")
    MobileElement password_input;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGIN']")
    MobileElement login_btn;

    @AndroidFindBy(id = "text_input_end_icon")
    MobileElement show_password_icon;

    @AndroidFindBy(id = "txtVForgotPassword")
    MobileElement forgot_password_link;

    @AndroidFindBy(id = "txtSignUp")
    MobileElement signup_btn;

    @AndroidFindBy(id = "txtFacebook")
    MobileElement facebook_btn;

    @AndroidFindBy(id = "txtGoogle")
    MobileElement google_btn;

    @AndroidFindBy(id = "callUs")
    MobileElement call_us_link;

    @AndroidFindBy(id = "privacyPolicyLink")
    MobileElement terms_and_condition_link;

    @AndroidFindBy(id = "cancelLogin")
    MobileElement cancel_login_btn;

    /**
     * This will verify the login screen
     *
     * @return
     */
    public LoginScreen verifyLoginScreen() {
        Assert.assertTrue(CommonAppiumLib.elementIsDisplayed(email_address_input), "Login screen verified");
        return this;
    }

    /**
     * This will enter the login credential and click login button
     */
    private void enterLoginDetails() {
        LoggerClass.println("----> Logging using user details provided");
        String username = CommonAppiumLib.sendInputData(email_address_input, ExcelFunctions.getValueFromExcel(sheetName, "C", iRowNumber));
        LoggerClass.println("----> Username : " + username);
        String password = CommonAppiumLib.sendInputData(password_input, ExcelFunctions.getValueFromExcel(sheetName, "D", iRowNumber));
        LoggerClass.println("----> Password : " + password);
        CommonAppiumLib.clickElement(login_btn);
        LoggerClass.println("----> Login button clicked");
    }

    /**
     * This will verify the login screen and enter the login details
     *
     * @return
     */
    public LoginScreen login() {
        verifyLoginScreen();
        enterLoginDetails();
        return this;
    }
}
