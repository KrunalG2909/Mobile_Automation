/**
 * Created by Nitin D on 2020
 */
package main.java.tataCliq.pageClassLib;

import main.java.tataCliq.pageClassLib.checkoutDetails.PaymentConfirmationScreen;
import main.java.tataCliq.pageClassLib.home.HomeScreen;
import main.java.tataCliq.pageClassLib.myAccount.LoginScreen;
import main.java.tataCliq.pageClassLib.myAccount.ProfileScreen;
import main.java.tataCliq.pageClassLib.checkoutDetails.AddressDetails;
import main.java.tataCliq.pageClassLib.checkoutDetails.PaymentDetails;

public interface TataCliqObjects {

    default Navigations getNavigations() {
        return new Navigations();
    }

    default HomeScreen getHomeScreen() {
        return new HomeScreen();
    }

    default LoginScreen getLoginScreen() {
        return new LoginScreen();
    }

    default ProfileScreen getProfileScreen() {
        return new ProfileScreen();
    }

    default AddressDetails getAddressDetails() {
        return new AddressDetails();
    }

    default PaymentDetails getPaymentDetails() {
        return new PaymentDetails();
    }

    default PaymentConfirmationScreen getPaymentConfirmationScreen() {
        return new PaymentConfirmationScreen();
    }
}
