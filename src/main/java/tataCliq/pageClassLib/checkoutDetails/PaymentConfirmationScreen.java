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

public class PaymentConfirmationScreen extends TataCliqBaseClass implements TataCliqObjects {

    public PaymentConfirmationScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='VIEW ORDERS']")
    MobileElement view_orders_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE SHOPPING']")
    MobileElement continue_shopping_btn;

    /**
     * This will return view order button is visible on screen or not
     *
     * @return
     */
    public PaymentConfirmationScreen verifyPaymentConfirmationScreen() {
        CommonAppiumLib.waitForElementToLoad(view_orders_btn, 30);
        Assert.assertTrue(CommonAppiumLib.elementIsDisplayed(view_orders_btn), "Payment confirmation screen verified");
        return this;
    }

    /**
     * This will click continue shopping button on payment confirmation screen
     *
     * @return
     */
    public PaymentConfirmationScreen clickContinueShopping() {
        CommonAppiumLib.clickElement(continue_shopping_btn);
        return this;
    }
}
