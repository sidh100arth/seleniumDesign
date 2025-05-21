package seleniumDesign.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumDesign.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
		
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
//	List<WebElement> productsInCart =  driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	List<WebElement> productsInCart;
	
	@FindBy(xpath="//button[normalize-space(text())='Checkout']")
	WebElement checkOutButton;
	
	By pathcheckOutButton = By.xpath("//button[normalize-space(text())='Checkout']");
	
	public Boolean matchProductInCart(String product) {
		waitForListWebElementToAppear(productsInCart);
		return productsInCart.stream().anyMatch(productCart->productCart.getText().equalsIgnoreCase(product));
	}
	
	public CheckoutPage clickOnCheckoutButton() {
		waitForElementToAppear(pathcheckOutButton);
		checkOutButton.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
	
	
}
