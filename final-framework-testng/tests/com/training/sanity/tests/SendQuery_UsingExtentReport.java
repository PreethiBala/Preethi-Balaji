/**************************************************************
 * Script Name - SendQueryFromContactsTests
 * Description - To Verify whether application allows user to send the query in Contact Form Page
 * Date created - 28th June 2020
 * Developed by - Preethi IBM India
 * Last Modified By - Preethi IBM India
 * Last Modified Date - 2nd July 2020
 ***************************************************************/
package com.training.sanity.tests;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.ContactFormPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SendQuery_UsingExtentReport {

	private WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	private String baseUrl;
	private ContactFormPOM contactFormPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	
	 @BeforeClass
	public void setUpBeforeClass() throws IOException {
		 
		 
		 extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/sendQuery.html");
	        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));   
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
	}

	@BeforeMethod
	public void setUp() throws Exception {
		   extentTest = extent.startTest("Launch Browser");
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		contactFormPOM = new ContactFormPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
		 String pageTitle=driver.getTitle();
		extentTest.log(LogStatus.PASS, pageTitle);
        extent.endTest(extentTest);
	}
	@AfterMethod
	public void tearDown(ITestResult result)  throws Exception {
		Thread.sleep(1000);		
		//Based on the end test result - To log info in the report 
				if(result.getStatus() == ITestResult.FAILURE) {
					extentTest.log(LogStatus.FAIL, "Test Failed" + result.getThrowable());

				}
				else if (result.getStatus() == ITestResult.SKIP) {
					extentTest.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());

				}
				else if (result.getStatus() == ITestResult.SUCCESS) {
					extentTest.log(LogStatus.PASS, "Test passed");			
				}		
        extent.flush();
        extent.close();
        driver.quit();
		
	}
	@Test
	public void submitQuery()  throws InterruptedException, AWTException {
	

		  extentTest = extent.startTest("send query");
		  
		//Click on Blog section and open in a new window 
		contactFormPOM.openBlogInNewWindow();
		
		Thread.sleep(2000);
		contactFormPOM.dropusLink();
		Thread.sleep(2000);
		contactFormPOM.sendName("manzoosssssr");
		contactFormPOM.sendEmail("manzoor@gmail.com");
		contactFormPOM.sendSubject("apartments");
		contactFormPOM.sendMessage("looking for an apartments");

		contactFormPOM.clickSubmiteBtn();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		//To scroll the screen
		contactFormPOM.scrollTo();
		Thread.sleep(5000);
		screenShot.captureScreenShot("RETC-012_Contact");
		
		//Verify the confirmation message
		String actualResult = contactFormPOM.verifyThankYouMsg();
		String expectedResult = "Thank you for your message. It has been sent. Message should get displayed"; 
		Assert.assertEquals(actualResult, expectedResult);
		
		//Application Defect
		
		extentTest.log(LogStatus.INFO, actualResult);
		extent.endTest(extentTest);
	}

}

