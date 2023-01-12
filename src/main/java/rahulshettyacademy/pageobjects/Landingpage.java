package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;


public class Landingpage extends AbstractComponent {
	
	WebDriver driver;
	//Constructor is the first method to execute when you touch your class
	//In this driver has no life, driver life is in "StandAloneTest" class
	public Landingpage(WebDriver driver)
	{
		//driver Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//PageFactory - By this you can reducing the syntax of creating your webElement
	@FindBy(id="userEmail")
	WebElement Email;
	
	//Question: How this "@FindBy" annotation knows about driver
	//Ans: There is one method called "initElements" which you have to write first, which will take care of constructing that using driver argument what you send in the method
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login") //Question: how you know that id is in small or caps , Ans: click on @FindBy annotation and click and then go in findby class - here all string mentioned id, xpath, css you can simply copy that string and paste here.
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//Action Class
		
		public ProductCatalog loginApplication(String email, String password) //Page object not contain any data, it's only take care of web elements and actions
		{
			Email.sendKeys(email);
			Password.sendKeys(password);
			login.click();
			ProductCatalog pc = new ProductCatalog(driver);
			return pc;
		}
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
			
		}
}
