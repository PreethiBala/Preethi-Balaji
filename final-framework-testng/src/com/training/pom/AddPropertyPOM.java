/**************************************************************
	 * Function Name - AddPostPOM()
	 * Description - This function will get the local web element details - Add new Post Page
	 * Date created - 2nd July 2020
	 * Developed by - Preethi IBM India
	 * Last Modified By - 
	 * Last Modified Date - 
	 * @throws Exception
	 * **************************************************************/
package com.training.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPropertyPOM {
	public WebDriver driver; 
	
	public AddPropertyPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath="//*[@id='menu-posts-property']/a/div[3]")
	private WebElement property; 
	

	@FindBy(xpath="//*[@class='wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-property']/ul/li[4]/a")
	private WebElement feature; 
	
	@FindBy(xpath="//*[@class='wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-property']/ul/li[3]/a")
	private WebElement addnew; 
	
	@FindBy(id="col-left")
	private WebElement addNewFeature; 
	
	@FindBy(xpath="//*[@class='wp-list-table widefat fixed striped tags']")
	private WebElement existingFeature; 
	
	@FindBy(xpath="//*[@class='active']")
	private WebElement loginTitle;	
	
	@FindBy(xpath="//*[@title='Real Estate']")
	private WebElement realEstateTitle;
	
	@FindBy(xpath="//*[@class='wp-heading-inline']")
	private WebElement addPropertyPage;
	
	@FindBy(xpath="//*[@class='headline margin-top-25 margin-bottom-20     ']")
	private WebElement realEstatePage;
	
	@FindBy(xpath="//*[@title='Search input']")
	private WebElement searchProperties;
	
	@FindBy(xpath="//*[@class='overlap']")
	private WebElement searchedProperty;
	
	
	@FindBy(id="wp-admin-bar-my-account")
	private WebElement userProfile; 
	 
	@FindBy(id="wp-admin-bar-logout")
	private WebElement adminLogout; 
	
	
	
	
	public void clickOnProperties() {
		this.property.click(); 
	}
	
	public void clickOnFeature() {
		this.feature.click(); 
	}
	
	public void clickOnAddNew() {
		this.addnew.click(); 
	}
	
	public boolean addNewFeatureTitle() {
		return this.addNewFeature.isDisplayed();
	}
	
	public boolean existingFeatureSection() {
		return this.existingFeature.isDisplayed();
	}
	
	public boolean loginTitle() {
		return this.loginTitle.isDisplayed();
	}
	
	public boolean realEstateTitle() {
		return this.realEstateTitle.isDisplayed();
	}
	
	public void clickOnRealEstateTitle() {
		this.realEstateTitle.click(); 
	}
	
	public boolean addPropertyPage() {
		return this.addPropertyPage.isDisplayed();
	}
	
	public boolean realEstatePage() {
		return this.realEstatePage.isDisplayed();
	}
	
	public boolean searchPropertiesField() {
		return this.searchProperties.isDisplayed();
	}
	
	public boolean searchedPropertyResult() {
		return this.searchedProperty.isDisplayed();
	}
	public void clickOnuserProfile() {
		this.userProfile.click(); 
	}

	public void clickOnAdminLogout() {
		this.adminLogout.click(); 
	}
		
	public void mouseOverNClickOnLogOut() throws InterruptedException {
		Actions act=new Actions(driver);
		act.moveToElement(userProfile).build().perform();
		Thread.sleep(3000);
		act.moveToElement(adminLogout).click().perform();
		
	}
	
	public void searchProperty(String post) {
		this.searchProperties.clear();
		this.searchProperties.sendKeys(post);
	}
	
}
