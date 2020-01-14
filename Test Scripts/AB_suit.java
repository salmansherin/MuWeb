import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AB_suit {

	WebDriver driver;
	Random random = new Random();
	int rand_number = random.nextInt(1520000);

	@BeforeSuite
	public void before() {
		System.setProperty("webdriver.chrome.driver","seleDrivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("http://localhost/addressbook/");
	}

	@Test(priority = 0)
	public void groups() {
		driver.findElement(By.xpath("//A[@href='group.php'][text()='groups']/self::A")).click();
	}

	@Test(priority = 1)
	public void addNewGroup() {

		driver.findElement(By.xpath("//*[@id=\"content\"]/form[1]/input")).click();

		driver.findElement(By.xpath("//INPUT[@type='text']/self::INPUT")).sendKeys("Suleman Group Demo " + rand_number);

		driver.findElement(By.xpath("//TEXTAREA[@name='group_header']/self::TEXTAREA"))
				.sendKeys("Suleman Group Header ");

		driver.findElement(By.xpath("//TEXTAREA[@name='group_footer']/self::TEXTAREA"))
				.sendKeys("Suleman Group Footer");

		driver.findElement(By.xpath("//INPUT[@type='submit']/self::INPUT")).click();
	}

	@Test(priority = 3)
	public void deleteGroup() {

		driver.findElement(By.xpath("//A[@href='group.php'][text()='groups']/..")).click();

		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[2]")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		}

		driver.findElement(By.xpath("(//INPUT[@type='submit'])[2]")).click();

	}

	@Test(priority = 2)
	public void editGroup() {

		driver.findElement(By.xpath("//A[@href='group.php'][text()='groups']/..")).click();
		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[2]")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		}

		driver.findElement(By.xpath("(//INPUT[@type='submit'])[3]")).click();

		// Edit Group
		driver.findElement(By.xpath("//INPUT[@type='text']/self::INPUT"))
				.sendKeys("Suleman Group Demo Edit " + rand_number);

		driver.findElement(By.xpath("//TEXTAREA[@name='group_header']/self::TEXTAREA"))
				.sendKeys("Suleman Group Header Edit");

		driver.findElement(By.xpath("//TEXTAREA[@name='group_footer']/self::TEXTAREA"))
				.sendKeys("Suleman Group Footer Edit");

		driver.findElement(By.xpath("//INPUT[@type='submit']/self::INPUT")).click();

	}

	@Test(priority = 4)
	public void addNewPage() {
		driver.findElement(By.xpath("//A[@href='edit.php'][text()='add new']/self::A")).click();

	}

	@Test(priority = 5)
	public void addNew() {
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Muhammad");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("Suleman");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("Quest Lab");

		driver.findElement(By.xpath("//TEXTAREA[@name='address']")).sendKeys("Rothas Road G9/4 lamabad");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("03165282707");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[5]")).sendKeys("03341006096");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[6]")).sendKeys("-----");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[7]")).sendKeys("----");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[8]")).sendKeys("msuleman526@gmail.com");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[9]")).sendKeys("msuleman526@gmail.com");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[10]")).sendKeys("-------");

		new Select(driver.findElement(By.xpath("//SELECT[@name='bday']"))).selectByVibleText("13");

		new Select(driver.findElement(By.xpath("//SELECT[@name='bmonth']"))).selectByVibleText("November");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[11]")).sendKeys("1995");

		driver.findElement(By.xpath("//TEXTAREA[@name='address2']")).sendKeys("Rothas Road G9/4 lamabad");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[12]")).sendKeys("");

		driver.findElement(By.xpath("//TEXTAREA[@name='notes']")).sendKeys("");

		driver.findElement(By.xpath("//INPUT[@type='submit']")).click();
	}

	public void deleteItem() {

		driver.findElement(By.xpath("//A[@href='./'][text()='home']/self::A")).click();

	}

	@Test(priority = 6)
	public void printAll() {
		driver.findElement(By.xpath("//A[@href='view.php?all&print'][text()='print all']/self::A")).click();
		driver.get("http://localhost/addressbook/");
	}

	@Test(priority = 7)
	public void printPhones() {
		driver.findElement(By.xpath("//A[@href='view.php?all&print&phones'][text()='print phones']/self::A")).click();
		driver.get("http://localhost/addressbook/");
	}

	@Test(priority = 8)
	public void csv() {
		driver.findElement(By.xpath("//A[@href='csv.php'][text()='export csv']/self::A")).click();
		driver.get("http://localhost/addressbook/");
	}

	@Test(priority = 9)
	public void nextBirthdays() {

		driver.findElement(By.xpath("//A[@href='birthdays.php'][text()='next birthdays']/self::A")).click();

	}

	@Test(priority = 10)
	public void nextBirthdayProfile() {

		driver.findElement(By.xpath("(//IMG[@src='icons/status_online.png'])[1]")).click();
	}

	@Test(priority = 12)
	public void nextBirthdayModify() {

		driver.findElement(By.xpath("(//INPUT[@type='submit'])[1]")).click();

		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Muhammad Modify");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("Suleman");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("Quest Lab");

		driver.findElement(By.xpath("//TEXTAREA[@name='address']")).sendKeys("Rothas Road G9/4 lamabad");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("03165282707");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[5]")).sendKeys("03341006096");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[6]")).sendKeys("-----");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[7]")).sendKeys("----");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[8]")).sendKeys("");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[9]")).sendKeys("");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[10]")).sendKeys("-------");

		new Select(driver.findElement(By.xpath("//SELECT[@name='bday']"))).selectByVibleText("13");

		new Select(driver.findElement(By.xpath("//SELECT[@name='bmonth']"))).selectByVibleText("December");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[11]")).sendKeys("1996");

		driver.findElement(By.xpath("//TEXTAREA[@name='address2']")).sendKeys("Rothas Road G9/4 lamabad");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[12]")).sendKeys("");

		driver.findElement(By.xpath("//TEXTAREA[@name='notes']")).sendKeys("");

		driver.findElement(By.xpath("(//INPUT[@type='submit'])[1]")).click();

		driver.get("http://localhost/addressbook/birthdays.php");

		driver.findElement(By.xpath("(//IMG[@src='icons/status_online.png'])[1]")).click();
	}

	@Test(priority = 11)
	public void nextBirthdayPrint() {
		driver.findElement(By.xpath("(//INPUT[@type='submit'])[2]")).click();
		driver.get("http://localhost/addressbook/birthdays.php");
		driver.findElement(By.xpath("(//IMG[@src='icons/status_online.png'])[1]")).click();

	}

	@Test(priority = 13)
	public void nextBirthdayModifyDelete() {

		driver.findElement(By.xpath("(//INPUT[@type='submit'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='submit'])[2]")).click();
		driver.get("http://localhost/addressbook/birthdays.php");

	}

	@Test(priority = 14)
	public void nextBirthdayEdit() {

		driver.findElement(By.xpath("(//IMG[@src='icons/pencil.png'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Muhammad Edit");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("Suleman");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("Quest Lab");

		driver.findElement(By.xpath("//TEXTAREA[@name='address']")).sendKeys("Rothas Road G9/4 lamabad");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("03165282707");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[5]")).sendKeys("03341006096");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[6]")).sendKeys("-----");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[7]")).sendKeys("----");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[8]")).sendKeys("");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[9]")).sendKeys("");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[10]")).sendKeys("-------");

		new Select(driver.findElement(By.xpath("//SELECT[@name='bday']"))).selectByVibleText("13");

		new Select(driver.findElement(By.xpath("//SELECT[@name='bmonth']"))).selectByVibleText("December");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[11]")).sendKeys("1996");

		driver.findElement(By.xpath("//TEXTAREA[@name='address2']")).sendKeys("Rothas Road G9/4 lamabad");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[12]")).sendKeys("");

		driver.findElement(By.xpath("//TEXTAREA[@name='notes']")).sendKeys("");

		driver.findElement(By.xpath("(//INPUT[@type='submit'])[1]")).click();

		driver.get("http://localhost/addressbook/birthdays.php");

		driver.findElement(By.xpath("(//IMG[@src='icons/pencil.png'])[1]")).click();

	}

	@Test(priority = 15)
	public void nextBirthdayDelete() {

		driver.findElement(By.xpath("(//INPUT[@type='submit'])[2]")).click();
		driver.get("http://localhost/addressbook/birthdays.php");
	}

	@Test(priority = 16)
	public void nextBirthdayGoogleMap() {

		driver.findElement(By.xpath("(//IMG[@src='icons/car.png'])[1]")).click();
		driver.get("http://localhost/addressbook/");
	}

	@Test(priority = 17)
	public void homeSearch() {

		driver.findElement(By.xpath("//INPUT[@type='text']")).sendKeys("Zeeshan");

	}

	@Test(priority = 18)
	public void homeAddToSpecific() {

		driver.findElement(By.xpath("//INPUT[@id='id1']")).click();
		driver.findElement(By.xpath("//INPUT[@type='submit']")).click();
		driver.get("http://localhost/addressbook/");
	}

	@Test(priority = 19)
	public void homesendMailToSpecific() {

		driver.findElement(By.xpath("//INPUT[@type='text']")).sendKeys("Zeeshan");
		driver.findElement(By.xpath("//INPUT[@id='id1']")).click();
		driver.findElement(By.xpath("//INPUT[@type='button']")).click();
		driver.get("http://localhost/addressbook/");
	}

	@Test(priority = 20)
	public void homeAddToAll() {
		driver.findElement(By.xpath("//INPUT[@id='MassCB']")).click();
		driver.findElement(By.xpath("//INPUT[@type='submit']")).click();
		driver.get("http://localhost/addressbook/");
	}

	@Test(priority = 21)
	public void homesendMailToAll() {

	}

}
