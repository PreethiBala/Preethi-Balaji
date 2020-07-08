/**************************************************************
 * Script Name - VerifyAddedPostTests
 * Description - To verify whether application displays added post in blog section of home screen
 * Pre-Condition - user should have launched the application by entering valid URL
 * admin shacould get logged in
 * Date created - 2nd July 2020
 * Developed by - Preethi IBM India
 * Last Modified By - 
 * Last Modified Date - 
 ***************************************************************/
package com.training.sanity.tests;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.AddCategoryPOM;
import com.training.pom.AddPostPOM;
import com.training.pom.AddPropertyPOM;
import com.training.pom.CommentsInBlogPOM;
import com.training.pom.ContactFormPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CreatePropertyDetailsTest  {
	public WebDriver driver;
	public String baseUrl;
	public ExtentReports extent;
	public ExtentTest extentTest;
	public LoginPOM loginPOM;
	public AddCategoryPOM addCategoryPOM;
	public AddPropertyPOM addPropertyPOM;
	public AddPostPOM addPostPOM;
	public static Properties properties;
	public ScreenShot screenShot;

	@BeforeClass
	public void setUp() throws IOException {

		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/CreateProperty.html");
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
/*	}
	@Test (priority = 2)
	public void addCategory() throws InterruptedException  {
*/		
		extentTest = extent.startTest("AddFeature");
		
		addCategoryPOM = new AddCategoryPOM(driver);
		addPropertyPOM = new AddPropertyPOM(driver);
		addPostPOM = new AddPostPOM(driver);
		screenShot = new ScreenShot(driver); 

		addPropertyPOM.clickOnProperties();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(driver.getPageSource().contains("All Properties"));
		Assert.assertTrue(driver.getPageSource().contains("Add New"));
		Assert.assertTrue(driver.getPageSource().contains("Features"));
		Assert.assertTrue(driver.getPageSource().contains("Regions"));
		Assert.assertTrue(driver.getPageSource().contains("Properties Setting"));	
		addPropertyPOM.clickOnFeature();	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Features page with Add New Feature module along with existing feature should get displayed
		addCategoryPOM.addNewFeatureTitle();
		addCategoryPOM.existingFeaturtesSection();
		
		addCategoryPOM.sendName("New Launches");
		addCategoryPOM.sendSlug("launch");
		addCategoryPOM.sendDescription("New Launches of villas, apartments, flats");		
		addCategoryPOM.clickAddCategoryBtn();

		driver.navigate().refresh();
		addCategoryPOM.sendCategories("New Launches");
		addCategoryPOM.clickSearchCategories();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Added feature in existing feature module should get displayed
		String actualResults = addCategoryPOM.searchedName();
		String expectedResults = "New Launches"; 
		screenShot.captureScreenShot("AddedCategoryDisplayed");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertEquals(actualResults, expectedResults);
	
		extentTest.log(LogStatus.INFO, actualResults);
		extent.endTest(extentTest);
	
		
		extentTest = extent.startTest("AddNewFeature");
		addPropertyPOM.clickOnAddNew();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Add Property page should get displayed
		addCategoryPOM.addPropertyTitle();
		
		addPostPOM.sendTitle("prestige");
		addPostPOM.sendBody("home town");	
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		addPostPOM.clickOnnewLaunchChkBox();
		addPostPOM.newLaunchChkBoxSelected();
		addPostPOM.clickPublishBtn(); 
		Thread.sleep(5000);

		//To verify the just added post in Blog section
		addPostPOM.postPublishedMsg();		
		String actualComment = addPostPOM.postPublishedMsg1();
		String expectedComment = "Post published. View post\n" + 
				"Dismiss this notice.";
		Assert.assertEquals(actualComment , expectedComment);
		extentTest.log(LogStatus.INFO, actualComment);
		extent.endTest(extentTest);
		
		extentTest = extent.startTest("VerifyAddedFeature");
		addPropertyPOM.mouseOverNClickOnLogOut();
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
		addPropertyPOM.loginTitle();
		addPropertyPOM.realEstateTitle();
		addPropertyPOM.clickOnRealEstateTitle();
		addPropertyPOM.searchPropertiesField();
		addPropertyPOM.searchProperty("prestige");
		addPropertyPOM.searchedPropertyResult();
		
		Assert.assertTrue(driver.getPageSource().contains("prestige"));
		
		String actual = addPostPOM.getResult();
		String expected = "prestige";
		Assert.assertEquals(actual , expected);	
		extentTest.log(LogStatus.INFO, actual);
		extent.endTest(extentTest);
	}
}
