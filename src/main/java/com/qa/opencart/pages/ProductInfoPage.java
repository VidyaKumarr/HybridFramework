package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private Map<String, String> productInfoMap;
	
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		eleutil = new ElementUtil(driver); 
	}
	
	
	public String getProductHeader(String mainProductName) {
		String xpath = "//h1[text()='"+mainProductName+"']";
		String productHeader = eleutil.doGetText(By.xpath(xpath));
		System.out.println("product header is : " + productHeader);
		return productHeader;
	}
	
	public int getProductImagesCount() {
		return eleutil.waitForElementsToBeVisible(productImages, AppConstants.DEFAULT_TIMEOUT).size();
	}
	
	public String getProductPageTitle(String productTitle) {
		return eleutil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT, productTitle);
	}
	
	public String getProductPageURL(String searchKey) {
		return eleutil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT, searchKey);
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	public Map<String, String> getProductMetaData() {
		List<WebElement> metalist = eleutil.getElements(productMetaData);
		productInfoMap = new LinkedHashMap<String, String>();
		for(WebElement e : metalist) {
			String metaText = e.getText();
			String meta[] = metaText.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		productInfoMap.forEach((k,v) -> System.out.println(k+ ":" +v));
		return productInfoMap;
	}

}
