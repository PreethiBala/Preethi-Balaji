/**************************************************************
	 * Function Name - CommentsInBlogPOM()
	 * Description - This function will get the local web element details - Add new Comments Page
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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommentsInBlogPOM {
	private WebDriver driver; 
	
	public CommentsInBlogPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="menu-item-617")
	private WebElement blogSection; 
	
	@FindBy(xpath="//*[@class='read-more']")
	private WebElement readMoreLink; 
	
	@FindBy(id="comment")
	private WebElement comment;
	
	@FindBy(id="author")
	private WebElement name;	
	
	@FindBy(name="email")
	private WebElement email;
	
	@FindBy(id="submit")
	private WebElement postCommentBtn;
	
	@FindBy(xpath="//*[@class='headline margin-bottom-35']")
	private WebElement commentsSection; 
	
	@FindBy(xpath="//*[text() = 'Good']")
	private WebElement addedComment; 
	
	//*[@class='current menu-top menu-icon-comments' and @id='menu-comments']
	@FindBy(xpath="//*[@id='menu-comments']/a/div[3]")
	private WebElement commentsBlog; 
	
	@FindBy(xpath="//*[@id='the-comment-list']/tr[1]/td[2]/p")
	private WebElement comnmentAdded;
	 
	@FindBy(id="wp-admin-bar-site-name")
	private WebElement realEstate; 
	
	@FindBy(xpath="//*[@class='avatar avatar-32 photo']") 
	private WebElement adminPic; 
	
	@FindBy(xpath="//*[text()=' Log Out']")
	private WebElement logout;
	
	public void blogSection() {
		this.blogSection.click();
	}
	
	public void readMoreLink() {
		this.readMoreLink.click(); 
	}
	
	public void sendComment(String comment) {
		this.comment.clear();
		this.comment.sendKeys(comment);
	}
	
	public void sendName(String name) {
		this.name.clear();
		this.name.sendKeys(name);
	}
	public void sendEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}
	public void clickPostCommentBtn() {
		this.postCommentBtn.click(); 
	}
	public String verifyAddedComment() {
		return this.addedComment.getText();
	}
	public void clickComments() {
		this.commentsBlog.click(); 
	}
	public String verifyCommentIsAdded() {
		return this.comnmentAdded.getText();
	}
	
	public void clickOnrealEstate() {
		this.realEstate.click(); 
	}
	public void clickOnAdminPic() {
		this.adminPic.click(); 
	}
	public void clickLogOut() {
		this.logout.click(); 
	}
	
}
