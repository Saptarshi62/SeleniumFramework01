package SeleniumAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumAutomation.PageObjects.LandingPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests 
{
	//WebDriverManager.chromedriver().setup();
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		//user.dir makes the file path dynamic and this program can work on any system
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\seleniumAutomation\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		//If input is given in maven cmd prmpt then take that otherwise use properties file default
		String browserName = System.getProperty("browser")!=null ? 
				System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("Headless"))
			{
				options.addArguments("headless");
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900));	//fullscreen
			}
			else
			{
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDatatoMap(String filePath) throws IOException
	{
		//read json file to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//convert string to hashmap using maven dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{	
		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void TearDown()
	{
		driver.close();
	}
}