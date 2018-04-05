package ResultsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ProductDetailsPage.ProductDetailsPage;

public class SearchResultsPage {
	WebDriver driver;
	String searchedTerm;
	WebElement searchResultsText;
	String fullText;
	StringBuffer sbSearchText= new StringBuffer();
	WebElement customerRating;
	
	//Constructor for Search Results page
	public SearchResultsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	//Class to get the searched text in search results page
	public String validateSearchResults() throws InterruptedException
	{
		Thread.sleep(5000);
		fullText=driver.findElement(By.xpath("//div[@class='search_result_HeaderMsgs']//descendant::p")).getText();
		int lengthOfFullText = fullText.length();
		for(int i=20;i<lengthOfFullText-1;i++)
		{
			sbSearchText = sbSearchText.append(fullText.charAt(i));
		}
		
		return sbSearchText.toString();
	}
	
	//Selecting a filter criteria
	public void setFilterCriteria(int checkboxValueFromTop, String filterOption) throws InterruptedException
	{
		Thread.sleep(5000);
		customerRating = driver.findElement(By.xpath("//span[contains(text(),'"+filterOption+"')]/ancestor::div[2]"));
		
		//#content > div.pmpSearch_middle_container.clearfix > div.pmpSearch_leftPanel > div.leftPanel_content > div > div:nth-child(9)
		customerRating.click();
		WebElement checkboxForSelection = driver.findElement(By.xpath("//ul[@data-dim='"+filterOption+"']/descendant::li["+checkboxValueFromTop+"]/descendant::span[1]"));
		//
		checkboxForSelection.click();	
	}
	
	//Selecting product from search results page
	public ProductDetailsPage selectProduct()
	{
		WebElement product = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]/div[1]/div[3]/ul/li[1]/div[1]"));
		product.click();
		return new ProductDetailsPage(driver);
	}

}
