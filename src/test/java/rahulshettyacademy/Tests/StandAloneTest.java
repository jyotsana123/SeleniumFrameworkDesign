package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.Landingpage;

//Section 17: Framework Part 1 - Create Maven Project and Prepare Functional End to end Test

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		//Create object of Landingpage here to call driver in landing page class, And send argument 'driver'
		Landingpage lp = new Landingpage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("nishu@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		String item = "ZARA COAT 3";
		List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst().orElse(null);
		driver.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
		
		
		//Need to wait until toast message "product added to cart" showing on screen, use explicit wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		
		//Need to wait until loader is get disappear, Use which classname is used for this you can ask with your developer if not able to found
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Click on cart icon
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Verify product added in the cart is actually appearing in the cart or not
		List <WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(item));
		Assert.assertTrue(match);
		
		//Proceed with checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Click on select country, Autosuggestive dropdown
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		//Scroll page to get "Place order button" in view, So that we can click on that
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//Check thank you message printed or not
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
		
		
//		List<WebElement> options = driver.findElements(By.cssSelector(".ta-results"));
//		for(int i=0;i<options.size();i++)
//		{
//			String A = options.get(i).getText();
//			System.out.println(A);
//		}
//		for(WebElement option :options)
//		{
//			if(option.getText().equalsIgnoreCase("india"))
//			{
//				option.click();
//				break;
//			}
//		}
	
		
//		for(int i=0;i<products.size();i++)
//		{
//			String items = products.get(i).getText();
//			System.out.println(items);
//			if(items.contains(product))
//			{
//
//
//				driver.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
//				System.out.println(product);
//			}
//		}
		
	}

}
