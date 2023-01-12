package rahulshettyacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponent.BaseTest;

//Section 17: Framework Part 1 - Create Maven Project and Prepare Functional End to end Test

public class ErrorValidation extends BaseTest {

@Test
public void SubmitOrder() throws InterruptedException, IOException
{
		String productName = "ZARA COAT 3";
		lp.loginApplication("nishu@yopmail.com", "Test@23");
				//div[aria-label='Incorrect email or password.']
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
						
		
		
	}

}
