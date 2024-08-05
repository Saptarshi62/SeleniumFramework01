package SeleniumAutomation.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumAutomation.TestComponents.BaseTests;
import seleniumAutomation.PageObjects.CartPage;
import seleniumAutomation.PageObjects.CheckOutPage;
import seleniumAutomation.PageObjects.ConfirmationPage;
import seleniumAutomation.PageObjects.OrdersPage;
import seleniumAutomation.PageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTests
{
	String Product_Name = "ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException 
	{
		ProductCatalogue pc = lp.login(input.get("email"), input.get("password"));
		
		pc.addProductToCart(input.get("product"));		
		CartPage cp = pc.goToCartPage();
		
		Boolean match = cp.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage cop = cp.goToCheckOutPage();
		
		cop.selectCountry("India");
		ConfirmationPage cnfp = cop.clickOnSubmit();
		
		String output = cnfp.getConfirmationMessage();
		Assert.assertTrue(output.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest() 
	{
		ProductCatalogue pc = lp.login("pegasus@wolverine.com", "Pegasus007@");
		OrdersPage op = pc.goToOrdersPage();
		
		Boolean match = op.verifyOrderDisplay(Product_Name);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDatatoMap(System.getProperty("user.dir") +"\\src\\test\\java\\SeleniumAutomation\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(0)}};
	}
}