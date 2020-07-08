/**************************************************************
 * Script Name - AdminLogInTests
 * Description - To Verify whether application allows registered admin to login into applicationv
 * Date created - 28th June 2020
 * Developed by - Preethi IBM India
 * Last Modified By - Preethi IBM India
 * Last Modified Date - 2nd July 2020
 ***************************************************************/
package com.training.sanity.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AdminLogInTests {

	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;


	@BeforeClass
	public void setUp() throws IOException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/AdminLogin.html");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));   
		properties = new Properties();
		FileInputStream inStream1 = new FileInputStream("./resources/others.properties");
		properties.load(inStream1);

		extentTest = extent.startTest("Launch Browser");
		properties = new Properties();
		FileInputStream inStream2 = new FileInputStream("./resources/others.properties");
		properties.load(inStream2);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
		
		String pageTitle=driver.getTitle();
		extentTest.log(LogStatus.PASS, pageTitle);
		extent.endTest(extentTest);
	}
	/*@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}*/
	@AfterMethod
	public void tearDown(ITestResult result)  throws Exception {
		Thread.sleep(1000);		
		if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Failed" + result.getThrowable());
			
		/*
			String failureScreenshot =screenShot.captureScreenShot("RETC-012_Contact");
			extentTest.addScreenCapture(failureScreenshot);
			*/
			
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
			
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				extentTest.log(LogStatus.PASS, "Test passed");			
			}		
        extent.flush();
        extent.close();
        driver.quit();
		
	}
	@Test (priority = 1)
	public void validLoginTest() throws InterruptedException  {
		
		extentTest = extent.startTest("LogIn");
		
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		Thread.sleep(1000);
		screenShot.captureScreenShot("RETC-010_AdminLogin");

		//To verify whether admin user is successfully logged in 
		String actualResult = loginPOM.dashboardTxt();
		String expectedResult = "Dashboard"; 
		Assert.assertEquals(actualResult, expectedResult);
		
		extentTest.log(LogStatus.INFO, actualResult);
		extent.endTest(extentTest);
	}
}
