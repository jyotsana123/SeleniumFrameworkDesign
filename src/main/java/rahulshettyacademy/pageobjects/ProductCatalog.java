package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponent.AbstractComponent;


public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		//driver Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List <WebElement> products;
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	//By loader = By.cssSelector(".ng-animating");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	//Action method - to get the product list
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	//Quest: can you apply page factory within webelement.findelement
	//Ans: No, you can not, it's not possible
	
	
	
}
