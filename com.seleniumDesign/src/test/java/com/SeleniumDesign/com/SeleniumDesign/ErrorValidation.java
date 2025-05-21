package com.SeleniumDesign.com.SeleniumDesign;

import org.testng.annotations.Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumDesign.Setup.BaseTest;
import seleniumDesign.pageObject.LandingPage;

public class ErrorValidation extends BaseTest{

	WebDriver driver;
	
	@Test
	public void checkErrorMessage() throws IOException {
		
	
		landingPage.loginIntoApplication("te1sdfsf00st@test.com", "12345678@Aa");
		String errorMessage = landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", errorMessage);
	}
}
