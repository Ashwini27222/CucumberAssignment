package com.visionit.automation.stepdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

import com.visionit.automation.core.WebDriverFactory;
import com.visionit.automation.pageobjects.CmnPageObjects;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs
{
	private static final Logger logger =  LogManager.getLogger(StepDefs.class);
	
	WebDriver driver;
	String base_url = "https://automationpractice.com";
	int implicit_wait_timeout_in_sec = 20;
	Scenario scn; 
	
	CmnPageObjects cmnPageObjects;
	
	@Before
	public void setUp(Scenario scn) throws Exception{

		this.scn = scn; //Assign this to class variable, so that it can be used in all the step def methods

		//Get the browser name by default it is chrome
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser invoked.");

		//Init Page Object Model Objects
		cmnPageObjects = new CmnPageObjects(driver);

	}
	
	@After(order=1)
	public void cleanUp(){
		WebDriverFactory.quitDriver();
		scn.log("Browser Closed");
	}

	@After(order=2) // this will execute first, higher the number, sooner it executes
	public void takeScreenShot(Scenario s) {
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png","Failed Step Name: " + s.getName());
		}else{
			scn.log("Test case is passed, no screen shot captured");
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		WebDriverFactory.navigateToTheUrl(base_url);
		scn.log("Browser navigated to URL: " + base_url);

		String expected = "My Store";
		cmnPageObjects.validatePageTitleMatch(expected);
	}


	
	
	@Then("page should contain logo with desired width as {int} and height as {int}")
	public void page_should_contain_logo_with_desired_width_as_and_height_as(int width, int height) {
		cmnPageObjects.validateLogo(width, height);
		scn.log("height : "+height+"  and width : "+width);
	}
	
	@When("User Search for product {string}")
	public void user_search_for_product(String productName) {
		cmnPageObjects.SetSearchTextBox(productName);

		scn.log("Product Searched: " + productName);
	}
	
	@Then("Search Result page is displayed with text {string}")
	public void search_result_page_is_displayed_with_text(String expected)
	{
		cmnPageObjects.validateSearch(expected);
		scn.log("validated Search : " + expected);
	}
	
	@When("display the text of three categories")
	public void display_the_text_of_three_categories() {
		cmnPageObjects.countTheProductLink();
		scn.log("product category displayed");
	}
	
	@When("user search for twitter link from footer section of the landing page")
	public void user_search_for_twitter_link_from_footer_section_of_the_landing_page() {
		cmnPageObjects.validate_footer();
		scn.log("footer validated");
	}

	@Then("Product Description is displayed in new tab with title {string}")
	public void product_description_is_displayed_in_new_tab_with_title(String expectedTitle) {
		cmnPageObjects.validate_footer_url(expectedTitle);
		scn.log("footer url validated : "+expectedTitle);
	}
	
	@Then("the twitter account name should be {string}")
	public void the_twitter_account_name_should_be(String str) {
		cmnPageObjects.validate_footer_name(str);
		scn.log("footer name validated : "+str);
	}

	@Then("Page Url should be {string}")
	public void page_url_should_be(String url)
	{
		cmnPageObjects.validateUrl(url);
	}	
	
	@When("User Search for product categories {string}")
	public void user_search_for_product_categories(String productCategory)
	{
		cmnPageObjects.SearchCategory(productCategory);
		scn.log("searched for product category : "+productCategory);
	}
	
	@Then("main product categories count should be {int}")
	public void main_product_categories_count_should_be(Integer count) 
	{
		cmnPageObjects.CountTheProductLink(count);
		//scn.log("count of main product categories : "+count);
	}
	
}
