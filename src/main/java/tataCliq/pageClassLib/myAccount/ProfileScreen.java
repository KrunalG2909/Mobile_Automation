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
import main.java.utils.LoggerClass;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;

public class ProfileScreen extends TataCliqBaseClass implements TataCliqObjects {

    public ProfileScreen() {

        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Book']")
    MobileElement address_book_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
    MobileElement delete_address_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Logout']")
    MobileElement logout_btn;

    /**
     * This will verify the profile screen
     *
     * @return
     */
    public ProfileScreen verifyProfileScreen() {
        CommonAppiumLib.waitForElementToLoad(logout_btn, 30);
        Assert.assertTrue(CommonAppiumLib.elementIsDisplayed(logout_btn), "Profile screen verified");
        return this;
    }

    /**
     * This will click the Address book option visible on Profile screen
     *
     * @return
     */
    public ProfileScreen clickAddressBook() {
        CommonAppiumLib.clickElement(address_book_btn);
        return this;
    }

    /**
     * This will click the delete address present in system/application
     *
     * @return
     */
    public ProfileScreen deleteAddress() {
        CommonAppiumLib.clickElement(delete_address_btn);
        return this;
    }

    /**
     * This will click logout button
     */
    private void clickLogout() {
        CommonAppiumLib.clickElement(logout_btn);
        LoggerClass.println("----> Logout button clicked");
    }

    /**
     * This will verify the profile screen and logout the user from application
     *
     * @return
     */
    public ProfileScreen logout() {
        verifyProfileScreen();
        clickLogout();
        return this;
    }
}
