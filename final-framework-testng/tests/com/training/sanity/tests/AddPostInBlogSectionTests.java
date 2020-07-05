/**************************************************************
 * Script Name - AddPostInBlogSectionTests
 * Description - To verify whether application displays added post in all post - RETC_040
 * Pre-Condition - User should have launched the application by entering valid URL
 * Admin should be logged in
 * Date created - 2nd July 2020
 * Developed by - Preethi IBM India
 * Last Modified By - 
 * Last Modified Date - 
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddCategoryPOM;
import com.training.pom.AddPostPOM;
import com.training.pom.ContactFormPOM;
import com.training.pom.LoginPOM;

import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddPostInBlogSectionTests extends AdminLogInTests {

	public String baseUrl;
	public LoginPOM loginPOM;
	public AddCategoryPOM addCategoryPOM;
	public AddPostPOM addPostPOM;
	public static Properties properties;
	public ScreenShot screenShot;

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test (priority = 2)
	public void addCategory() throws InterruptedException  {
		addCategoryPOM = new AddCategoryPOM(driver);
		addPostPOM = new AddPostPOM(driver);
		screenShot = new ScreenShot(driver); 

		//To add a new Category 
		addCategoryPOM.clickOnPost();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertTrue(driver.getPageSource().contains("All Posts"));
		Assert.assertTrue(driver.getPageSource().contains("Add New"));
		Assert.assertTrue(driver.getPageSource().contains("Categories"));
		Assert.assertTrue(driver.getPageSource().contains("Tags"));

		addCategoryPOM.clickOnCategories();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		addCategoryPOM.categoriesTitle();
		addCategoryPOM.addNewCategoryTitle();
		addCategoryPOM.existingCategoriesSection();
		screenShot.captureScreenShot("CategoriesScreen");

		//Add the category details
		addCategoryPOM.sendName("New Launches");
		addCategoryPOM.sendSlug("launch");
		addCategoryPOM.sendDescription("New Launches of villas, apartments, flats");		
		addCategoryPOM.clickAddCategoryBtn();

		driver.navigate().refresh();
		addCategoryPOM.sendCategories("New Launches");
		addCategoryPOM.clickSearchCategories();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String actualResult = addCategoryPOM.searchedName();
		String expectedResult = "New Launches"; 
		screenShot.captureScreenShot("AddedCategoryDisplayed");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Assert.assertEquals(actualResult, expectedResult);

		//To add a new Post
		addPostPOM.clickOnPost();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		addPostPOM.clickOnAddNew();
		addPostPOM.sendTitle("New Launches");
		addPostPOM.sendBody(" New Launch in Home");
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		addPostPOM.clickOnnewLaunchChkBox();
		addPostPOM.clickPublishBtn(); 
		Thread.sleep(5000);

		//To verify the just added post in Blog section
		addPostPOM.postPublishedMsg();
		addPostPOM.clickOnViewPost();		
		String actualComment = addPostPOM.addedComment();
		System.out.println( actualResult);
		String expectedComment = "New Launch in Home";
		Assert.assertEquals(actualComment , expectedComment);

	}
}
