package seleniumDesign.Setup;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportSetup {

	
	public static ExtentReports initlizeExtentReport() {
		File file = new File(System.getProperty("user.dir")+"//reports//report.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		spark.config().setReportName("First Report");
		ExtentReports extent;
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Sidharth");
		return extent;
	}
}
