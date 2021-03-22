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
import main.java.utils.UtilLib;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class PaymentDetails extends TataCliqBaseClass implements TataCliqObjects {
    private String sheetName = "Payment_Details";

    public PaymentDetails() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue']")
    List<MobileElement> continue_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Debit Card']")
    MobileElement debit_card_option;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Card number*']")
    MobileElement card_number_input;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Name on card*']")
    MobileElement name_on_card_input;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='CVV*']")
    MobileElement cvv_input;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Expiry month']")
    MobileElement expiry_month_dd;//03

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Expiry year']")
    MobileElement expiry_year_dd;//2023

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pay Now']")
    MobileElement pay_now_btn;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Success']")
    MobileElement payment_success_btn;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Failure']")
    MobileElement payment_failure_btn;

    /**
     * This will
     * 1) Click continue button to navigate to payment details options
     * 2) Enter card details as provided from input excel file
     * 3) And make payment
     *
     * @return
     */
    public PaymentDetails enterPaymentDetails() {

        CommonAppiumLib.clickElement(continue_btn.get(0));

        if (continue_btn.size() >= 1) {
            CommonAppiumLib.clickElement(continue_btn.get(0));
        }

        CommonAppiumLib.scrollToSpecificElement(debit_card_option);
        CommonAppiumLib.clickElement(debit_card_option);
        enterCardDetails();
        CommonAppiumLib.clickElement(pay_now_btn);
        UtilLib.sleep(10);
        boolean successBtn = CommonAppiumLib.elementIsDisplayed(payment_success_btn);
        if (successBtn) {
            CommonAppiumLib.clickElement(payment_success_btn);
        }
        return this;
    }

    /**
     * This will enter the card details as provided through input excel file
     */
    public PaymentDetails enterCardDetails() {
        CommonAppiumLib.sendInputData(card_number_input, ExcelFunctions.getValueFromExcel(sheetName, "B", iRowNumber));
        CommonAppiumLib.sendInputData(name_on_card_input, ExcelFunctions.getValueFromExcel(sheetName, "C", iRowNumber));
        CommonAppiumLib.clickAndSelectFromDropDownWithClassAsTextView(expiry_month_dd, ExcelFunctions.getValueFromExcel(sheetName, "D", iRowNumber));
        CommonAppiumLib.clickAndSelectFromDropDownWithClassAsTextView(expiry_year_dd, ExcelFunctions.getValueFromExcel(sheetName, "E", iRowNumber));
        CommonAppiumLib.sendInputData(cvv_input, ExcelFunctions.getValueFromExcel(sheetName, "F", iRowNumber));
        return this;
    }
}
