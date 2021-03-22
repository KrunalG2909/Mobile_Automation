/**
 * Created by Nitin D on 2020
 */
package main.java.tataCliq.pageClassLib;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.core.CommonAppiumLib;
import main.java.tataCliq.TataCliqBaseClass;
import main.java.utils.LoggerClass;
import main.java.utils.UtilLib;
import org.openqa.selenium.support.PageFactory;


public class Navigations extends TataCliqBaseClass implements TataCliqObjects {
    public Navigations() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='TataCLiQ Logo']//preceding-sibling::android.widget.ImageButton")
    MobileElement back_btn;

    @AndroidFindBy(xpath = "//android.widget.Button[@index='0']")
    MobileElement popUp_close;

    @AndroidFindBy(xpath = "//android.widget.Button[@index='1']")
    MobileElement popUp_Accept;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Account']")
    MobileElement my_account_tab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
    MobileElement home_tab;

    /**
     * This will decline/close the pop up on screen
     *
     * @return
     */
    public Navigations closePopUp() {

        CommonAppiumLib.clickElement(popUp_close);
        LoggerClass.println("----> Pop up closed");
        return this;
    }

    /**
     * This will accept the pop up on screen
     *
     * @return
     */
    public Navigations acceptPopUp() {

        CommonAppiumLib.clickElement(popUp_Accept);
        LoggerClass.println("----> Pop up closed");
        return this;
    }

    /**
     * This will click the back button present on the screen
     *
     * @return
     */
    public Navigations clickBackButton() {

        CommonAppiumLib.clickElement(back_btn);
        LoggerClass.println("----> Back button clicked");
        return this;
    }

    /**
     * This will click my account tab
     *
     * @return
     */
    public Navigations clickMyAccount() {
        UtilLib.sleep(5);
        CommonAppiumLib.waitForElementToLoad(my_account_tab, 30);
        CommonAppiumLib.clickElement(my_account_tab);
        LoggerClass.println("----> My account tab clicked");
        return this;
    }

    /**
     * This will click Home tab
     *
     * @return
     */
    public Navigations clickHome() {
        CommonAppiumLib.clickElement(home_tab);
        LoggerClass.println("----> Home tab clicked");
        return this;
    }

}
