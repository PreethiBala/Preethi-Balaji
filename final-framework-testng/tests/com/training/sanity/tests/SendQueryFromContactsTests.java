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

		Actions act= new Actions(driver);
		WebElement blogSeection = driver.findElement(By.id("menu-item-617"));	
		act.contextClick(blogSeection).build().perform();
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(2000);

		robot.keyPress(KeyEvent.VK_ENTER);

		ArrayList<String> windowdetails=new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowdetails.get(1));
		Thread.sleep(2000);
		contactFormPOM.dropusLink();
		Thread.sleep(2000);
		contactFormPOM.sendName("manzoor");
		contactFormPOM.sendEmail("manzoor@gmail.com");
		contactFormPOM.sendSubject("apartments");
		contactFormPOM.sendMessage("looking for an apartments");
		System.out.println("new");
		contactFormPOM.clickSubmiteBtn();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		JavascriptExecutor js= (JavascriptExecutor)driver;

		js.executeScript("window.scrollBy(0,150)");
		Thread.sleep(5000);
		screenShot.captureScreenShot("RETC-012");


		String actualResult = contactFormPOM.verifyThankYouMsg();
		String expectedResult = "Thank you for your message. It has been sent. Message should get displayed"; 
		Assert.assertEquals(actualResult, expectedResult);
		//Application Defect
	}
}
