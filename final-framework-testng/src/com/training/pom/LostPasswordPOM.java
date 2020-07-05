/**************************************************************
	 * Function Name - LostPasswordPOM()
	 * Description - This function will get the local web element details - Password Recovery Page
	 * Date created - 28th June 2020
	 * Developed by - Preethi IBM India
	 * Last Modified By - 
	 * Last Modified Date - 
	 * @throws Exception
	 * **************************************************************/
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LostPasswordPOM {
	private WebDriver driver; 
	
	public LostPasswordPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@href='http://realty-real-estatem1.upskills.in/wp-login.php?action=lostpassword']")
	private WebElement lostPwdLink; 
	
	@FindBy(xpath="//*[@class='lostpassword-button']")
	private WebElement lostPwdBtn; 
	
	@FindBy(xpath="//*[@class='col-md-12'][1]/h2")
	private WebElement lostPwdTxt; 
	
	@FindBy(xpath="//*[contains(text(),'The email could not be sent.')]")
	private WebElement confirmationMsg; 
	
	
	public void clickLostPwdLink() {
		this.lostPwdLink.click(); 
	}
	
	public void clickLostPwdBtn() {
		this.lostPwdBtn.click(); 
	}
	
	public String verifyConfirmationMsg() {
		return this.confirmationMsg.getText();
	}
}
