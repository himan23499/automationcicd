package TestFramework.pageobjects;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestFramework.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);//this initelement method of pagefactory class will initialize all the elements below with which we pass driver argument needed for construction of elements.
	}
//	List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	@FindBy(css=".ngx-spinner-overlay")
	WebElement spinner;
	
//	@FindBy(xpath="//*[contains(@class,'cart')]")
//	WebElement CartBtn;
	
	
	By selCnty=By.cssSelector("[placeholder='Select Country']");
	By productBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg= By.cssSelector("#toast-container");
	By cntylist = By.cssSelector(".ta-results");
	public List<WebElement> getProductList(){
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement items = getProductList().stream().filter(item->item.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);	
		return items;
		
	}
	
	
	
	
	public void addProductsToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		Thread.sleep(2000);
//		waitForElementToDisappear(spinner);
		waitForElementToAppear(toastMsg); 
		
//		CartBtn.click();
	}
	
	
	
	}

