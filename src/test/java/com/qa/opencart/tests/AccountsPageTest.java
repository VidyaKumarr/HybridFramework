package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void getAccountsPageTitleTest() {
		String actualAccPageTitle = ap.getAccountsPageTitle();
		Assert.assertEquals(actualAccPageTitle, AppConstants.ACC_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void getAccountsPageURLTest() {
		Assert.assertTrue(ap.getAccountsPageURL());
	}

	@Test(priority = 3)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(ap.isLogoutLinkExist());
	}

	@Test(priority = 4)
	public void isSearchBoxExistTest() {
		Assert.assertTrue(ap.isSearchBoxExist());

	}

	@Test(priority = 5)
	public void getSectionHeadersListTest() {
		ArrayList<String> actualSecHeaderList = ap.getSectionHeadersList();
		System.out.println("The headers available in Accounts page:" + actualSecHeaderList);
		Assert.assertEquals(actualSecHeaderList, AppConstants.ACC_PAGE_SECTION_HEADERS);
	}

	@DataProvider
	public Object[][] getProductKey() {
		return new Object[][] { { "Macbook" }, { "iMac" }, { "Samsung" } };
	}

	@Test(dataProvider = "getProductKey", priority=6)
	public void performSearchTest(String productKey) {
		srp = ap.performSearch(productKey);
		Assert.assertTrue(srp.isSearchSuccessful());
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
				{ "Macbook", "MacBook Pro" },
				{ "Macbook", "MacBook Air" },
				{ "iMac", "iMac" },
				{"Samsung", "Samsung SyncMaster 941BW"},
				{"Samsung", "Samsung Galaxy Tab 10.1"}
				};
	}

	@Test(dataProvider = "getProductData", priority = 7)
	public void searchTest(String searchKey, String mainProductName) {
		srp = ap.performSearch(searchKey);
		if (srp.isSearchSuccessful()) {
			pip = srp.selectProduct(mainProductName);
			String actualProductHeader = pip.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductHeader, mainProductName);
		}
	}
	
	

}
