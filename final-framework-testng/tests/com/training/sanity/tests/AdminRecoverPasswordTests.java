package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.LostPasswordPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AdminRecoverPasswordTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private LostPasswordPOM lostPasswordPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);	
		loginPOM = new LoginPOM(driver); 
		lostPasswordPOM = new LostPasswordPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);  
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void recoveryPwdTest() {
		loginPOM.clickLoginLink();
		lostPasswordPOM.clickLostPwdLink();	
		loginPOM.sendUserName("admin");
		screenShot.captureScreenShot("RETC_010_SC1");
		lostPasswordPOM.clickLostPwdBtn();
		screenShot.captureScreenShot("RETC_010");

		String actualResult = lostPasswordPOM.verifyConfirmationMsg();
		String expectedResult = "A confirmation link has been sent to your email address. Message should get displayed & confirmation mail should be sent to registered mail id"; 
		Assert.assertEquals(actualResult, expectedResult);
		//Application defect
	}
}
