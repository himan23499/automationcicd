package TestFramework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFramework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);//this initelement method of pagefactory class will initialize all the elements below with which we pass driver argument needed for construction of elements.
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userpass;
	
	@FindBy(id="login")
	WebElement lgn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errMsg;
	
	public ProductCatalogue loginApplication(String email,String password) {
		userEmail.sendKeys(email);
		userpass.sendKeys(password);
		lgn.click();
		ProductCatalogue p=new ProductCatalogue(driver);
		return p;
	}
	public String getErrMsg() {
		waitForWebElementToAppear(errMsg);
		return errMsg.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

