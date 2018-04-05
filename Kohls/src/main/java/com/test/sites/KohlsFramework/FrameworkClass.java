package com.test.sites.KohlsFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class FrameworkClass {
	
	protected WebDriver driver;
	private String chromedriverPath = "C:\\Users\\syd aravy\\Downloads\\chromedriver_win32/chromedriver.exe";
	private String firefoxdriverPath = "";
	private String IEdriverPath = "";
	
	//Selecting the driver object for a particular browser
	@Parameters("browser")
	@BeforeClass	
	public void setupDriver(String browser) throws Exception
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", chromedriverPath);
			setDriver(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.firefox.driver", firefoxdriverPath);
			setDriver(new FirefoxDriver());
		}
		else if (browser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.firefox.driver", IEdriverPath);
			setDriver(new InternetExplorerDriver());	
		}
		else 
			throw new Exception ("Incorrect browser entered");
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
