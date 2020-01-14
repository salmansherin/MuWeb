import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Wolf_Suit {
	
	
	WebDriver driver;
	
	@BeforeSuite
	public void before() {
		System.setProperty("webdriver.chrome.driver","seleDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("http://localhost/wolf/");
	}

	@Test(priority = 0)
	public void home() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Home Page");
	}
	
	@Test(priority = 1)
	public void about_me_more() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/p[1]/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"About us");
	}
	
	@Test(priority = 2)
	public void favourite_site_wolf() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul[1]/li/a")).click();
		driver.get("http://localhost/wolf/");
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Home Page");
		
	}
	
	@Test(priority = 3)
	public void archives() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/p[2]/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Articles");
		
	}
	
	@Test(priority = 4)
	public void archives_by_month() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul/li/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"August 2019 archive");
		
	}
	
	@Test(priority = 5)
	public void archives_by_month_second_article() {
		driver.findElement(By.xpath("//*[@id=\"col1\"]/div[1]/h3/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"My second article");
		
	}
	
	@Test(priority = 6)
	public void archives_by_month_first_article() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul/li/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col1\"]/div[2]/h3/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"My first article");
		
	}
	
	@Test(priority = 7)
	public void home_first_article() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul[2]/li[2]/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"My first article");
		
	}
	@Test(priority = 8)
	public void home_second_article() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul[2]/li[1]/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"My second article");
		
	}
	
	@Test(priority = 9)
	public void home_first_article1() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col1\"]/div[2]/h3/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"My first article");
		
	}
	@Test(priority = 10)
	public void home_second_article2() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col1\"]/div[1]/h3/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"My second article");
		
	}
	
	@Test(priority = 11)
	public void home_RSS() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col2\"]/p[3]/a")).click();
		driver.get("http://localhost/wolf/");
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Home Page");
		
	}
	
	@Test(priority = 12)
	public void selenium_text_page() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[2]/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Selenium test page");
		
		
	}
	
	@Test(priority = 13)
	public void topBarAboutUs() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[3]/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"About us");
		
		
	}
	
	@Test(priority = 14)
	public void aboutUsRSS() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/p[3]/a")).click();
		driver.get("http://localhost/wolf/");
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Home Page");
		
	}
	
	@Test(priority = 15)
	public void topBarArticles() {
		driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[4]/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Articles");
		
		
	}
	
	@Test(priority = 16)
	public void articlePageByMonth() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul/li/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"August 2019 archive");
		
		
	}
	@Test(priority = 17)
	public void articlePageByMonthFirst() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul/li/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col1\"]/div[2]/h3/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"My first article");
		
		
	}
	@Test(priority = 18)
	public void articlePageByMonthSecond() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul/li/a")).click();
		driver.findElement(By.xpath("//*[@id=\"col1\"]/div[1]/h3/a")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"My second article");
		
		
	}
	
	@Test(priority = 19)
	public void footerYourName() {
		driver.findElement(By.xpath("//*[@id=\"col2\"]/ul/li/a")).click();
		driver.findElement(By.xpath("//*[@id=\"footer\"]/p/a[1]")).click();
		driver.get("http://localhost/wolf/");
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Home Page");
		
		
	}
	
	@Test(priority = 20)
	public void footerCMS() {
		driver.findElement(By.xpath("//*[@id=\"footer\"]/p/a[2]")).click();
		driver.get("http://localhost/wolf/");
		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"col1\"]/h2")).getText().toString(),
				"Home Page");
		
		
	}
	
	@AfterSuite
	public void last() {
		driver.close();
	}
	
	
	

}