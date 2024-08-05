package seleniumAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumAutomation.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents
{
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> productNames;
	
	public Boolean verifyOrderDisplay(String ProductName)
	{
		Boolean match = productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));
		return match;
	}
}