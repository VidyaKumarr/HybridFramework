package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		srp = ap.performSearch("Macbook");
		pip = srp.selectProduct("MacBook Pro");
		String actProdHeader = pip.getProductHeader("MacBook Pro");
		Assert.assertEquals(actProdHeader, "MacBook Pro");
	}

	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] { { "Macbook", "MacBook Pro", AppConstants.MACBOOK_PRO_IMAGES_COUNT },
				{ "Macbook", "MacBook Air", AppConstants.MACBOOK_AIR_IMAGES_COUNT },
				{ "iMac", "iMac", AppConstants.IMAC_IMAGES_COUNT }, };
	}

	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchKey, String mainProductName, int ImagesCount) {
		srp = ap.performSearch(searchKey);
		pip = srp.selectProduct(mainProductName);
		int actProductImages = pip.getProductImagesCount();
		System.out.println("actual product images : " + actProductImages);
		Assert.assertEquals(actProductImages, ImagesCount);
	}

	@Test
	public void productMetaDataTest() {
		srp = ap.performSearch("Macbook");
		pip = srp.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = pip.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
	}

}
