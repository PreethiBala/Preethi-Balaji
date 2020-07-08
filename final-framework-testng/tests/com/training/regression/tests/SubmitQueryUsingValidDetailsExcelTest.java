package com.training.regression.tests;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.dataproviders.QueryValidDeatilsDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.ContactFormPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SubmitQueryUsingValidDetailsExcelTest {
	private WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	private String baseUrl;
	private ContactFormPOM contactFormPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		//To create extend report "sendQueryValidDetails.html" in tset-output folder
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/sendQueryValidDetails.html");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));   
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	@BeforeMethod
	public void setUp() throws Exception {

		//To start the test -"Launch Browser"
		extentTest = extent.startTest("Launch Browser");
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		contactFormPOM = new ContactFormPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
		String pageTitle=driver.getTitle();
		//To end test with info of actual result
		extentTest.log(LogStatus.PASS, pageTitle);
		extent.endTest(extentTest);
		//Every test should be ended before 2nd test gets executed
	}

	@AfterMethod
	public void tearDown(ITestResult result)  throws Exception {
		Thread.sleep(1000);	
		//Based on the end test result - To log pass in the report
		if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Failed" + result.getThrowable());

		}
		//To log skip in the report
		else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());

		} 
		//To log pass in the report
		else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test passed");			
		}		
		extent.flush();
		extent.close();
		driver.quit();

	}



	@Test(dataProvider = "excel-inputs", dataProviderClass = QueryValidDeatilsDataProviders.class)
	public void submitQueryTest(String name, String email, String subject, String message) throws InterruptedException, AWTException {
		//To start the test -"SendQueryValidDetails"
		extentTest = extent.startTest("SendQueryValidDetails");
		Thread.sleep(1000);
		contactFormPOM.openBlogInNewWindow();

		Thread.sleep(2000);
		contactFormPOM.dropusLink();
		Thread.sleep(2000);
		contactFormPOM.sendName(name);
		contactFormPOM.sendEmail(email);
		contactFormPOM.sendSubject(subject);
		contactFormPOM.sendMessage(message);

		contactFormPOM.clickSubmiteBtn();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		//To scroll the screen
		contactFormPOM.scrollTo();
		Thread.sleep(5000);
		screenShot.captureScreenShot("COnfirmation Message");

		//Verify the confirmation message
		String actualResult = contactFormPOM.verifyThankYouMsg();
		String expectedResult = "Thank you for your message. It has been sent. Message should get displayed"; 
		Assert.assertEquals(actualResult, expectedResult);
		//To end test with info of actual result
		extentTest.log(LogStatus.INFO, actualResult);
		extent.endTest(extentTest);
	}

}