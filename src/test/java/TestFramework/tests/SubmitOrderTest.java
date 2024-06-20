package TestFramework.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestFramework.pageobjects.CartPage;
import TestFramework.pageobjects.CheckOutPage;
import TestFramework.pageobjects.ConfirmationPage;
import TestFramework.pageobjects.LandingPage;
import TestFramework.pageobjects.OrderPage;
import TestFramework.pageobjects.ProductCatalogue;
import TestFramework.testComponents.BaseTest;
public class SubmitOrderTest extends BaseTest{
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		
		
		ProductCatalogue p=ld.loginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> products = p.getProductList();
		p.addProductsToCart(input.get("product_name"));
		CartPage ct=p.goToCartPage();
		
		Assert.assertTrue(ct.verifyProductInCartByName(input.get("product_name")));
		CheckOutPage ch = ct.goToCheckOut();
		ch.selectCountry("india");
		ConfirmationPage cp = ch.submitOrder();
		String 	msg = cp.getConfMsg();
		
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
	}
	
	//To Verify if ZARA COAT 3 is getting displayed in Orders page.
	@Test(dependsOnMethods={"submitOrder"})
	public void ordersHistoryTest() {
		
		ProductCatalogue p=ld.loginApplication("himanshupatil517@gmail.com","HIdipa499");
		OrderPage op=p.gotoOrderPage();
		Assert.assertTrue(op.verifyOrderDisplay(productName));
	}
	
//	public String getScreenshot(String testCaseName) throws IOException {
//		TakesScreenshot ts = (TakesScreenshot)driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		File target =new File( System.getProperty("user.dir") + "//reports//" + testCaseName+".png");
//		FileUtils.copyFile(source, target);
//		return System.getProperty("user.dir") + "//reports//" + testCaseName+".png";
//	}
	
	//Extent Reports-:This utility is used to create execellent html reports and attach ss to reports.
	
	@DataProvider
	public Object[][] getData() throws IOException  {
//		HashMap<String,String> mp = new HashMap<String,String>();	
//		mp.put("email", "himanshupatil517@gmail.com");
//		mp.put("password", "HIdipa499");
//		mp.put("product_name", "ZARA COAT 3");
//		HashMap<String,String> mp1 = new HashMap<String,String>();	
//		mp1.put("email", "himanshupatil23499@gmail.com");
//		mp1.put("password", "HIdipa499");
//		mp1.put("product_name", "ADIDAS ORIGINAL");
//		return new Object[][] {{mp},{mp1}};
		
		//reading data through json file
		String filepath = System.getProperty("user.dir")+"//src//test//java//TestFramework//Data//PurchaseOrder.json";
		List<HashMap<String,String>> data = getJsonDataToMap(filepath);
		return new Object[][] {{data.get(0)},{data.get(1)}};
//		return new Object[][] {{mp},{"himanshupatil23499@gmail.com","HIdipa499","ADIDAS ORIGINAL"}};
	}
}
