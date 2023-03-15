package parallelHooks;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class MyHooks{
	String browser;
	 Context context;
	
	public MyHooks(Context context)
	{
		this.context=context;
	}

//	@Before(order=1)		//to test order of execution
//	public void beforeScenario(Scenario scenario)
//	{
//		System.out.println("running before setup method");
//	}

	
	@Before
	public void setup() throws IOException {
		
		
		 Logger logger=LogManager.getLogger(this.getClass());
		  context.setLogger(logger);
		  
//		FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
//				 p=new Properties();
//				 p.load(file);
//				 br=p.getProperty("browser");

		ResourceBundle rb=ResourceBundle.getBundle("config");
		//String br=rb.getString("browser");
		
		browser=context.getBr();
		
		  
	if(browser.equalsIgnoreCase("edge"))
	{
		WebDriver driver=new EdgeDriver();
		context.setDriver(driver);
	}
	else if(browser.equalsIgnoreCase("chrome"))
	{
		WebDriver driver=new ChromeDriver();
		context.setDriver(driver);
	}
	else if(browser.equalsIgnoreCase("firefox"))
	{
		WebDriver driver=new FirefoxDriver();
		context.setDriver(driver);
	}
	
	context.initializePages(context.getDriver());	//initializing all the pages
	
	WebDriverWait wait=new WebDriverWait(context.getDriver(), Duration.ofSeconds(10));
	context.setWait(wait);
	
	System.out.println("browser is: " + context.getBr());
	
	context.getLogger().info("opening browser");
	String appurl=rb.getString("appURL");
	context.getDriver().get(appurl);
	
	context.getLogger().info("maximizing window");
	context.getDriver().manage().window().maximize();

	}

	@After
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status===>" + scenario.getStatus()); // to get the status of the scenario-pass or
																			// fail

		if (scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) context.getDriver();
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}

		context.getDriver().quit();
	}

	// optional part. takes screenshot before and after each step
//	@BeforeStep
//	public void beforeStep(Scenario scenario)
//	{
//		TakesScreenshot ts=(TakesScreenshot)driver;
//		byte[]screenshot=ts.getScreenshotAs(OutputType.BYTES);
//		scenario.attach(screenshot, "image/png",scenario.getName());
//	}
//	
//	@AfterStep
//	public void afterStep(Scenario scenario)
//	{
//		TakesScreenshot ts=(TakesScreenshot)driver;
//		byte[]screenshot=ts.getScreenshotAs(OutputType.BYTES);
//		scenario.attach(screenshot, "image/png",scenario.getName());
//	}

}
