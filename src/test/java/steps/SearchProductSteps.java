package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import parallelHooks.Context;

public class SearchProductSteps{
	
	
	//WebDriver driver=MyHooks.driver;
	
	
	Context context;
	 public SearchProductSteps(Context context)
	 {
		 this.context=context;
	 }
	
	@When("user search a {string}")
	public void user_search_a(String pname) throws InterruptedException {

		//homepage = new HomePage(driver);

		Thread.sleep(1000);
		
		context.getLogger().info("searching a product");
		context.getHomepage().SearchProduct(pname);
		
		context.getLogger().info("clicking on search button");
		context.getHomepage().clickSearchButton();

	}

	@When("clicks on the {string}")
	public void clicks_on_the(String pname) throws InterruptedException {

		//sp = new SearchPage(driver);

		context.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[3]//img")));

		context.getSp().searchAProduct(pname);

		Thread.sleep(2000);
		context.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[3]//img")));
		context.getLogger().info("clicking on a searched  product");
		context.getSp().clickOnSearchedProduct(pname);
	}

	@When("adds it to the cart")
	public void adds_it_to_the_cart() {

		//sp = new SearchPage(driver);

		context.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='button-cart']")));
		
		context.getLogger().info("clicking on add to cart button");
		context.getSp().addToCart();
	}

	@Then("add to cart is successful")
	public void add_to_cart_is_successful() {

		//sp = new SearchPage(driver);
//		WebElement actual_msg=driver.findElement(By.xpath("//div[text()='Success: You have added ']"));
//		
//		String act_msg=actual_msg.getText();
//		System.out.println("actual message: "+act_msg);
		// sp.verifySuccessMsg();

		context.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Success: You have added ']")));
		if (context.getSp().verifySuccessMsg()) {
			System.out.println("success message:" + context.getSp().verifySuccessMsg());
			
			context.getLogger().info("add to cart successful");
			Assert.assertTrue(true);
		} else {
			System.out.println("success message:" + context.getSp().verifySuccessMsg());
			
			context.getLogger().error("add to cart unsuccessful");
			Assert.assertTrue(false);
		}
		// driver.quit();
	}
}
