package TestFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestFramework.pageobjects.CartPage;
import TestFramework.pageobjects.CheckOutPage;
import TestFramework.pageobjects.OrderPage;

public class AbstractComponents {

	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	WebDriver driver;
	@FindBy(xpath="//*[contains(@class,'cart')]")
	WebElement CartBtn;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutbtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public CartPage goToCartPage() {
		CartBtn.click();
		CartPage ct = new CartPage(driver);
		return ct;
	}
	
	public CheckOutPage goToCheckOut() {
		
		checkoutbtn.click();
		return new CheckOutPage(driver);
	}
	public void waitForElementToAppear(By element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public OrderPage gotoOrderPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}

}
