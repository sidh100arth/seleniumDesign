package seleniumDesign.Listners;

import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumDesign.Setup.BaseTest;
import seleniumDesign.Setup.ExtentReportSetup;

public class Listners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportSetup.initlizeExtentReport();
	ExtentTest test ;
	ThreadLocal<ExtentTest> localTest = new ThreadLocal<ExtentTest>();
	
	WebDriver driver;
	
	public void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }
	
	public void onStart(ITestContext context) {
	    // not implemented
	  }
	
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
	public void onTestStart(ITestResult result) {
	    // not implemented
		test = extent.createTest(result.getMethod().getMethodName());
		localTest.set(test);
		
	  }
	public void onTestSuccess(ITestResult result) {
		String filePath = null;
	    // not implemented
		localTest.get().log(Status.PASS, "Test is successfull" + result.getMethod().getMethodName());
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filePath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	public void onTestFailure(ITestResult result) {
	    // not implemented
		
		String filePath = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filePath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		localTest.get().fail("Test failed  " + result.getThrowable());
	  }
	
}
