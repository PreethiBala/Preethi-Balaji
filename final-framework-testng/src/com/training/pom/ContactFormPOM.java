/**************************************************************
	 * Function Name - LoginPOM()
	 * Description - This function will get the local web element details - Contact From Page
	 * Date created - 28th June 2020
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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	
	/**************************************************************
	 * Function Name - scrollTo()
	 * Description -To scroll the screen using JavaScriptExecutor with a dimension of (0, 150)
	 * Date created - 2nd July 2020 
	 * Developed by - Preethi IBM India
	 * Last Modified By - 
	 * Last Modified Date - 
	 * 
	 * @throws Exception
	 ***************************************************************/
	public void scrollTo() {	
	JavascriptExecutor js= (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,150)");
	}
	
	/**************************************************************
	 * Function Name - openBlogInNewWindow()
	 * Description -To click on Blog section and launch in a new tab
	 * Date created - 2nd July 2020 
	 * Developed by - Preethi IBM India
	 * Last Modified By - 
	 * Last Modified Date - 
	 * 
	 * @throws Exception
	 ***************************************************************/
	public void openBlogInNewWindow() throws AWTException, InterruptedException {
	
	Actions act= new Actions(driver);
	act.contextClick(blogSection).build().perform(); 
	Robot robot= new Robot();
	robot.keyPress(KeyEvent.VK_DOWN);
	Thread.sleep(2000);

	robot.keyPress(KeyEvent.VK_ENTER);

	ArrayList<String> windowdetails=new ArrayList<>(driver.getWindowHandles());
	driver.switchTo().window(windowdetails.get(1));
	
	}
}
