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
import org.testng.annotations.AfterMethod;
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
import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.ContactFormPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


public class SubmitQueryUsingInvalidDetailsDBTest {
	public WebDriver driver;
	public String baseUrl;
	public ContactFormPOM contactFormPOM;
	public Properties properties;
	public ScreenShot screenShot;
	public GenericMethods genericMethods; 

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		   
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		//To start the test -"Launch Browser"
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		contactFormPOM = new ContactFormPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);
		String pageTitle=driver.getTitle();
	}

	@AfterMethod
	public void tearDown(ITestResult result)  throws Exception {
		Thread.sleep(1000);
		driver.quit();

	}
	@Test(dataProvider = "db-inputs", dataProviderClass = QueryValidDeatilsDataProviders.class)
		public void submitQueryTest(String name, String email, String subject, String message) throws InterruptedException, AWTException {
		
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
		screenShot.captureScreenShot("Email Incorrect");

		//emailFieldIsEmpty
		contactFormPOM.emailIncorrect();

		//Verify the error message
		String actualResult = contactFormPOM.verifyErrorMsg();
		String expectedResult = "One or more fields have an error. Please check and try again."; 
		Assert.assertEquals(actualResult, expectedResult);
		
	}

}