package runner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import parallelHooks.Context;

//@RunWith(Cucumber.class)
@CucumberOptions(glue={"steps","parallelHooks"},
					
				
				monochrome=true,
				//features= {"src/test/resources/Features/Login.feature"},
				features= {"src/test/resources/Features"},// "src/test/resources/Features/Login.feature"},
				
				
				dryRun=!true,	//when dryrun=true shows only implemented steps. no print statements
				//snippets = SnippetType.CAMELCASE,	//shows snippets without underscore
				
				tags="@one or @search",		//do not use curly braces. shows type mismatch error.
				plugin= {"pretty","html:CucumberReports.html",	//without pretty only print statements are seen in console
						
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						
						"json:Reports/jsonReports.json",
						"junit:Reports/JunitReports.xml"
						}
				
					)
//public class TestRunner {
	
public class ParallelRunner extends AbstractTestNGCucumberTests{
	
	 @Override								//these 4 stmts are for parallel execution using testNG
	    @DataProvider(parallel = true)			//make parallel= true when want to run parallely
	    public Object[][] scenarios() {
	        return super.scenarios();
}
	
	
	 @BeforeTest
	 @Parameters({"browser"})
	 public void defineBrowser(String browser) {
		 Context.setBr(browser);
	 }
}
   