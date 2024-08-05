package SeleniumAutomation.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import SeleniumAutomation.TestComponents.BaseTests;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumAutomation.PageObjects.CartPage;
import seleniumAutomation.PageObjects.CheckOutPage;
import seleniumAutomation.PageObjects.ConfirmationPage;
import seleniumAutomation.PageObjects.LandingPage;
import seleniumAutomation.PageObjects.ProductCatalogue;

public class StepImplementation extends BaseTests
{
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;
	public CheckOutPage checkoutpage;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and passowrd (.+)$")
	public void Logged_in_with_username_and_passowrd(String username, String password)
	{
		productcatalogue = landingpage.login(username, password);
	}
	
	@When("^Add product (.+) to cart$")
	public void Add_product_to_cart(String productName) throws InterruptedException
	{
		productcatalogue.addProductToCart(productName);		
		cartpage = productcatalogue.goToCartPage();
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutpage = cartpage.goToCheckOutPage();
		
		checkoutpage.selectCountry("India");
		confirmationpage = checkoutpage.clickOnSubmit();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String output = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(output.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
	}
}