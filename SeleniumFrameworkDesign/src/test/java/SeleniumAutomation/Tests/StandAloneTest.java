package SeleniumAutomation.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import seleniumAutomation.PageObjects.LandingPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest 
{
	public static void main(String[] args) 
	{
		String Product_Name = "ADIDAS ORIGINAL";
		//Changes for CICD
		//WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("pegasus@wolverine.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pegasus007@");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		
		WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(Product_Name))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cart = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = cart.stream().anyMatch(s->s.getText().equalsIgnoreCase(Product_Name));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"Ind").build().perform();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item"));
		countries.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		
		a.scrollToElement(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String output = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(output.equalsIgnoreCase("Thankyou for the order."));
	}
}