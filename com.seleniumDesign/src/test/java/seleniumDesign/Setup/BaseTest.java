package seleniumDesign.Setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumDesign.pageObject.LandingPage;

public class BaseTest{
	
	
	public WebDriver driver;
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
	
	
public List<HashMap<String , String>> convertJsonToMap() throws IOException {
		
		String file = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//seleniumDesign//DataProviderPackage//datafile.json"),StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String , String>> data = mapper.readValue(file, new TypeReference<List<HashMap<String , String>>>(){});
		
		return data;
	}

@DataProvider
public Object[][] dp() throws IOException {
	
	List<HashMap<String, String>> input = convertJsonToMap();
	Object[][] data = new Object[input.size()][1];
	for(int i =0 ; i<input.size();i++) {
		data[i][0] = input.get(i);
	}
	return data;
}


public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir")+"//reports//" + testCaseName+".png";
	System.out.println(System.getProperty("user.dir")+"//reports//" + testCaseName+".png");
	File file = new File(path);
	FileUtils.copyFile(source, file);
	return path;
};
	
}
