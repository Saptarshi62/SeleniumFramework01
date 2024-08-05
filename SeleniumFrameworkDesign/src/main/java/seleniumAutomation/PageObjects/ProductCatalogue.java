package seleniumAutomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumAutomation.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents
{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory Declaration of Elements
	@FindBy(xpath="//div[contains(@class,'mb-3')]")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container");
	//By spinner = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductName(String ProductName)
	{
		WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(ProductName))
		.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String ProductName) throws InterruptedException
	{
		WebElement prod = getProductName(ProductName);
		prod.findElement(addToCart).click();
		waitForElementToDisappear(spinner);
		waitForElementToAppear(toast);
	}
}
