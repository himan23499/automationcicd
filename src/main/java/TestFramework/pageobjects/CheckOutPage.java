package TestFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFramework.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement clkcnty;

	@FindBy(css = ".action__submit")
	WebElement acSub;

	By selCnty = By.cssSelector("[placeholder='Select Country']");
	By cntylist = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(selCnty), countryName).build().perform();
		waitForElementToAppear(cntylist);
		clkcnty.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		acSub.click();
		return new ConfirmationPage(driver);
		
	}
}
