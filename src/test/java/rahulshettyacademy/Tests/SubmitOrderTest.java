package rahulshettyacademy.Tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.AbstractComponent.AbstractComponent;
import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.Dropdown;
import rahulshettyacademy.pageobjects.Landingpage;
import rahulshettyacademy.pageobjects.ProductCatalog;

//Section 17: Framework Part 1 - Create Maven Project and Prepare Functional End to end Test

public class SubmitOrderTest extends BaseTest {

@Test
public void SubmitOrder() throws InterruptedException, IOException
{
		String productName = "ZARA COAT 3";
		ProductCatalog pc =lp.loginApplication("nishu@yopmail.com", "Test@123");
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp = pc.clickOnCartButton();
		boolean match = cp.verifyProductDisplay(productName);
		Assert.assertTrue(match);  //validation of test pass or fail is present inside the test file only on in the page object file.
		Dropdown dp = cp.checoutClick();
		dp.sendValuesInDropdown();
		ConfirmationPage cnfrmPage = dp.clickOnPlaceOrder();
		String confirmMessage = cnfrmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
