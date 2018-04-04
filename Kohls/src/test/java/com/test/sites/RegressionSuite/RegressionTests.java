package com.test.sites.RegressionSuite;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.sites.KohlsFramework.FrameworkClass;
import com.test.sites.KohlsFramework.UtilityClass;

import KohlsHomepage.LandingPage;
import ProductDetailsPage.ProductDetailsPage;
import ResultsPage.SearchResultsPage;



public class RegressionTests extends FrameworkClass{
	String url = "https://www.kohls.com/";
	String expectedPageTitle = "Kohl's | Shop Clothing, Shoes, Home, Kitchen, Bedding, Toys & More";
	//static WebDriver driver=null;
	
	@Test(priority=1)
	public void verifyPageTitle()
	{
		LandingPage landingpage = new LandingPage(driver);
		String actualPageTitle = landingpage.openHomepage(url);
		driver.manage().window().maximize();
		Assert.assertEquals(actualPageTitle, expectedPageTitle);		
	}
	
	@Test(enabled=false)
	@Parameters("searchTerm")
	public void validateSearch(String searchTerm) throws InterruptedException
	{
		LandingPage landingpage = new LandingPage(driver);
		landingpage.submitSearch(searchTerm);
		SearchResultsPage searchresultspage = new SearchResultsPage(driver);
		String actualResult = searchresultspage.validateSearchResults();
		Assert.assertEquals(actualResult, searchTerm);
	}
	
	@Test(enabled=false)
	@Parameters("searchTerm")
	public void getNumberOfSearchResults(String searchTerm)
	{
		LandingPage landingpage = new LandingPage(driver);
		int numOfSuggestions =landingpage.numberOfSuggestions(searchTerm);
		System.out.println("The number of search suggestions for " + searchTerm + " are - " + numOfSuggestions);
	}
	
	@Test(enabled=false)
	@Parameters("searchTerm")
	public void listOfSearchSuggestions(String searchTerm) throws InterruptedException
	{
		LandingPage landingpage = new LandingPage(driver);
		List<String> suggestionsList = landingpage.suggestionsList(searchTerm);
		Iterator<String> suggestionsListIterator = suggestionsList.iterator();
		while(suggestionsListIterator.hasNext())
		{
			System.out.println("The following are the auto-complete suggestions for the" + searchTerm +"- /n" + suggestionsListIterator.next());
		}		
		
	}
	
	@Test(dependsOnMethods="verifyPageTitle")
	@Parameters("searchTerm")
	public void selectingProduct(String searchTerm) throws InterruptedException
	{	
		LandingPage landingpage = new LandingPage(driver);
		landingpage.submitSearch(searchTerm);
		SearchResultsPage searchresultspage = new SearchResultsPage(driver);
		searchresultspage.setFilterCriteria(1, "Customer Rating");
		searchresultspage.selectProduct();
		ProductDetailsPage productdetailspage = new ProductDetailsPage(driver);
		productdetailspage.addToBag();				
	}
	
	@Test(dependsOnMethods = "verifyPageTitle")
	public void selectFeedback() throws Exception 
	{
		UtilityClass utilityclass = new UtilityClass(driver);
		utilityclass.clickfeedbackModule("Store Feedback");
	}
	

}
