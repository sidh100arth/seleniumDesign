package seleniumDesign.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumDesign.abstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{

	
	WebDriver driver;
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".offset-sm-1")
	List<WebElement> products;
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(By.cssSelector(".offset-sm-1"));
		return products;
	}
}
