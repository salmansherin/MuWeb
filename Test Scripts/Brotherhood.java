package com;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import okio.Timeout;

public class NewTest {
	WebDriver driver;
	Random rand = new Random(); 
	  
    // Generate random integers in range 0 to 999 
    int rand_int1 = rand.nextInt(99999999); 
	
	@BeforeClass
	public void before()
	{
		System.setProperty("webdriver.chrome.driver","seleDrivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");	  
		driver = new ChromeDriver(options);
		driver.get("http://localhost/sn/index.php");
	}
	
	@Test (priority=0)
	public void regter()
	{
		driver.get("http://localhost/sn/index.php");
		
		WebElement firstName = driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[1]/td[2]/input"));
		firstName.sendKeys("Muhammad");
		
		WebElement lastName = driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[2]/td[2]/input"));
		lastName.sendKeys("Suleman");
		
		WebElement email = driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[3]/td[2]/input"));
		email.sendKeys("msuleman"+rand_int1+"@gmail.com");
		
		WebElement password = driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[4]/td[2]/input"));
		password.sendKeys("Suleman009");
		
		WebElement password1= driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[5]/td[2]/input"));
		password1.sendKeys("Suleman009");
		
		Select gender = new Select(driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[6]/td[2]/select")));
		gender.selectByVibleText("Male");
		
		Select day = new Select(driver.findElement(By.xpath("//*[@id=\"dayselect\"]")));
		day.selectByVibleText("13");
		
		Select month = new Select(driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[7]/td[2]/select[1]")));
		month.selectByVibleText("November");
		
		Select year = new Select(driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[7]/td[2]/select[3]")));
		year.selectByVibleText("1995");
		
		driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/form/table/tbody/tr[8]/td[2]/input")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"userInfoTable\"]/tbody/tr[1]/td[2]")).getText(),"msuleman"+rand_int1+"@gmail.com" );
		
	}
	@Test (priority=1)
	public void logout2()
	{
		driver.findElement(By.xpath("//*[@id=\"uniLogoutButtonDiv\"]/button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"homepageRegForm\"]/p/font")).getText(), "Join the Brotherhood!");
	}
	
	@Test (priority=2)
	public  void loginUser()
	{
		WebElement email = driver.findElement(By.xpath("//FORM[@id='loginbox']//INPUT[@type='text']"));
		email.sendKeys("msuleman"+rand_int1+"@gmail.com");
		WebElement password = driver.findElement(By.xpath("//FORM[@id='loginbox']//INPUT[@type='password']"));
		password.sendKeys("Suleman009");
		driver.findElement(By.xpath("//*[@id=\"loginbox\"]/input[3]")).click();
        
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"contentTable\"]/tbody/tr/td/h1[1]")).getText(),"Post a Dpatch" );
	}

	@Test (priority=3)
	public  void updateProfileImage()
	{
		WebElement image = driver.findElement(By.cssSelector("#file"));
		image.sendKeys("C:/Users/pc/eclipse-workspace/BH_test/pics/suleman.jpg");
		driver.findElement(By.cssSelector("#\\\\alertarea\\\" > tbody > tr:nth-child(1) > td > form > input[type=submit]:nth-child(4)")).click();
		WebDriverWait wait = new WebDriverWait(driver,4,10000); 
	}
	
	@Test (priority=4)
	public void searchUser()
	{
		driver.findElement(By.xpath("//INPUT[@id='query']/self::INPUT")).sendKeys("Muhammad Suleman");
		driver.findElement(By.xpath("//INPUT[@id='query']/following-sibling::INPUT")).click();
	}
	
	
	@Test (priority=5)
	public void sendFriendRequest()
	{
		
		driver.findElement(By.xpath("//BUTTON[@type='button'][text()='+1 Friend'][text()='+1 Friend']/self::BUTTON")).click();
	}
	
	
	/*@Test
	public void completeProfileMiddleName()
	{
		driver.findElement(By.xpath("//INPUT[@type='text']/self::INPUT")).sendKeys("Middle Name");
		driver.findElement(By.xpath("//INPUT[@type='text'][2]/following-sibling::INPUT")).click();
	}
	*/
	
	@Test (priority=6)
	public void myProfile()
	{
		
		driver.findElement(By.xpath("//BUTTON[@type='button'][text()='My Profile']/self::BUTTON")).click();
		
		//Click on Message Div Link
		driver.findElement(By.xpath("//A[@href='#'][text()='Click Here To Show Msg Div']/self::A")).click();
				
		//Click on My Friends
		driver.findElement(By.xpath("//TD[@id='riendstabButton\"']/self::TD")).click();
				
		//Click on User Info
		driver.findElement(By.xpath("//TD[@id='\\userInfoTabButton\"']/self::TD")).click();
						
		//Click on Dpatch
		driver.findElement(By.xpath("//TD[@id='\\wallTabButton\"']/self::TD")).click();
	}
	
	
	@Test (priority=7)
	public void postFromMyProfile()
	{
		WebElement postArea = driver.findElement(By.xpath("//TEXTAREA[@name='dpatch'][text()='				']/self::TEXTAREA"));
		postArea.sendKeys("Testing Post");
		driver.findElement(By.xpath("//TEXTAREA[@name='dpatch'][text()='				']/..//INPUT[@type='submit']")).click();
	}
	
	/*@Test  (priority=8)
	public void completeProfileMiddleName()
	{
		driver.findElement(By.xpath("(//INPUT[@type='submit'])[2]/preceding-sibling::INPUT")).sendKeys("Middle Name");
		driver.findElement(By.xpath("//INPUT[@type='text'][2]/following-sibling::INPUT")).click();
	}*/
	
	
	@Test (priority=8)
	public void approvedRequest()
	{
		try
		{
			
		driver.findElement(By.xpath("//BUTTON[@type='button'][text()='Approve'][text()='Approve']/self::BUTTON")).click();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	
	@Test (priority=9)
	public  void about()
	{
		//open Profile
		driver.findElement(By.xpath("//*[@id=\"footer\"]/a[2]")).click();
		
		
	}
	
	@Test (priority=10)
	public  void termAndConditions()
	{

		driver.findElement(By.xpath("//*[@id=\"footer\"]/a[3]")).click();
		driver.get("http://localhost/sn/index.php");
	}
	
	@AfterSuite
	public void logoutt()
	{
		driver.findElement(By.xpath("//*[@id=\"uniLogoutButtonDiv\"]/button")).click();
		driver.close();
	}
}
