package TestFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Results");//this provides meta data to extentreports class about report.
		
		
		ExtentReports extents = new ExtentReports();
		extents.attachReporter(reporter);
		extents.setSystemInfo("Tester","Himan");
		return extents;
	}
	
}
