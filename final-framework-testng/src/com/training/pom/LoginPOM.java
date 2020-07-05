/**************************************************************
	 * Function Name - LoginPOM()
	 * Description - This function will get the local web element details - Log In Page
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

public class LoginPOM {
	private WebDriver driver; 
	
	public LoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@href='http://realty-real-estatem1.upskills.in/my-profile/' and @class='sign-in']")
	private WebElement loginLink; 
	
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//*[@class='wp-menu-name' and text()='Dashboard']")
	private WebElement dashboardTxt; 
	
	public void clickLoginLink() {
		this.loginLink.click(); 
	}
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	public String dashboardTxt() {
		return this.dashboardTxt.getText();
	}
}
