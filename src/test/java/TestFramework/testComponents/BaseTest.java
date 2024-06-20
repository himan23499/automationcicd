package TestFramework.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import TestFramework.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage ld;
	public WebDriver initializeDriver() throws IOException {
		
		//properties class to extract global parameters from .properties files
		
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//TestFramework//resources//GlobalData.properties");
		prop.load(fs);
		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"): prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			 
			 driver=new ChromeDriver(options);
			 driver.manage().window().setSize(new Dimension(1440,900));//full screen mode.
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			 driver=new EdgeDriver();
			
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	
		//String to HashMap -Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});//this will return {map,map}.
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target =new File( System.getProperty("user.dir") + "//reports//" + testCaseName+".png");
		FileUtils.copyFile(source, target);
		return System.getProperty("user.dir") + "//reports//" + testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		 ld=new LandingPage(driver);
		ld.goTo();
		return ld;
	}
	
	//this will always run even if only specified group is selected to run in testng.xml file.
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
