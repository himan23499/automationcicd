package TestFramework.tests;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import TestFramework.pageobjects.CartPage;
import TestFramework.pageobjects.CheckOutPage;
import TestFramework.pageobjects.ConfirmationPage;
import TestFramework.pageobjects.LandingPage;
import TestFramework.pageobjects.ProductCatalogue;
import TestFramework.testComponents.BaseTest;
public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = TestFramework.testComponents.Retry.class)
	public void logInErrValidation() throws IOException {
		
		
		
		
		ld.loginApplication("himanshupatil5173@gmail.com","HId2ipa499");
		
		Assert.assertEquals("Incorrect email or password.", ld.getErrMsg());
	
		
		
		
	}
	
	@Test
	public void productErrValidations() throws IOException, InterruptedException {
		String productName="ZARA COAT 3";
		
		
		
		ProductCatalogue p=ld.loginApplication("himanshupatil517@gmail.com","HIdipa499");
		
		
		p.addProductsToCart(productName);
		CartPage ct=p.goToCartPage();
		Thread.sleep(2000);
		Assert.assertFalse(ct.verifyProductInCartByName("ZARA COAT 33"));
		
		
		
		
	}
}
