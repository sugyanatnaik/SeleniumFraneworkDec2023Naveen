package com.qa.opencart.pages;

import com.qa.opencart.factory.DriverFactory;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    public static <TPage> TPage getPageObject(Class<TPage> pageClass) {
        try {
            return PageFactory.initElements(DriverFactory.getDriver(), pageClass);
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
}
