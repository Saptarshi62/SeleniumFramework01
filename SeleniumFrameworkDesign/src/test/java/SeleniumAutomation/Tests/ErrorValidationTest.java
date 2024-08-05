package SeleniumAutomation.Tests;

import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
import org.testng.Assert;
import SeleniumAutomation.TestComponents.BaseTests;
import seleniumAutomation.PageObjects.CartPage;
import seleniumAutomation.PageObjects.CheckOutPage;
import seleniumAutomation.PageObjects.ConfirmationPage;
import seleniumAutomation.PageObjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTests
{
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = SeleniumAutomation.TestComponents.Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException 
	{
		lp.login("pegasus@wolverine.com", "Pjhgjhfs0nb7@");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException 
	{
		String Product_Name = "ADIDAS ORIGINAL";
		
		ProductCatalogue pc = lp.login("abc@cdf.com", "Abc@cdf123");
		
		pc.addProductToCart(Product_Name);		
		CartPage cp = pc.goToCartPage();
		
		Boolean match = cp.verifyProductDisplay("ADIDAS hgdhyd");
		Assert.assertFalse(match);
	}
}