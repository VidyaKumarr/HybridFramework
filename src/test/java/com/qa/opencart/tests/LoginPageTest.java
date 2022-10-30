package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{

	@Test(priority=1)
	public void loginPageTitleTest() {
		String actualTitle= lp.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void loginPageURLTest() {
		Assert.assertTrue(lp.getLoginPageURL());
	}
	
	@Test(priority=3)
	public void isForgotPwdLinkExist() {
		Assert.assertTrue(lp.isForgotPwdLinkExist());
	}
	
	@Test(priority=4)
	public void doLoginTest() {
		ap=lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(ap.isLogoutLinkExist());
	}
}
