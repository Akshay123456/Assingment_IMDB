package test.IMDB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IMDB {
	private WebDriver driver;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		
		if(browser.equalsIgnoreCase("firefox"))
		{

		System.setProperty("webdriver.gecko.driver","/Learning Selenium2/IMDB/Drivers/geckodriver-v0.11.1-win64/geckodriver.exe");
		driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","/Learning Selenium2/IMDB/Drivers/chromedriver.exe");
			driver = new FirefoxDriver();
		}
	}

	@Test
	public void IMDB_DATA() throws InterruptedException {
		driver.get("http://www.imdb.com/search/title?groups=top_250&sort=user_rating");
		Thread.sleep(20);
		boolean flag=true;
		for (int i=1;i<=50;i++){
			String Moviename= driver.findElement(By.xpath(".//*[@id='main']/div/div/div[3]/div["+i+"]/div[3]/h3/a")).getText();
			String Year = driver.findElement(By.className("lister-item-year")).getText();
			String rating_star = driver.findElement(By.xpath(".//*[@id='main']/div/div/div[3]/div["+i+"]/div[3]/div/div[1]/strong")).getText();
			System.out.println(Moviename+Year+"\t"+rating_star);
			if(i==50)
			{
				if(driver.findElement(By.className("next-page")).isDisplayed())
				{
					driver.findElement(By.className("next-page")).click();
					i=1;
				}

				else
					System.out.println("Done");

			} 
		
		}
	}
	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
