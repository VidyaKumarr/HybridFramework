package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

private WebDriver driver;
private ElementUtil eleutil;
	
	private By producSearchLayout = By.cssSelector("div.product-layout");
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	
	public boolean isSearchSuccessful() {
		List<WebElement> searchList=eleutil.waitForElementsToBeVisible(producSearchLayout,AppConstants.DEFAULT_LARGE_TIMEOUT);
		if(searchList.size()>0) {
			System.out.println("The search is succesful");
			return true;
		}
		else {
			System.out.println("The search is not successful");
			return false;
		}
	}
	public ProductInfoPage selectProduct(String productName) {
		By mainPrName = By.linkText(productName);
		eleutil.doClick(mainPrName);
		return new ProductInfoPage(driver);

	}
}
