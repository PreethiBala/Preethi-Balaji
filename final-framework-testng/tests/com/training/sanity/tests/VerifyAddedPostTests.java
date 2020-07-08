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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddPostPOM;
import com.training.pom.ContactFormPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class VerifyAddedPostTests  extends AdminLogInTests {

	public String baseUrl;
	public LoginPOM loginPOM;
	public AddPostPOM addPostPOM;
	public static Properties properties;
	public ScreenShot screenShot;

	/*@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}*/

	@Test (priority = 2)
	public void addCategory() throws InterruptedException  {
		addPostPOM = new AddPostPOM(driver);
		screenShot = new ScreenShot(driver); 
		
		
		
		//To add a new post
		addPostPOM.clickOnPost();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		addPostPOM.clickOnAddNew();
		addPostPOM.sendTitle("New Launches");
		addPostPOM.sendBody("New Launch in Home");

		Thread.sleep(5000);
		addPostPOM.clickPublishBtn(); 
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		addPostPOM.postPublishedMsg();

		//To verify the added post
		addPostPOM.clickOnAllPost();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.navigate().refresh();
		addPostPOM.sendPost("New Launches");			
		addPostPOM.clickSearchPost();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String actualResult = addPostPOM.searchedPost();                                    
		String expectedResult = "New Launches"; 
		screenShot.captureScreenShot("AddedPostDisplayed");
		Assert.assertEquals(actualResult, expectedResult);

		addPostPOM.clickOnSearchedPost();
		assertTrue(driver.getPageSource().contains("Edit Post"));
		screenShot.captureScreenShot("EditPostPage");
		String actualTitle = addPostPOM.getTitle(); 
		String expectedTitle ="New Launches"; 
		Assert.assertEquals(actualTitle, expectedTitle);

		String actualBody = addPostPOM.getBody(); 
		System.out.println(actualBody);
		String expectedBody = "New Launch in Home"; 
		Assert.assertEquals(actualBody, expectedBody);
		driver.switchTo().defaultContent();
	}
}
