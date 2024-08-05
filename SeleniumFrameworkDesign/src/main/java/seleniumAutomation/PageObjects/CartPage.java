package seleniumAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumAutomation.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents
{
	WebDriver driver;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cart;
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutButton;

	public Boolean verifyProductDisplay(String ProductName)
	{
		Boolean match = cart.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	public CheckOutPage goToCheckOutPage()
	{
		checkOutButton.click();
		return new CheckOutPage(driver);
	}
}
