package test.java.org.tataCliq;

import io.qameta.allure.Description;
import main.java.core.TestResultListener;
import main.java.tataCliq.TataCliqBaseClass;
import main.java.tataCliq.pageClassLib.EnvironmentSetup;
import main.java.tataCliq.pageClassLib.TataCliqVariables;
import main.java.utils.ExcelFunctions;
import main.java.tataCliq.pageClassLib.Navigations;
import main.java.tataCliq.pageClassLib.TataCliqObjects;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by Nitin D on 2020.
 */
@Listeners(TestResultListener.class)
public class TataCliqTestClass extends TataCliqBaseClass implements TataCliqObjects {
    public static TataCliqVariables tataCliqVariables = new TataCliqVariables();

    @Description("This test case will\n" +
            "1) Login into the application\n" +
            "2) Logout of the application")
    @Test(description = "Login Logout check", priority = 1)
    public static void loginLogout() {
        iRowNumber = (int) Double.parseDouble(ExcelFunctions.getValueFromExcel("Login_Details", "B", 2));

        new EnvironmentSetup().setEnvironment();
        new Navigations()
                /*.closePopUp()*/
                .clickMyAccount()
                .getLoginScreen().verifyLoginScreen()
                .getLoginScreen().login()
                .getProfileScreen().verifyProfileScreen()
                .getProfileScreen().logout()
                .getHomeScreen().verifyHomeScreen();
    }

    @Description("This test case will\n" +
            "1) Login into the application\n" +
            "2) Go to home screen\n" +
            "3) Search the product\n" +
            "4) Selected the product\n" +
            "5) Enter pin code\n" +
            "6) Add product to bag\n" +
            "7) Checkout the product\n" +
            "8) Make Payment\n" +
            "9) Delete Address from address book\n" +
            "10) Logout of the application")
    @Test(description = "Search and Select product from home screen", priority = 2)
    public static void searchAndSelectProductFromHomeScreen() {
        iRowNumber = Integer.parseInt(ExcelFunctions.getValueFromExcel("Login_Details", "B", 2));
        new EnvironmentSetup().setEnvironment();
        new Navigations()
                /*.closePopUp()*/
                .clickMyAccount()
                .getLoginScreen().verifyLoginScreen()
                .getLoginScreen().login()
                .getProfileScreen().verifyProfileScreen()
                .getNavigations().clickHome()
                .getHomeScreen().searchAndSelectProduct(tataCliqVariables).checkoutProduct()
                .getAddressDetails().enterAddressDetails()
                .getPaymentDetails().enterPaymentDetails()
                .getPaymentConfirmationScreen().verifyPaymentConfirmationScreen().clickContinueShopping()
                .getNavigations().clickMyAccount()
                .getProfileScreen().verifyProfileScreen().clickAddressBook().deleteAddress()
                .getNavigations().clickBackButton()
                .getProfileScreen().logout()
                .getHomeScreen().verifyHomeScreen();
    }

    @Description("This test case will\n" +
            "1) Login into the application\n" +
            "2) Go to home screen\n" +
            "3) Click first option from Home screen (top left first image)\n" +
            "4) Selected the product\n" +
            "5) Enter pin code\n" +
            "6) Add product to bag\n" +
            "7) Remove product from bag\n" +
            "8) Logout of the application")
    @Test(description = "Select product from home screen", priority = 3)
    public static void selectProductFromHomeScreen() {
        iRowNumber = (int) Double.parseDouble(ExcelFunctions.getValueFromExcel("Login_Details", "B", 2));

        new EnvironmentSetup().setEnvironment();
        new Navigations()
                /*.closePopUp()*/
                .clickMyAccount()
                .getLoginScreen().verifyLoginScreen()
                .getLoginScreen().login()
                .getProfileScreen().verifyProfileScreen()
                .getNavigations().clickHome()
                .getHomeScreen().selectFirstProductFromHome(tataCliqVariables)
                .getNavigations().clickMyAccount()
                .getProfileScreen().verifyProfileScreen().logout()
                .getHomeScreen().verifyHomeScreen();
    }
}