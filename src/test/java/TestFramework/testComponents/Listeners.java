package TestFramework.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TestFramework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extents =ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//This will make test obj thread safe means every obj will have its own thread of execution and they will not overlap on parallel execution.
	@Override
	public void onTestStart(ITestResult result) {
		
		test=extents.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id(ErrorValidation)->test obj->stored it as a map.
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test is passed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		//Screenshot code on test failed
		//response if api is failed
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	@Override
	public void onStart(ITestContext context) {
		
	}
	@Override
	public void onFinish(ITestContext context) {
		extents.flush();
	}
}
