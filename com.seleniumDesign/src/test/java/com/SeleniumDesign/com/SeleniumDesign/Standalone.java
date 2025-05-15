package com.SeleniumDesign.com.SeleniumDesign;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumDesign.pageObject.CartPage;
import seleniumDesign.pageObject.CheckoutPage;
import seleniumDesign.pageObject.LandingPage;
import seleniumDesign.pageObject.ProductCatalog;
import seleniumDesign.pageObject.ThankyouPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Standalone {
	
	@Test
	public void urlOpen() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String productName = "ZARA COAT 3";
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginIntoApplication("te100st@test.com", "12345678@Aa");
		ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		productCatalog.goToCart();
		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.matchProductInCart(productName);
		cartpage.clickOnCheckoutButton();
		Assert.assertTrue(match);
		
		String countryToSelect = "India";
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.selectCountry(countryToSelect);
		checkoutpage.submitOrder();
		ThankyouPage thankspage = new ThankyouPage(driver);
		String thanksText = thankspage.getThankyouMessage();
		Assert.assertEquals(thanksText, "THANKYOU FOR THE ORDER.");
		}
	
}
