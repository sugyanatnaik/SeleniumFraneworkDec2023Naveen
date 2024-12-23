package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void accPageURLTest() {
		String actURL = accPage.getAccPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}

	@Test(priority = 3)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test(priority = 4)
	public void isMyAccountLinkExistTest() {
		Assert.assertTrue(accPage.isMyAccountLinkExist());
	}

	@Test(priority = 5)
	public void accPageHeadersTest() {
		List<String> actHeadersList = accPage.getAccountsPageHeaderList();
		System.out.println(actHeadersList);

	}

	@Test(priority = 6)
	public void searchTest() {
		accPage.doSearch("macbook");
	}

}
