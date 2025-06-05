package seleniumDesign.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumDesign.abstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{

	
	public WebDriver driver;
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	By productLocator = By.cssSelector("b");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By addToCartToast = By.cssSelector("#toast-container");
	
	@FindBy(css=".offset-sm-1")
	List<WebElement> products;

	@FindBy(css=".ng-animating")
	WebElement animation;
	
	@FindBy(css="button[routerLink='/dashboard/cart']")
	WebElement cartButton;
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(By.cssSelector(".offset-sm-1"));
		return products;
	}
	
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(productLocator)
						.getText()
						.equals(productName))
				.findFirst()
				.orElse(null);
		return prod;
	}
	
	
	public void addProductToCart(String productName) {
		
		getProductByName(productName).findElement(addToCart).click();
		
	}
	
	
	public CartPage goToCart() {
		waitForInvisibilityOfWebElement(animation);
		waitForElementToAppear(addToCartToast);
		cartButton.click();
		CartPage cartPge = new CartPage(driver);
		return cartPge;
	}
}
