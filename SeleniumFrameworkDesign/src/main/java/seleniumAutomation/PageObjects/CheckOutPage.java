package seleniumAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumAutomation.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents
{
	WebDriver driver;
	Actions a;
	
	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.a = new Actions(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountryDropdown;
	
	@FindBy(css=".ta-item")
	List<WebElement> countries;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		a.sendKeys(selectCountryDropdown,countryName).build().perform();
		waitForElementToAppear(results);
		countries.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null).click();
	}
	
	public ConfirmationPage clickOnSubmit()
	{
		a.scrollToElement(submitButton).build().perform();
		submitButton.click();
		return new ConfirmationPage(driver);
	}
}
