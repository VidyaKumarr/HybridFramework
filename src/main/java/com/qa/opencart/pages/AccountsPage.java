package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By logoutLink = By.linkText("Logout");
	private By secHeader= By.xpath("//div[@class='col-sm-9']/h2[text()]");
	private By searchbox= By.name("search");
	private By searchIcon = By.xpath("//button[@class='btn btn-default btn-lg']");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);

	}
	
	public String getAccountsPageTitle() {
		String title =eleutil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT,AppConstants.ACC_PAGE_TITLE);
		System.out.println("The title of this page is:"+title);
		return title;
	}
	
	public boolean getAccountsPageURL() {
		String CurrentURL=eleutil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT,AppConstants.ACC_PAGE_URL_PARAM);
		System.out.println("The accoutns page url is:"+CurrentURL);
		if(CurrentURL.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	
	public boolean isLogoutLinkExist() {
		boolean flag = eleutil.doEleIsDisplayed(logoutLink);
		if(flag) {
			return true;
		}
		return false;
	}
	
	public boolean isSearchBoxExist() {
		boolean flag = eleutil.doEleIsDisplayed(searchbox);
		if(flag) {
			return true;
		}
		return false;
	}
	
	public ArrayList<String>  getSectionHeadersList() {
		List<WebElement> secHeadersList = eleutil.waitForElementsToBeVisible(secHeader,AppConstants.DEFAULT_TIMEOUT);
		System.out.println("The number of headers available in the account page section are:"+secHeadersList.size());
		ArrayList<String> secHeaderTextList=new ArrayList<String>();
		for(WebElement e:secHeadersList) {
			String Text = e.getText();
			secHeaderTextList.add(Text);
		}
		return secHeaderTextList;	
		
	}
	
	public SearchResultsPage performSearch(String productKey) {
		if(isSearchBoxExist()) {
			eleutil.doSendKeys(searchbox, productKey);
			eleutil.doClick(searchIcon);
			return new SearchResultsPage(driver);

		}
		else {
			System.out.println("The search field is not available:");
			return null;
		}
	}

}
