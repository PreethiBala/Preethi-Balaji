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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ContactFormPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SendQueryFromContactsTests {

	private WebDriver driver;
	private String baseUrl;
	private ContactFormPOM contactFormPOM;
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
		contactFormPOM = new ContactFormPOM(driver);
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
	public void validLoginTest() throws InterruptedException, AWTException {
		
		//Click on Blog section and open in a new window 
		contactFormPOM.openBlogInNewWindow();
		
		Thread.sleep(2000);
		contactFormPOM.dropusLink();
		Thread.sleep(2000);
		contactFormPOM.sendName("manzoosssssr");
		contactFormPOM.sendEmail("manzoor@gmail.com");
		contactFormPOM.sendSubject("apartments");
		contactFormPOM.sendMessage("looking for an apartments");
		System.out.println("new");
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
	}

}
