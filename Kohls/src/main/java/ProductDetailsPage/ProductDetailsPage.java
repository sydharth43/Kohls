package ProductDetailsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage {
	WebDriver driver;
	String productTitle;
	
	public ProductDetailsPage(WebDriver driver2) {
		// TODO Auto-generated constructor stub
		this.driver=driver2;
	}

	public String openProductpage()
	{
		productTitle=driver.findElement(By.xpath("//h1[class='pdp-product-title']")).getText();
		if(driver.getTitle().equals(productTitle))
		{
			return driver.getTitle();
		}
		else
		{
			System.out.println("Page did not load");
			return "Page did not load";
		}
	}
	
	public void addToBag()
	{
		WebElement addToBag = driver.findElement(By.cssSelector("#addtobagID"));
		addToBag.click();
	}
	
	public void cartDetails()
	{
		String numberofItemsInBag = driver.findElement(By.cssSelector("#tr_phase2_ShoppingBg > span.number-items")).getText();
		System.out.println(numberofItemsInBag);
		String cartTotal = driver.findElement(By.cssSelector("#tr_phase2_ShoppingBg > span.subtotal")).getText();
		System.out.println(cartTotal);		
	}
}
