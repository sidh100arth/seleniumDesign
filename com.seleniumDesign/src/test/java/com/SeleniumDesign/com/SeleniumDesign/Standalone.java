package com.SeleniumDesign.com.SeleniumDesign;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Standalone {
	
	@Test
	public void urlOpen() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String productName = "ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("te100st@test.com");
		driver.findElement(By.id("userPassword")).sendKeys("12345678@Aa");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-sm-1")));
		List<WebElement> products = driver.findElements(By.cssSelector(".offset-sm-1"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b"))
						.getText()
						.equals("ZARA COAT 3"))
				.findFirst()
				.orElse(null);
		
		System.out.println(prod.findElement(By.cssSelector("b")).getText());
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.cssSelector("button[routerLink='/dashboard/cart']")).click();
		List<WebElement> productsInCart =  driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = productsInCart.stream().anyMatch(product->product.getText().equals(productName));
		Assert.assertTrue(match);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space(text())='Checkout']")));
		driver.findElement(By.xpath("//button[normalize-space(text())='Checkout']")).click();
		String countryToSelect = "India";
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(countryToSelect);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section .list-group")));
		List<WebElement> countries = driver.findElements(By.cssSelector("section .list-group button"));
		WebElement givenCountry = countries.stream()
				.filter(country->country.findElement(By.cssSelector("span"))
						.getText()
						.equalsIgnoreCase(countryToSelect))
				.findFirst()
				.orElse(null);
		
		givenCountry.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
		String thanksText = driver.findElement(By.cssSelector("h1")).getText().trim();
		Assert.assertEquals(thanksText, "THANKYOU FOR THE ORDER.");
		}
	
}
