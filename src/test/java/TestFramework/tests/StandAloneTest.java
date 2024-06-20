package TestFramework.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("himanshupatil517@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("HIdipa499");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".col-lg-4"))));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		
		WebElement items = products.stream().filter(item->item.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);	
			items.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> list = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = list.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//my way for auto suggestive dropdown
		driver.findElement(By.cssSelector(".totalRow button")).click();
//		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group button")));
//		
//		List<WebElement> countrys= driver.findElements(By.cssSelector(".form-group button"));
//		WebElement ans = countrys.stream().filter(item->item.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
//		ans.click();
//		
		
		//way shown in video
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
//		[class*='ta-item']:nth-of-type(2)
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String conMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(conMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
		
		
	}

}
