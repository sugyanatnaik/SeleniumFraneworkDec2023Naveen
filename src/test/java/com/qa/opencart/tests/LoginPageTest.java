package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.Pages;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
//		String actTitle = loginPage.getLoginPageTitle();
		String actTitle = Pages.getPageObject(LoginPage.class).getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}

	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = Pages.getPageObject(LoginPage.class).getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);
	}

	@Test(priority = 3)
	public void forgotPwdLinkExist() {
		Assert.assertTrue(Pages.getPageObject(LoginPage.class).isForgotPwdLinkExist());
	}

	@Test(priority = 4)
	public void loginTest() {
		Pages.getPageObject(LoginPage.class).doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(Pages.getPageObject(AccountsPage.class).getAccPageTitle(),
				AppConstants.ACCOUNTS_PAGE_TITLE);
	}

}
