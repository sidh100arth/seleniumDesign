package seleniumDesign.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumDesign.abstractComponents.AbstractComponents;

public class ThankyouPage extends AbstractComponents{
	
	
	WebDriver driver;
	
	public ThankyouPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By thanks= By.cssSelector("h1");

	
	
	@FindBy(css="h1")
	WebElement thanksheader;
	
	public String getThankyouMessage() {
		
		waitForElementToAppear(thanks);
		return thanksheader.getText().trim();
		
	}
	

}
