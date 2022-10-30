package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	//2830-fatty
	//me- 2170
	DriverFactory df;
	public Properties prop;
	public WebDriver driver;

	public LoginPage lp;
	public AccountsPage ap;
	public SearchResultsPage srp;
	public ProductInfoPage pip;

	@BeforeTest
	public void setup() {

		/*		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		*/
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		lp= new LoginPage(driver);
	}

}
