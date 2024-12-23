package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	/*
	 * No TestNG code, no assertions
	 */
	
	// Page class or Page Library
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By Locators
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	// 2. Public Page class constructor

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, 5);
		System.out.println("Acc page title : " + title);
		return title;
	}

	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, 5);
		System.out.println("Acc page url : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, 10).isDisplayed();
	}

	public boolean isMyAccountLinkExist() {
		return eleUtil.waitForElementVisible(myAccountLink, 10).isDisplayed();
	}

	public List<String> getAccountsPageHeaderList() {
		List<WebElement> headersElementList = eleUtil.getElements(headers);
		List<String> headersList = new ArrayList<String>();
		for (WebElement e : headersElementList) {
			String header = e.getText();
			headersList.add(header);
		}
		return headersList;
	}

	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("searching for : " + searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

}
