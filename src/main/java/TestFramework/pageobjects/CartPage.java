package TestFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFramework.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);//this initelement method of pagefactory class will initialize all the elements below with which we pass driver argument needed for construction of elements.
	}

	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement clkcnty;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> addToCartlist;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutbtn;
	
	
	
	
	
	By productBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> getProductListInCart() {
		return addToCartlist;
}
		
public boolean verifyProductInCartByName(String productName) {
		
		boolean match = getProductListInCart().stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		return match;
	}


}
