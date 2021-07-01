package com.free_crm_data;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.excel_reader_utility.ExcelUtility;

public class FreeCrmContactForm {

	static WebDriver driver;
	static Properties prop;
	static FileInputStream fis;
	
	public static WebDriverWait wait;
	
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		// Accessing config.properties file
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\prate\\workspace\\DataDrivenUsingTestNG\\config.properties");
		prop.load(fis);
		
		//Invoking browser
		/*System.setProperty("webdriver.chrome.driver", "E:\\STUDY Mate\\SELENIUM\\WebDrivers\\Chrome WebDriver\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();*/
		WebDriverManager.chromedriver().version("90.0.4").setup();
		driver = new ChromeDriver();
		
		
		//Performing actiosn once bowser invoked 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(ExcelUtility.pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ExcelUtility.implicitlyWait, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		/*driver.findElement(By.xpath("//a[@class='btn btn-primary btn-xs-2 btn-shadow btn-rect btn-icon btn-icon-left']")).click();*/
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("(//div[contains(text(),'Login')])[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Contacts')]")).click();
		driver.findElement(By.xpath("(//button[@class='ui linkedin button'])[3]")).click();
	}

	@Test(dataProvider = "ExcelData")
	public void CreatContacts(String FirstName, String LastName, String Company, String Email) throws IOException
	{

//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(FirstName);
//		driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys(LastName);
//		driver.findElement(By.xpath("//div[@name='company']//input[@class='search']")).sendKeys(Company);
//		driver.findElement(By.xpath("//input[@placeholder='Email address']")).sendKeys(Email);
		
		WebElement Fname = driver.findElement(By.xpath("//input[@name='first_name']"));
		WebElement Lname = driver.findElement(By.xpath("//input[@name='last_name']"));
		WebElement Company1 = driver.findElement(By.xpath("//div[@name='company']//input[@class='search']"));
		WebElement Address  = driver.findElement(By.xpath("//input[@placeholder='Email address']"));
		WebElement Save = driver.findElement(By.xpath("//button[@class='ui linkedin button']"));
		
		sendData(driver, Fname, 10, FirstName);
		sendData(driver, Lname, 10, LastName);
		sendData(driver, Company1, 10, Company);
		sendData(driver, Address, 10, Email);
		
		clickOn(driver, Save, 10);
		
//		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Username);
//		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Password);
//		driver.findElement(By.xpath("//button[@id='u_0_h']"));
	}
	
	//Explicit wait implementation 
	public static void sendData(WebDriver driver, WebElement element, int Timeout, String value){
		
		wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);	
	}
	//Explicit wait implementation 
	public static void clickOn(WebDriver driver, WebElement element, int Timeout){
		
		wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	@DataProvider
	public Object[][] ExcelData() throws IOException
	{
	 Object[][] obj = ExcelUtility.ExcelDataReader("FreeCrmData");
	 return obj;
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}	
}
