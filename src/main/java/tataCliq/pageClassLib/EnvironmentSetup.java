/**
 * Created by Nitin D on 2021
 */
package main.java.tataCliq.pageClassLib;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import main.java.core.CommonAppiumLib;
import main.java.tataCliq.TataCliqBaseClass;
import main.java.utils.ExcelFunctions;
import main.java.utils.LoggerClass;
import org.openqa.selenium.support.PageFactory;

public class EnvironmentSetup extends TataCliqBaseClass implements TataCliqObjects {
    public EnvironmentSetup() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "view_environment_selector")
    MobileElement env_options;

    @AndroidFindBy(id = "btn_click")
    MobileElement set_env_btn;

    /**
     * This will select the environment
     */
    private void selectEnvironment() {
        String env = ExcelFunctions.getValueFromExcel("Login_Details", "E", iRowNumber);
        CommonAppiumLib.clickAndSelectFromDropDownWithClassAsTextView(env_options, env);
        LoggerClass.println("----> Env selected : " + env);
    }

    /**
     * This will select the environment and click set env button
     */
    public EnvironmentSetup setEnvironment() {
        selectEnvironment();
        CommonAppiumLib.clickElement(set_env_btn);
        return this;
    }
}
