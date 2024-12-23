package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	// Page class or Page Library
	private WebDriver driver;
	private ElementUtil eleUtil;

	Map<String, String> productMap = new HashMap<String, String>(); // TreeMap is used to store keys alphabetically, LinkedHashMap is used to store in same order

	// 1. Private By Locators
	private By productHeader = By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	// 2. Public Page class constructor

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eleUtil.doGetElementText(productHeader);
		System.out.println("The Product header is : " + header);
		return header;
	}

	public int getProductImagesCount() {
		int totalImages = eleUtil.waitForElementsVisible(images, 10).size();
		System.out.println("Image count for " + getProductHeader() + " is : " + totalImages);
		return totalImages;
	}

	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaList) {
			String text = e.getText();
			String metaKey = text.split(":")[0].trim();
			String metaValue = text.split(":")[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}

	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", price);
		productMap.put("exTaxPrice", exTaxPrice);

	}

	public Map<String, String> getProductDetailsMap() {
		productMap.put("header", getProductHeader());
		productMap.put("productImages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("The Product Details is : \n" + productMap);
		return productMap;

	}

}
