package seleniumAutomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumAutomation.PageObjects.CartPage;
import seleniumAutomation.PageObjects.OrdersPage;

public class AbstractComponents 
{
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponents(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orders;

	public void waitForElementToAppear(By findBy)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement e)
	{
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException
	{
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage()
	{
		cart.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrdersPage goToOrdersPage()
	{
		orders.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}
}