package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.Pages;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.utils.ExcelUtil;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void infoPageSetUp() {
		Pages.getPageObject(LoginPage.class).doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getProductSearchData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" },
				{ "samsung", "Samsung SyncMaster 941BW" }, { "samsung", "Samsung Galaxy Tab 10.1" } };
	}

	@Test(priority = 1, enabled = true, dataProvider = "getProductSearchData")
	public void productHeaderTest(String searchKey, String productName) {
		Pages.getPageObject(AccountsPage.class).doSearch(searchKey);
		Pages.getPageObject(SearchResultsPage.class).selectProduct(productName);
		Assert.assertEquals(Pages.getPageObject(ProductInfoPage.class).getProductHeader(), productName);
	}

	// ********** Start *************

	@DataProvider
	public Object[][] getProductImagesDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}

	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] { { "macbook", "MacBook Pro", "4" }, { "imac", "iMac", "3" },
				{ "samsung", "Samsung SyncMaster 941BW", "1" }, { "samsung", "Samsung Galaxy Tab 10.1", "7" } };
	}

	@Test(priority = 2, enabled = true, dataProvider = "getProductImagesData")
	public void productImagesCountTest(String searchKey, String productName, String imagesCount) { // imagesCount is made as String as excel sheet gives numbers as string. To make things equal, the data provider is also made to Sting i.e. "4"
		Pages.getPageObject(AccountsPage.class).doSearch(searchKey);
		Pages.getPageObject(SearchResultsPage.class).selectProduct(productName);
		Assert.assertEquals(Pages.getPageObject(ProductInfoPage.class).getProductImagesCount(), Integer.parseInt(imagesCount));
	}
// ********** End *************

	@Test(priority = 3, enabled = true)
	public void productInfoTest() {
		Pages.getPageObject(AccountsPage.class).doSearch("macbook");
		Pages.getPageObject(SearchResultsPage.class).selectProduct("MacBook Pro");
		Assert.assertEquals(Pages.getPageObject(ProductInfoPage.class).getProductDetailsMap().get("Brand"), "Apple");
		softAssert.assertEquals(Pages.getPageObject(ProductInfoPage.class).getProductDetailsMap().get("Brand"),
				"Apple");
		softAssert.assertEquals(Pages.getPageObject(ProductInfoPage.class).getProductDetailsMap().get("Product Code"),
				"Product 18");
		softAssert.assertEquals(Pages.getPageObject(ProductInfoPage.class).getProductDetailsMap().get("Availability"),
				"In Stock");
		softAssert.assertEquals(Pages.getPageObject(ProductInfoPage.class).getProductDetailsMap().get("productPrice"),
				"$2,000.00");
		softAssert.assertEquals(Pages.getPageObject(ProductInfoPage.class).getProductDetailsMap().get("exTaxPrice"),
				"$2,000.00");
		softAssert.assertAll();
	}
}
