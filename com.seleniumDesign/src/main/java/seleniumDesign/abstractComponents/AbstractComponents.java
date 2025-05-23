package seleniumDesign.abstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	
	
	public void waitForElementToAppear(By locator) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForInvisibilityOfWebElement(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForWebElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForListWebElementToAppear(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
}
