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

public class AddPostPOM {
	private WebDriver driver; 
	
	public AddPostPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath="//*[@class='wp-menu-image dashicons-before dashicons-admin-post']")
	private WebElement postLink; 
	
	@FindBy(xpath="//*[@href='post-new.php']")
	private WebElement addNew; 
	
	@FindBy(xpath="//*[@class='wp-heading-inline']")
	private WebElement addNewPostTitle;	
	
	@FindBy(xpath="//*[@class='wp-heading-inline']")
	private WebElement categoriesTitle;
	
	@FindBy(id="title")
	private WebElement title; 
	 
	@FindBy(id="content")
	private WebElement body; 
	
	@FindBy(id="tinymce")
	private WebElement body1; 
	
	//*[@class='button button-primary button-large' and @name='publish' and @id='publish']
	//*[@id='publish'] 
	//*[@value='Publish']
	@FindBy(xpath="//*[@class='submitbox']/div[2]/div[2]/input[2]")
	private WebElement publishBtn; 
	
	@FindBy(xpath="//*[@class='updated notice notice-success is-dismissible']")
	private WebElement postPublishedMsg; 
	
	@FindBy(xpath="//*[@class='wp-first-item current']")
	private WebElement allPost; 
	
	@FindBy(id="post-search-input")
	private WebElement post; 
	
	@FindBy(id="search-submit")
	private WebElement searchPost; 
	
	//@FindBy(xpath="//*[@class='title column-title has-row-actions column-primary page-title']")
	//@FindBy(xpath="//*[@data-colname='Title']")
	@FindBy(xpath="//*[@id=\"posts-filter\"]/table/tbody/tr[1]/td[1]/strong")
	private WebElement searchedPost; 
	
	
	//a[text()='New Launches']/@href
	@FindBy(xpath="//*[@aria-label='“New Launches” (Edit)']") 
	private WebElement searchedPost1; 
	
	@FindBy(xpath="//*[@class='wp-heading-inline']")
	private WebElement editPost; 
	
	@FindBy(xpath="//*[text()=' New Launches']")
	private WebElement newLaunchChkBox; 
	
	@FindBy(xpath="//*[@id='message']//a")
	private WebElement viewPost; 
	
	@FindBy(xpath="//*[@class='blog-page']/div/div[1]/div[1]/div/p")
	private WebElement addedComment; 
	
	
	public void clickOnPost() {
		this.postLink.click(); 
	}
	
	public void clickOnAddNew() {
		this.addNew.click(); 
	}
	
	public void sendTitle(String title) {
		this.title.clear();
		this.title.sendKeys(title);
	}
	
	public void sendBody(String body) {
		this.body.clear();
		this.body.sendKeys(body);
	}
	
	public void sendBody1(String body) {
		driver.switchTo().frame(0);
		this.body.sendKeys(body);
		driver.switchTo().parentFrame();
	}
	
	public void clickPublishBtn() {
		Actions action = new Actions(driver);
        action.doubleClick(publishBtn).build().perform();
		
	}
	
	public boolean postPublishedMsg() {
		return this.postPublishedMsg.isDisplayed();
	}
	
	public void clickOnAllPost() {
		this.allPost.click(); 
	}
	
	public void sendPost(String post) {
		this.post.clear();
		this.post.sendKeys(post);
	}
	
	public void clickSearchPost() {
		this.searchPost.click(); 
	}
	
	public String searchedPost() {
		return this.searchedPost.getText();
	}
	
	public void clickOnSearchedPost() {
		this.searchedPost1.click(); 
	}
	
	//To be removed
	public void clickOnSearchedPost1(List<WebElement> searchedPost1) {
		Actions action = new Actions(driver);
        action.doubleClick(searchedPost).perform();
		
	}
	
	public boolean editPostPage() {
		return this.editPost.isDisplayed();
	}
	
	public String getTitle() {
		return this.title.getAttribute("value");
	}
	
	public String getBody() {
		return this.body.getText();
	}	
	
	public String getBody1() {	
		driver.switchTo().frame("content_ifr");     
		return this.body.getText();
	
	}
	
	public void clickOnnewLaunchChkBox() {
		this.newLaunchChkBox.click(); 
	}
	public void clickOnViewPost() {
		this.viewPost.click(); 
	}
	
	public String addedComment() {	    
		return this.addedComment.getText();
	
	}
}
