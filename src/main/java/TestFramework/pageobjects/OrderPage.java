package TestFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFramework.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);//this initelement method of pagefactory class will initialize all the elements below with which we pass driver argument needed for construction of elements.
	}

	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	
	
	
	
	
		
public boolean verifyOrderDisplay(String productName) {
		
		boolean match = productNames.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		return match;
	}


}
