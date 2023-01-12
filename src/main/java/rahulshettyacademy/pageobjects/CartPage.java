package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartItems;
	

	public boolean verifyProductDisplay(String productName)
	{
		boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public Dropdown checoutClick()
	{
		checkoutButton.click();
		Dropdown dp = new Dropdown(driver);
		return dp;
	}

	

}
