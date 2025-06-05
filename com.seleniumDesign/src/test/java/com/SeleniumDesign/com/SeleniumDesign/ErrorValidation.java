package com.SeleniumDesign.com.SeleniumDesign;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import seleniumDesign.Setup.BaseTest;
import seleniumDesign.pageObject.CartPage;
import seleniumDesign.pageObject.ProductCatalog;

public class ErrorValidation extends BaseTest{

	WebDriver driver;
	
	@Test(dataProvider="dp")
	public void checkErrorMessage(HashMap<String , String> input) throws IOException {
		
	
		landingPage.loginIntoApplication(input.get("email"), input.get("password"));
		String errorMessage = landingPage.getErrorMessage();
		Assert.assertEquals(errorMessage,"Incorrect email or password.");
	}
	
	@Test(dataProvider="dp")
	public void productErrorValidation(HashMap<String , String> input) {
		
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog =landingPage.loginIntoApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		
		CartPage cartpage = productCatalog.goToCart();
		Boolean match = cartpage.matchProductInCart(productName);
		
		Assert.assertTrue(match);
	}
	
}
