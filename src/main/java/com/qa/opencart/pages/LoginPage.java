package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotpasswordlink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@class ='btn btn-primary']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);

	}

	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("The title of this page is:" + title);
		return title;
	}

	public boolean getLoginPageURL() {
		String CurrentURL = eleutil.waitForUrlContains(AppConstants.DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		System.out.println("The login page url is:" + CurrentURL);
		if (CurrentURL.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}

	public boolean isForgotPwdLinkExist() {
		boolean flag = eleutil.doEleIsDisplayed(forgotpasswordlink);
		if (flag) {
			return true;
		}
		return false;
	}

	public AccountsPage doLogin(String username, String pwd) {
		eleutil.doSendKeysWithWait(emailid, AppConstants.DEFAULT_TIMEOUT, username);
		eleutil.doSendKeysWithWait(password, AppConstants.DEFAULT_TIMEOUT, pwd);
		eleutil.doClick(loginBtn);
		return new AccountsPage(driver);

	}
}
