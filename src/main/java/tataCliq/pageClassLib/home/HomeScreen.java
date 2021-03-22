package main.java.tataCliq.pageClassLib.home;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.core.CommonAppiumLib;
import main.java.tataCliq.TataCliqBaseClass;
import main.java.tataCliq.pageClassLib.TataCliqObjects;
import main.java.tataCliq.pageClassLib.TataCliqVariables;
import main.java.utils.ExcelFunctions;
import main.java.utils.LoggerClass;
import main.java.utils.UtilLib;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Nitin D on 2020.
 */
public class HomeScreen extends TataCliqBaseClass implements TataCliqObjects {

    public HomeScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    boolean actualMRP;

    @AndroidFindBy(id = "toolbar_icon")
    MobileElement tataCliq_logo;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='TataCLiQ Logo']//preceding-sibling::android.widget.ImageButton")
    MobileElement back_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Search TataCLiQ']")
    MobileElement search_icon;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='What are you looking for?']")
    MobileElement search_input;

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'search_mag_icon')]")
    MobileElement search_activate_button;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textViewProductName')]")
    List<MobileElement> product_name;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textViewProductSP')]")
    List<MobileElement> price_info_with_discount;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'textViewProductMRP')]")
    List<MobileElement> price_info_without_discount;

    // Screen after Product selection
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='MOP']")
    MobileElement price_info_after;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Product Name']")
    MobileElement product_name_after;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Change']")
    MobileElement change_pincode_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Submit']")
    MobileElement submit_pincode_btn;

    @AndroidFindBy(id = "editTextEnterPinCode")
    MobileElement pincode_input;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@index='0' and contains(@resource-id,'image_view_product')]")
    MobileElement firstOption_mainScreen;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@index='0' and contains(@resource-id,'image_view')]")
    MobileElement firstOption_secondScreen;

    @AndroidFindBy(id = "text_view_my_bag_product_name")
    MobileElement productName_inBag;

    @AndroidFindBy(id = "text_view_my_bag_product_offer_price")
    MobileElement productPrice_inBag;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Bag Total']/following-sibling::android.widget.TextView")
    MobileElement bagTotal;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Shipping Charges']/following-sibling::android.widget.TextView")
    MobileElement shippingCharges;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Bag Subtotal']/following-sibling::android.widget.TextView")
    MobileElement bagSubtotal;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Product Discount(s)']/following-sibling::android.widget.TextView")
    MobileElement productDiscount;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Total Payable']/following-sibling::android.widget.TextView")
    MobileElement totalPayable;

    @AndroidFindBy(id = "txtStickyHeaderTotalPayableAmount")
    MobileElement checkoutPayable;

    @AndroidFindBy(id = "button_my_bag_checkout")
    MobileElement checkout_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO BAG']")
    MobileElement add_to_bag_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='GO TO BAG']")
    MobileElement go_to_bag_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue Shopping']")
    MobileElement continue_shopping_btn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove']")
    MobileElement remove_product_from_bag_btn;

    /**
     * This will verify the home screen of application using search icon visible on UI
     *
     * @return
     */
    public HomeScreen verifyHomeScreen() {
        Assert.assertTrue(CommonAppiumLib.elementIsDisplayed(search_icon), "Home screen verified");
        return this;
    }

    /**
     * This will click the search Icon visible on UI
     */
    private void clickSearchIcon() {
        CommonAppiumLib.clickElement(search_icon);
        LoggerClass.println("----> Search icon clicked");
        UtilLib.sleep(2);
        boolean value = CommonAppiumLib.elementIsDisplayed(search_input);
        Assert.assertTrue(value, "Search input element is not present");
    }

    /**
     * This will enter the pin code as provided from input excel data file
     *
     * @return
     */
    public HomeScreen enterPincode() {
        CommonAppiumLib.scrollToSpecificElement(change_pincode_btn);
        CommonAppiumLib.clickElement(change_pincode_btn);
        String pincode = CommonAppiumLib.sendInputData(pincode_input, ExcelFunctions.getValueFromExcel("Address_Details", "A", iRowNumber));
        CommonAppiumLib.clickElement(submit_pincode_btn);
        LoggerClass.println("----> Pin code is : " + pincode);
        return this;
    }

    /**
     * This will add product to bag
     *
     * @return
     */
    public HomeScreen addToBag() {
        CommonAppiumLib.clickElement(add_to_bag_btn);
        LoggerClass.println("----> Add to bag button clicked");
        return this;
    }

    /**
     * This will click go to bag button and navigate user to in-bag details
     *
     * @return
     */
    public HomeScreen goToBag() {
        CommonAppiumLib.clickElement(go_to_bag_btn);
        LoggerClass.println("----> Go to bag button clicked");
        return this;
    }

    /**
     * This will remove product from bag
     *
     * @return
     */
    public HomeScreen removeFromBag() {
        CommonAppiumLib.clickElement(remove_product_from_bag_btn);
        LoggerClass.println("----> Remove from bag button clicked");
        return this;
    }

    /**
     * This will click continue shopping button when bag is empty
     *
     * @return
     */
    public HomeScreen clickContinueShopping() {
        CommonAppiumLib.clickElement(continue_shopping_btn);
        LoggerClass.println("----> Continue shopping button clicked");
        return this;
    }

    /**
     * This will capture the product details
     * e.g product name, product price with and without discount from UI
     * and set it to respective variables defined
     *
     * @param tataCliqVariables
     * @return
     */
    public HomeScreen setDataFromFirstScreen(TataCliqVariables tataCliqVariables) {
        UtilLib.sleep(5);
        // get text from first product from the list visible on UI and set name to productName variable defined in variables class
        tataCliqVariables.setProductName(CommonAppiumLib.getTextPresent(product_name.get(0)));
        LoggerClass.println("----> Product selected is : " + tataCliqVariables.getProductName());

        tataCliqVariables.setProductPriceWithDiscount(CommonAppiumLib.getNumberPresentOnScreen(price_info_with_discount.get(0)));
        LoggerClass.println("----> Product price with discount is : " + tataCliqVariables.getProductPriceWithDiscount());

        actualMRP = price_info_without_discount.size() >= 1;
        if (actualMRP) {
            tataCliqVariables.setProductPriceWithoutDiscount(CommonAppiumLib.getNumberPresentOnScreen(price_info_without_discount.get(0)));
        } else {
            tataCliqVariables.setProductPriceWithoutDiscount(0);
        }
        LoggerClass.println("----> Product price without discount is : " + tataCliqVariables.getProductPriceWithoutDiscount());
        return this;
    }

    /**
     * This will assert the captured the product details from first screen
     * e.g product name, product price with and without discount from UI
     * along with other details e.g. shipping charges, total bag amount,
     * sub total bag amount etc...
     *
     * @param tataCliqVariables
     * @return
     */
    public HomeScreen setAndAssertDataInBag(TataCliqVariables tataCliqVariables) {
        CommonAppiumLib.waitForElementToLoad(productName_inBag, 30);
        // below two lines will verify the product name and price in bag with the info captured on previous screen
        CommonAppiumLib.verifyTextPresent(productName_inBag, tataCliqVariables.getProductName());
        CommonAppiumLib.verifyNumberTextPresent(productPrice_inBag, tataCliqVariables.getProductPriceWithDiscount());

        // Scroll to payment info visible on UI
        CommonAppiumLib.scrollVerticallyByTouchAction(0.8, 0.2, 0.1);

        // Verify the product price
        if (actualMRP) {
            CommonAppiumLib.verifyNumberTextPresent(bagTotal, tataCliqVariables.getProductPriceWithoutDiscount());
            LoggerClass.println("----> Amount in Bag total field is :" + tataCliqVariables.getProductPriceWithoutDiscount());
        } else {
            CommonAppiumLib.verifyNumberTextPresent(bagTotal, tataCliqVariables.getProductPriceWithDiscount());
            LoggerClass.println("----> Amount in Bag total field is :" + tataCliqVariables.getProductPriceWithDiscount());
        }
        // Get the shipping charges info and assign it to shippingCharges variable defined in variables class
        if (CommonAppiumLib.getTextPresent(shippingCharges).equalsIgnoreCase("free")) {
            tataCliqVariables.setShippingCharges(0);
        } else {
            tataCliqVariables.setShippingCharges(CommonAppiumLib.getNumberPresentOnScreen(shippingCharges));
        }
        LoggerClass.println("----> Shipping charges :" + tataCliqVariables.getShippingCharges());
        int subTotal = 0;
        if (actualMRP) {
            subTotal = tataCliqVariables.getProductPriceWithoutDiscount() + tataCliqVariables.getShippingCharges();
        } else {
            subTotal = tataCliqVariables.getProductPriceWithDiscount() + tataCliqVariables.getShippingCharges();
        }
        CommonAppiumLib.verifyNumberTextPresent(bagSubtotal, subTotal);
        LoggerClass.println("----> Bag subtotal amount is :" + subTotal);

        if (CommonAppiumLib.elementIsDisplayed(productDiscount)) {
            tataCliqVariables.setProductDiscount(CommonAppiumLib.getNumberPresentOnScreen(productDiscount));
        } else {
            tataCliqVariables.setProductDiscount(0);
        }
        LoggerClass.println("----> Product discount amount is :" + tataCliqVariables.getProductDiscount());

        // Get the total payable amount from UI and assign it to totalPayable variable defined in variables class
        tataCliqVariables.setTotalPayable(CommonAppiumLib.getNumberPresentOnScreen(totalPayable));
        LoggerClass.println("----> Total payable amount is :" + tataCliqVariables.getTotalPayable());

        // This will assert the total payable and subtotal are same or not
        UtilLib.assertNumber(tataCliqVariables.getTotalPayable(), subTotal - tataCliqVariables.getProductDiscount());
        // This will assert the total payable and checkout payable are same or not
        CommonAppiumLib.verifyNumberTextPresent(checkoutPayable, tataCliqVariables.getTotalPayable());
        return this;
    }

    private void selectSize(String sSize) {

        UtilLib.sleep(2);
        // Select the size of product
        CommonAppiumLib.clickElementByClassAsTextView(sSize);
        LoggerClass.println("----> Size selected is : " + sSize);
    }

    /**
     * This will click checkout button on UI/screen
     *
     * @return
     */
    public HomeScreen checkoutProduct() {
        CommonAppiumLib.clickElement(checkout_btn);
        LoggerClass.println("----> Check out button clicked");
        return this;
    }

    /**
     * This will assert whether bag is empty or not
     *
     * @return
     */
    public HomeScreen assertBagEmpty() {
        UtilLib.sleep(2);
        // Assert the bag is empty
        Assert.assertTrue(CommonAppiumLib.elementIsDisplayedByXpath_seleniumhq(CommonAppiumLib.getXpathByClassAsTextView("Your bag is empty!")));
        LoggerClass.println("----> Bag is empty");
        return this;
    }

    /**
     * This will search the product by clicking search icon
     * and entering the product details provided through input excel
     *
     * @return
     */
    public HomeScreen searchProduct() {
        clickSearchIcon();
        String productId = CommonAppiumLib.sendInputData(search_input, ExcelFunctions.getValueFromExcel("Product_Details", "A", iRowNumber));
        LoggerClass.println("----> Product Id entered is : " + productId);
        CommonAppiumLib.hitEnter();
        return this;
    }

    /**
     * This will
     * 1) Search the product from home screen,
     * 2) Assert the product price and name on following screen
     * 3) Change pin code
     * 4) Add product to bag
     * 5) Go to bag and assert details
     *
     * @param tataCliqVariables
     * @return
     */
    public HomeScreen searchAndSelectProduct(TataCliqVariables tataCliqVariables) {

        searchProduct();

        // get the product info from first screen (name, price)
        setDataFromFirstScreen(tataCliqVariables);

        // Click the product
        CommonAppiumLib.clickElementByClassAsTextView(tataCliqVariables.getProductName());

        // Verify price of product captured from previous screen
        CommonAppiumLib.verifyNumberTextPresent(price_info_after, tataCliqVariables.getProductPriceWithDiscount());

        // This will scroll to pin code input field and enter the pin code
        enterPincode();

        // This will add product to bag
        addToBag();

        // This will go to the bag
        goToBag();

        // This will assert the product info and product price details in bag
        setAndAssertDataInBag(tataCliqVariables);
        return this;
    }

    /**
     * This will
     * 1) Click the first image option visible on UI and select the product from it,
     * 2) Assert the product price and name on following respective images
     * 3) Change pin code
     * 4) Add product to bag
     * 5) Go to bag and assert details
     * 6) Remove product from bag and assert bag is empty
     * 7) Click on Continue shopping button
     *
     * @param tataCliqVariables
     * @return
     */
    public HomeScreen selectFirstProductFromHome(TataCliqVariables tataCliqVariables) {

        // Select the first image option visible on top left corner of application home screen
        CommonAppiumLib.clickElement(firstOption_mainScreen);
        // After clicking the image another image is clicked
        CommonAppiumLib.clickElement(firstOption_secondScreen);

        setDataFromFirstScreen(tataCliqVariables);

        // Click the product
        CommonAppiumLib.clickElementByClassAsTextView(tataCliqVariables.getProductName());

        // Below two lines of code will verify the name and price of product captured from previous screen
        CommonAppiumLib.verifyTextPresent(product_name_after, tataCliqVariables.getProductName());
        CommonAppiumLib.verifyNumberTextPresent(price_info_after, tataCliqVariables.getProductPriceWithDiscount());

        // This will scroll to pin code input field and enter the pin code
        enterPincode();

        //Select size of product
        selectSize("M");

        // This will add product to bag
        addToBag();

        // This will go to the bag
        goToBag();

        // Assert the product details (name, price) in bag
        setAndAssertDataInBag(tataCliqVariables);

        // Remove product from bag
        removeFromBag();

        //Assert bag is empty
        assertBagEmpty();

        // Click continue shopping button
        clickContinueShopping();

        return this;
    }
}
