package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactFormPOM {
	private WebDriver driver; 
	
	public ContactFormPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(id="menu-item-617")
	private WebElement blogSection; 
	
	@FindBy(xpath="//*[@href='/contact/']")
	private WebElement dropusLink; 
	
	@FindBy(xpath="//*[@class='headline margin-top-15 margin-bottom-35     ' and text()='Contact Form']")
	private WebElement contactFormTxt; 
	
	@FindBy(name="name")
	private WebElement name; 
	
	@FindBy(name="email")
	private WebElement email; 
	
	@FindBy(name="subject")
	private WebElement subject; 
	
	@FindBy(xpath="//*[@class='wpcf7-form-control wpcf7-textarea wpcf7-validates-as-required']")
	private WebElement message; 
	
	@FindBy(xpath="//*[@type='submit']")
	private WebElement submiteBtn; 
	
	@FindBy(xpath="//*[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng']")
	private WebElement thankYouMsg; 
	
	public void blogSection() {
		this.blogSection.click(); 
	}
	
	public void dropusLink() {
		this.dropusLink.click(); 
	}
	
	public void sendName(String name) {
		this.name.clear();
		this.name.sendKeys(name);
	}
	
	public void sendEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}
	public void sendSubject(String subject) {
		this.subject.clear();
		this.subject.sendKeys(subject);
	}
	
	public void sendMessage(String message) {
		this.message.clear(); 
		this.message.sendKeys(message); 
	}
	
	public void clickSubmiteBtn() {
		this.submiteBtn.click(); 
	}
	public String verifyThankYouMsg() {
		return this.thankYouMsg.getText();
	}
}
