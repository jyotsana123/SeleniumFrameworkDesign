package rahulshettyacademy.TestComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.Landingpage;

public class BaseTest {
	
	 
	
	public WebDriver driver;  //we have initialized driver globally
	
	public Landingpage lp;

	public WebDriver initializeDriver() throws IOException
	{
		//propertise class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		//FileInputStream fis = new FileInputStream("C:\\Users\\Jyotsana Pandey\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		//C:\\Users\\Jyotsana Pandey\\eclipse-workspace\\SeleniumFrameworkDesign  - from here you can dynamically define your path
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox
			System.setProperty("WebDriver.firefox.driver", "firefox.exe file path");
			driver = new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge
			System.setProperty("WebDriver.edge.driver", "edge.exe file path");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod
	public Landingpage launchApplication() throws IOException
	{
		driver = initializeDriver();
		lp = new Landingpage(driver);
		lp.goTo();
		return lp;
	}
	
	//@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
}
