/**************************************************************
	 * Function Name - AddCategoryPOM()
	 * Description - This function will get the local web element details - Category Page
	 * Date created - 2nd July 2020
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

public class AddCategoryPOM {
	private WebDriver driver; 
	
	public AddCategoryPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath="//*[@class='wp-menu-image dashicons-before dashicons-admin-post']")
	private WebElement postLink; 
	
	@FindBy(xpath="//*[@href='edit-tags.php?taxonomy=category']")
	private WebElement categoriesLink; 
	
	@FindBy(xpath="//*[@class='wp-menu-name' and text()='Dashboard']")
	private WebElement dashboard;	
	
	@FindBy(xpath="//*[@class='wp-heading-inline']")
	private WebElement categoriesTitle;
	
	@FindBy(xpath="//*[@class='form-wrap']")  
	private WebElement addNewCategoryTxt; 
	
	@FindBy(xpath="//*[@class='form-wrap']")  
	private WebElement addNewFeatureTxt; 
		
	@FindBy(xpath="//*[@class='wp-heading-inline']")  
	private WebElement addPropertyTxt; 
	
	@FindBy(xpath="//*[@class='wp-list-table widefat fixed striped tags']")
	private WebElement existingCategories; 

	@FindBy(xpath="//*[@class='wp-list-table widefat fixed striped tags']")
	private WebElement existingFeaturtes; 
	
	@FindBy(id="tag-name")
	private WebElement tagName; 
	
	@FindBy(id="tag-slug")
	private WebElement tagSlug; 
	
	@FindBy(id="tag-description")
	private WebElement tagDescription; 
	
	@FindBy(id="submit")
	private WebElement addCategoryBtn; 
	
	@FindBy(id="tag-search-input")
	private WebElement categories;
	
	@FindBy(id="search-submit")
	private WebElement searchCategories;
	
	@FindBy(xpath="//*[@class='name column-name has-row-actions column-primary']")
	private WebElement searchedName; 
	
	public void clickOnPost() {
		this.postLink.click(); 
	}
	
	public void clickOnCategories() {
		this.categoriesLink.click(); 
	}
	
	public boolean categoriesTitle() {
		return this.categoriesTitle.isDisplayed();
	}
	
	public boolean addNewCategoryTitle() {
		return this.addNewCategoryTxt.isDisplayed();
	}
	
	public boolean existingCategoriesSection() {
		return this.existingCategories.isDisplayed();
	}
	
	public boolean addNewFeatureTitle() {
		return this.addNewFeatureTxt.isDisplayed();
	}
	
	public boolean existingFeaturtesSection() {
		return this.existingFeaturtes.isDisplayed();
	}
	
	public boolean addPropertyTitle() {
		return this.addPropertyTxt.isDisplayed();
	}
	
	public void sendName(String tagName) {
		this.tagName.clear();
		this.tagName.sendKeys(tagName);
	}
	
	public void sendSlug(String tagSlug) {
		this.tagSlug.clear();
		this.tagSlug.sendKeys(tagSlug);
	}
	public void sendDescription(String tagDescription) {
		this.tagDescription.clear();
		this.tagDescription.sendKeys(tagDescription);
	}
	
	public void clickAddCategoryBtn() {
		this.addCategoryBtn.click(); 
	}
	public void sendCategories(String categories) {
		this.categories.clear();
		this.categories.sendKeys(categories);
	}
	
	public void clickSearchCategories() {
		this.searchCategories.click(); 
	}
	
	public String searchedName() {
		return this.searchedName.getText();
	}
	
}
