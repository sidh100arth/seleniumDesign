package com.SeleniumDesign.com.SeleniumDesign;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Standalone {
	
	@Test
	public void urlOpen() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("te100st@test.com");
		driver.findElement(By.id("userPassword")).sendKeys("12345678@Aa");
		driver.findElement(By.id("login")).click();
		
		}
	
}
