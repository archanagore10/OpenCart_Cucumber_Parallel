package parallelHooks;

import java.util.ResourceBundle;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;

public class Context {

	private WebDriver driver;
	private HomePage homepage;
	private SearchPage sp;
	
	private MyAccountPage myacc;
	private LoginPage loginpage;
	
	private WebDriverWait wait;
	private Logger logger;
	
	
	private static String br=null;
	
	public String getBr() {
		return br;
	}

	public static void setBr(String br) {
		Context.br = br;
	}


	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage getHomepage() {
		return homepage;
	}

	

	public SearchPage getSp() {
		return sp;
	}

	

	public MyAccountPage getMyAcc() {
		return myacc;
	}

	

	public LoginPage getLp() {
		return loginpage;
	}

	

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	
	public void initializePages(WebDriver driver)
	{
		 homepage=new HomePage(driver);
		  sp=new SearchPage(driver);
		
		  myacc=new MyAccountPage(driver);
		  loginpage=new LoginPage(driver);
	}
}
