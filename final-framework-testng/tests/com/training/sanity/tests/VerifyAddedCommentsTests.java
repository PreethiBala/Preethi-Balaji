/**************************************************************
 * Script Name - VerifyAddedCommentsTests
 * Description - To verify whether application display comments added by the user in admin page
 * Pre-Condition - user should have launched the application by entering valid URL
 * admin should have added New Launch Post
 * Date created - 4th July 2020
 * Developed by - Preethi IBM India
 * Last Modified By -
 * Last Modified Date -
 ***************************************************************/
package com.training.sanity.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddCategoryPOM;
import com.training.pom.AddPostPOM;
import com.training.pom.CommentsInBlogPOM;
import com.training.pom.ContactFormPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
public class VerifyAddedCommentsTests extends VerifyAddedPostTests {
	public String baseUrl;
	public LoginPOM loginPOM;
	public AddPostPOM addPostPOM;
	public ContactFormPOM contactFormPOM;
	public CommentsInBlogPOM commentsInBlogPOM;
	public static Properties properties;
	public ScreenShot screenShot;


	@Test (priority = 2)
	public void addCategoryTest() throws InterruptedException, AWTException  {
		commentsInBlogPOM = new CommentsInBlogPOM(driver);
		screenShot = new ScreenShot(driver); 

		//To add a comment to the post added by Admin
		commentsInBlogPOM.clickOnrealEstate();
		commentsInBlogPOM.clickOnAdminPic();
		commentsInBlogPOM.clickLogOut();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		commentsInBlogPOM.blogSection();
		commentsInBlogPOM.readMoreLink();			
		commentsInBlogPOM.sendComment("Good");
		commentsInBlogPOM.sendName("pb");
		commentsInBlogPOM.sendEmail("pb@gmail.com");

		//To validate the posted comment
		commentsInBlogPOM.clickPostCommentBtn();
		screenShot.captureScreenShot("AddedComment_Blog");

		String actualResult = commentsInBlogPOM.verifyAddedComment();
		String expectedResult = "Good"; 
		Assert.assertEquals(actualResult, expectedResult);

	}
	@Test (priority = 3, dependsOnMethods="addCategoryTest")

	public void verifyAddedCommentsTest() throws IOException  {

		//To launch a new window and open admin site
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		addPostPOM = new AddPostPOM(driver);
		commentsInBlogPOM = new CommentsInBlogPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		driver.get(baseUrl);

		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");	
		loginPOM.clickLoginBtn(); 
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		//To verify the added comments
		commentsInBlogPOM.clickComments();		
		screenShot.captureScreenShot("AddedComment_AdminProfile");
		String actualResult = commentsInBlogPOM.verifyCommentIsAdded();
		String expectedResult = "Good"; 
		Assert.assertEquals(actualResult, expectedResult);

	}
}
