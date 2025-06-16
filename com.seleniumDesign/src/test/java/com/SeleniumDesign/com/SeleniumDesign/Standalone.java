package com.SeleniumDesign.com.SeleniumDesign;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumDesign.Listners.RetryListner;
import seleniumDesign.Setup.BaseTest;
import seleniumDesign.pageObject.CartPage;
import seleniumDesign.pageObject.CheckoutPage;
import seleniumDesign.pageObject.LandingPage;
import seleniumDesign.pageObject.ProductCatalog;
import seleniumDesign.pageObject.ThankyouPage;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Standalone extends BaseTest{
	
	@Test(dataProvider="dp",retryAnalyzer=RetryListner.class)
	public void urlOpen(HashMap<String , String> input) throws IOException {
		
		
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog =landingPage.loginIntoApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		
		CartPage cartpage = productCatalog.goToCart();
		Boolean match = cartpage.matchProductInCart(productName);
		
		Assert.assertTrue(match);
		String countryToSelect = "India";
		CheckoutPage checkoutpage = cartpage.clickOnCheckoutButton();
		checkoutpage.selectCountry(countryToSelect);
		ThankyouPage thankspage = checkoutpage.submitOrder();;
		String thanksText = thankspage.getThankyouMessage();
		Assert.assertEquals(thanksText, "THANKYOU FOR THE ORDER.");
		
		}
	
	
	
}
