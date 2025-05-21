package seleniumDesign.Setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import seleniumDesign.pageObject.LandingPage;

public class BaseTest {
	
	
	WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver intilizeDriver() throws IOException {
		Properties properties = new Properties();
		System.out.println(System.getProperty("user.dir"));
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//Global.properties");
		properties.load(fis);
		String browserName = properties.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("Please select a Valid Browser");
		}
		
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod
	public WebDriver launchApplication() throws IOException {
		driver = intilizeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return driver;
	}
	@AfterMethod
	public void closeDriver() {
		driver.close();
	}
}
