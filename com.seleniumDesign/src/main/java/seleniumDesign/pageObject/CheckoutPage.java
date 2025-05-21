package seleniumDesign.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumDesign.abstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{

	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By countryList = By.cssSelector("section .list-group");
	
	
	@FindBy(css="section .list-group button")
	List<WebElement> countries;
	
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	
	@FindBy(css=".action__submit")
	WebElement submitOrderButton;
	
	
	public void selectCountry(String countryToSelect) {
		country.sendKeys(countryToSelect);
		waitForElementToAppear(countryList);
		WebElement givenCountry = countries.stream()
				.filter(country->country.findElement(By.cssSelector("span"))
						.getText()
						.equalsIgnoreCase(countryToSelect))
				.findFirst()
				.orElse(null);
		givenCountry.click();
	}
	
	public ThankyouPage submitOrder() {
		submitOrderButton.click();
		ThankyouPage thankspage = new ThankyouPage(driver);
		return thankspage;
	}
	
}
