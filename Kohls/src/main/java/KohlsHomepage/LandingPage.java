package KohlsHomepage;


import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ResultsPage.SearchResultsPage;

public class LandingPage {
	WebDriver driver;
	WebElement searchTextbox;
	List<WebElement> suggestions;
	String pageTitle = "Kohl's | Shop Clothing, Shoes, Home, Kitchen, Bedding, Toys & More";
	List <String> suggestionsList;
	int numberOfSuggestions;
	
	//Constructor of landing page
	public LandingPage(WebDriver driver) {
		this.driver=driver;		
	}
	
	//Launching home page
	public String openHomepage(String pageUrl)
	{
		driver.get(pageUrl);
		if(driver.getTitle().equals(pageTitle))
		{
			return driver.getTitle();
		}
		else
		{
			System.out.println("Page did not load");
			return "Page did not load";
		}
	}

	
	//To get the list of auto-complete options
	public List<String> suggestionsList(String searchTerm) throws InterruptedException
	{
		searchTextbox = driver.findElement(By.id("search"));
		/*WebDriverWait waitUntilPageLoad = new WebDriverWait(driver, 10);
		waitUntilPageLoad.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#kohls-ad-wallpaper > img.kohls-ad-wallpaper__image"))));*/
		Thread.sleep(6000);
		for(int i=0; i<searchTerm.toCharArray().length;i++)
		{
			char charsInSearchTerm = searchTerm.charAt(i);
			String enteringSearchTerm = new StringBuilder().append(charsInSearchTerm).toString();
			searchTextbox.sendKeys(enteringSearchTerm);
			Thread.sleep(300);
		}
		WebElement ulId=driver.findElement(By.id("K1000"));
		List<WebElement> suggestions = ulId.findElements(By.tagName("li"));
		for (WebElement suggestion : suggestions)
		{
			suggestionsList.add(suggestion.getText());
		}
		return suggestionsList;
}
	//To get the number of auto-complete options
	public int numberOfSuggestions(String searchTerm) {
		suggestions = driver.findElements(By.xpath("//span[class='ta-suggestion-text']"));
		numberOfSuggestions = suggestions.size();
		return numberOfSuggestions;
	}

	//Submit search action
	public SearchResultsPage submitSearch(String searchTerm) throws InterruptedException {
		Thread.sleep(4000);
			driver.findElement(By.name("search")).sendKeys(searchTerm);
			driver.findElement(By.name("submit-search")).click();
			return new SearchResultsPage(driver);		
	}

}
