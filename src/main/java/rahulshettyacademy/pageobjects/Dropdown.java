package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class Dropdown extends AbstractComponent {
	
	WebDriver driver;
	public Dropdown(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By autosuggestive = By.cssSelector(".ta-results");
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement dropdown;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement AutoSuggest;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	

	public void sendValuesInDropdown()
	{
		Actions a = new Actions(driver);
		a.sendKeys(dropdown, "india").build().perform();
		waitForElementToAppear(autosuggestive);
		AutoSuggest.click();
	}
	
	public ConfirmationPage clickOnPlaceOrder() throws InterruptedException
	{
	    Thread.sleep(3000);
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    //scroll page from top to bottom
	    js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		placeOrderButton.click();
		ConfirmationPage cnfrmPage = new ConfirmationPage(driver);
		return cnfrmPage;
	}
	
//	Actions a = new Actions(driver);
//	a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	//driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	
	
	

}
