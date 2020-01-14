import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SchoolMate {

	WebDriver driver;
	Random random = new Random();
	int rand_number = random.nextInt(1520000);

	@BeforeSuite
	public void before() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Quest Lab\\eclipse-workspace\\AddressBook\\driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("http://192.168.1.68/schoolmate/");
	}

	@Test(priority = 0)
	public void login() {
		driver.findElement(By.xpath("//INPUT[@type='text']")).sendKeys("test");
		driver.findElement(By.xpath("//INPUT[@type='password']")).sendKeys("test");
		driver.findElement(By.xpath("//INPUT[@type='submit']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("(//H1[text()='Manage Classes'])[1]")).getText().toString(),
				"Manage Classes");

	}

	@Test(priority = 1)
	public void openSchool() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='School']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage School Information']/self::H1")).getText().toString(),
				"Manage School Information");

	}

	@Test(priority = 2)
	public void EditSchool() {
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Quest School");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("G10/4 lamabad");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("+923142123423");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("1");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[5]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[5]")).sendKeys("2");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[6]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[6]")).sendKeys("0.1");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[7]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[7]")).sendKeys("0.2");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[8]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[8]")).sendKeys("0.3");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[9]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[9]")).sendKeys("0.4");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[10]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[10]")).sendKeys("0.5");
		driver.findElement(By.xpath("//TEXTAREA[@name='sitetext']")).clear();
		driver.findElement(By.xpath("//TEXTAREA[@name='sitetext']")).sendKeys("Login Page Text Demo");
		driver.findElement(By.xpath("//INPUT[@type='button']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage School Information']/self::H1")).getText().toString(),
				"Manage School Information");

	}

	@Test(priority = 3)
	public void openUsersForTeachers() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Users']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Users']/self::H1")).getText().toString(),
				"Manage Users");

	}

	@Test(priority = 4)
	public void AddUserForTeacher() {

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("//INPUT[@type='text']")).sendKeys("Salman Sharin" + rand_number);
		driver.findElement(By.xpath("(//INPUT[@type='password'])[1]")).sendKeys("123456");
		driver.findElement(By.xpath("(//INPUT[@type='password'])[2]")).sendKeys("123456");
		new Select(driver.findElement(By.xpath("//SELECT[@name='type']"))).selectByVibleText("Teacher");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Users']/self::H1")).getText().toString(),
				"Manage Users");

	}

	@Test(priority = 5)
	public void EditUserForTeacher() {

		driver.findElement(By.xpath("//A[@class='menu'][text()='Users']/self::A")).click();
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[3]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
		driver.findElement(By.xpath("//INPUT[@type='text']")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='password'])[1]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='password'])[2]")).clear();

		driver.findElement(By.xpath("//INPUT[@type='text']")).sendKeys("Salman Sharin" + rand_number);
		driver.findElement(By.xpath("(//INPUT[@type='password'])[1]")).sendKeys("123456");
		driver.findElement(By.xpath("(//INPUT[@type='password'])[2]")).sendKeys("123456");
		new Select(driver.findElement(By.xpath("//SELECT[@name='type']"))).selectByVibleText("Teacher");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 6, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Users']")).getText().toString(),
				"Manage Users");

	}

	@Test(priority = 6)
	public void DeleteUserForTeacher() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Users']/self::A")).click();
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[3]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 6, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Users']/self::H1")).getText().toString(),
				"Manage Users");

	}

	@Test(priority = 7)
	public void openTeachers() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Teachers']/self::A")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Teachers']/self::H1")).getText().toString(),
				"Manage Teachers");

	}

	@Test(priority = 8)
	public void AddTeacher() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Teachers']/self::A")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Salman");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("Sharin");

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Teachers']/self::H1")).getText().toString(),
				"Manage Teachers");

	}

	@Test(priority = 9)
	public void EditTeacher() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Teachers']/self::A")).click();
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("");

		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Salman");

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Teachers']/self::H1")).getText().toString(),
				"Manage Teachers");

	}

	@Test(priority = 10)
	public void DeleteTeacher() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Teachers']/self::A")).click();
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Teachers']/self::H1")).getText().toString(),
				"Manage Teachers");

	}

	@Test(priority = 11)
	public void openClasses() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Classes']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Classes']/self::H1")).getText().toString(),
				"Manage Classes");

	}

	@Test(priority = 12)
	public void AddClasses() {

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Class4");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("1");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("1");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("1");
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[3]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Classes']/self::H1")).getText().toString(),
				"Manage Classes");

	}

	@Test(priority = 13)
	public void openClasses1() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Classes']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Classes']/self::H1")).getText().toString(),
				"Manage Classes");

	}

	@Test(priority = 14)
	public void AddClasses1() {

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Class4");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("1");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("1");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("1");
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[3]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Classes']/self::H1")).getText().toString(),
				"Manage Classes");

	}

	@Test(priority = 15)
	public void DeleteClasses() {

		try {
			driver.findElement(By.xpath("//A[@class='menu'][text()='Classes']/self::A")).click();

			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();

			driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
			try {

				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (Exception e) {

			}

		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Classes']/self::H1")).getText().toString(),
				"Manage Classes");

	}

	@Test(priority = 16)
	public void openTerm() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Terms']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Terms']/self::H1")).getText().toString(),
				"Manage Terms");

	}

	@Test(priority = 17)
	public void AddTerm() {

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Term " + rand_number);
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("12/08/2019");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("12/09/2020");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Terms']/self::H1")).getText().toString(),
				"Manage Terms");

	}

	@Test(priority = 18)
	public void EditTerm() {

		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Term Edit ");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("1/08/2018");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("1/09/2019");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Terms']/self::H1")).getText().toString(),
				"Manage Terms");

	}

	@Test(priority = 19)
	public void DeleteTerm() {
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Terms']/self::H1")).getText().toString(),
				"Manage Terms");

	}

	@Test(priority = 20)
	public void openUsersForStudent() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Users']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Users']/self::H1")).getText().toString(),
				"Manage Users");

	}

	@Test(priority = 21)
	public void AddUserForStudent() {

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("//INPUT[@type='text']")).sendKeys("Muhammad Suleman");
		driver.findElement(By.xpath("(//INPUT[@type='password'])[1]")).sendKeys("123456");
		driver.findElement(By.xpath("(//INPUT[@type='password'])[2]")).sendKeys("123456");
		new Select(driver.findElement(By.xpath("//SELECT[@name='type']"))).selectByVibleText("Student");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Users']")).getText().toString(),
				"Manage Users");

	}

	@Test(priority = 22)
	public void EditUserForStudent() {

		driver.findElement(By.xpath("//A[@class='menu'][text()='Users']/self::A")).click();
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[3]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
		driver.findElement(By.xpath("//INPUT[@type='text']")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='password'])[1]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='password'])[2]")).clear();

		driver.findElement(By.xpath("//INPUT[@type='text']")).sendKeys("Muhammad Suleman");
		driver.findElement(By.xpath("(//INPUT[@type='password'])[1]")).sendKeys("123456");
		driver.findElement(By.xpath("(//INPUT[@type='password'])[2]")).sendKeys("123456");
		new Select(driver.findElement(By.xpath("//SELECT[@name='type']"))).selectByVibleText("Student");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Users']")).getText().toString(),
				"Manage Users");

	}

	@Test(priority = 23)
	public void DeleteUserForStudent() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Users']/self::A")).click();
		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[3]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Users']")).getText().toString(),
				"Manage Users");

	}

	@Test(priority = 24)
	public void openStudents() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Students']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Students']")).getText().toString(),
				"Manage Students");

	}

	@Test(priority = 25)
	public void AddStudents() {

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Ikram");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("-");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("Khan");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Students']")).getText().toString(),
				"Manage Students");

	}

	@Test(priority = 26)
	public void EditStudents() {
		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
			driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
			driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Ikram");
			driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("-");
			driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("Khan");
			driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Students']")).getText().toString(),
				"Manage Students");

	}

	@Test(priority = 27)
	public void DeleteStudents() {
		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
			driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (Exception e) {

			}

		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Students']")).getText().toString(),
				"Manage Students");

	}

	@Test(priority = 28)
	public void openResgtration() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Regtration']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Regtration']/self::H1")).getText().toString(),
				"Regtration");

	}

	@Test(priority = 29)
	public void RegtrationStudents() {
		try {
			driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
			new Select(driver.findElement(By.xpath("//SELECT[@name='class'][text()='	']")))
					.selectByVibleText("Class 2");
			driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Regtration']/self::H1")).getText().toString(),
				"Regtration");

	}

	@Test(priority = 30)
	public void deleteRegtration() {
		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
			driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (Exception e) {

			}

		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Regtration']/self::H1")).getText().toString(),
				"Regtration");

	}

	@Test(priority = 31)
	public void openSemester() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Semesters']/self::A")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Semesters']/self::H1")).getText().toString(),
				"Manage Semesters");

	}

	@Test(priority = 32)
	public void AddSemester() {

		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Fall 2019 1");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("12/09/2019");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("26/12/2019");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("01/03/2020");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Semesters']/self::H1")).getText().toString(),
				"Manage Semesters");

	}

	@Test(priority = 33)
	public void EditSemester() {

		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Fall 2019 1");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("12/09/2019");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[3]")).sendKeys("08/12/2020");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[4]")).sendKeys("01/03/2020");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Semesters']/self::H1")).getText().toString(),
				"Manage Semesters");

	}

	@Test(priority = 34)
	public void DeleteSemester() {
		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[2]")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		}

		driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Semesters']/self::H1")).getText().toString(),
				"Manage Semesters");

	}

	@Test(priority = 35)
	public void openAttendance() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Attendance']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Attendance']/self::H1")).getText().toString(),
				"Attendance");

	}

	@Test(priority = 36)
	public void openParents() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Parents']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Parents']/self::H1")).getText().toString(),
				"Manage Parents");

	}

	@Test(priority = 37)
	public void addParents() {
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Parent");
		driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("Name1");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Parents']/self::H1")).getText().toString(),
				"Manage Parents");

	}

	@Test(priority = 38)
	public void editParent() {

		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
			driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
			driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).clear();
			driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).clear();
			driver.findElement(By.xpath("(//INPUT[@type='text'])[1]")).sendKeys("Parent");
			driver.findElement(By.xpath("(//INPUT[@type='text'])[2]")).sendKeys("Name1");
			driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Parents']/self::H1")).getText().toString(),
				"Manage Parents");

	}

	@Test(priority = 39)
	public void deleteParent() {
		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[2]")).click();
			driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (Exception e) {

			}

		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//H1[text()='Manage Parents']/self::H1")).getText().toString(),
				"Manage Parents");

	}

	@Test(priority = 40)
	public void openAnnouncment() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Announcements']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Announcements']/self::H1")).getText().toString(),
				"Manage Announcements");

	}

	@Test(priority = 41)
	public void addAnnouncment() {
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])")).sendKeys("Announcment Title");
		driver.findElement(By.xpath("//TEXTAREA[@name='message']")).sendKeys("Announcment Message");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Announcements']/self::H1")).getText().toString(),
				"Manage Announcements");

	}

	@Test(priority = 42)
	public void editAnnouncment() {

		driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='button'])[2]")).click();
		driver.findElement(By.xpath("(//INPUT[@type='text'])")).clear();
		driver.findElement(By.xpath("//TEXTAREA[@name='message']")).clear();
		driver.findElement(By.xpath("(//INPUT[@type='text'])")).sendKeys("Announcment Title");
		driver.findElement(By.xpath("//TEXTAREA[@name='message']")).sendKeys("Announcment Message");
		driver.findElement(By.xpath("(//INPUT[@type='button'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Announcements']/self::H1")).getText().toString(),
				"Manage Announcements");

	}

	@Test(priority = 43)
	public void deleteAnnouncment() {
		try {
			driver.findElement(By.xpath("(//INPUT[@type='checkbox'])[1]")).click();
			driver.findElement(By.xpath("(//INPUT[@type='button'])[3]")).click();
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
			} catch (Exception e) {

			}

		} catch (Exception e) {

		}
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//H1[text()='Manage Announcements']/self::H1")).getText().toString(),
				"Manage Announcements");

	}

	@Test(priority = 44)
	public void logout() {
		driver.findElement(By.xpath("//A[@class='menu'][text()='Log Out']/self::A")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3, 10000);
		Assert.assertEquals(driver.findElement(By.xpath("//DIV[@class='messagebox']/self::DIV")).getText().toString(),
				"Login Page Text Demo");

	}

	@AfterSuite
	public void last() {
		driver.close();
	}

}
