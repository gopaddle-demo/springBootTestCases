package springBootTestCases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



public class petClinicTest {
	
	public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filename)));
		return new JSONObject(content);
	}
	
	static Properties p;
	WebDriver driver;
	
	@BeforeTest
	public void init() {
		try {
			FileReader reader = new FileReader("config.properties");
			p = new Properties();
			p.load(reader);
			
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setHeadless(true);
			chromeOptions.addArguments("--remote-debugging-port=9222");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			
			File filePath = new File("src/test/resources/chromedriver_linux64/chromedriver"); //ChromeDriver version ---> ChromeDriver 85.0.4183.83 //chrome version ---> Google Chrome 85.0.4183.102 
			String filename = filePath.getAbsolutePath();
			System.setProperty("webdriver.chrome.driver", filename);  
			driver = new ChromeDriver(chromeOptions);  
			//driver = new ChromeDriver();  
			driver.manage().window().maximize();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1)
	public void ADD_NEW_OWNER() {
		System.out.println("Test Case Initiated ADD_NEW_OWNER !!! ");
		
		driver.get(p.getProperty("home").toString());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec
		
		driver.get(p.getProperty("owners").toString());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec
		
		driver.findElement(By.linkText("Add Owner")).click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec
		
		WebElement ownerFirstName = driver.findElement(By.id("firstName"));
		ownerFirstName.clear();
		ownerFirstName.sendKeys("Gayathri");
		
		WebElement ownerLastName = driver.findElement(By.id("lastName"));
		ownerLastName.clear();
		ownerLastName.sendKeys("Gayu");
		
		WebElement ownerAddress = driver.findElement(By.id("address"));
		ownerAddress.clear();
		ownerAddress.sendKeys("CBE");
		
		WebElement ownerCity = driver.findElement(By.id("city"));
		ownerCity.clear();
		ownerCity.sendKeys("Pollachi");
		
		WebElement ownerMobile = driver.findElement(By.id("telephone"));
		ownerMobile.clear();
		ownerMobile.sendKeys("8075169665");
		
		WebElement btn_addOwner = driver.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button"));
		btn_addOwner.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec

	}
	
	@Test(priority = 2)
	public void EDIT_OWNER() {
		System.out.println("Test Case Initiated for EDIT_OWNER !!! ");
		
		driver.findElement(By.linkText("Edit Owner")).click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec
		
		WebElement ownerLastName = driver.findElement(By.id("lastName"));
		ownerLastName.clear();
		ownerLastName.sendKeys("R");
		
		WebElement ownerAddress = driver.findElement(By.id("address"));
		ownerAddress.clear();
		ownerAddress.sendKeys("Pollachi");
		
		WebElement ownerMobile = driver.findElement(By.id("telephone"));
		ownerMobile.clear();
		ownerMobile.sendKeys("7085139665");
		
		WebElement btn_editOwner = driver.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button"));
		btn_editOwner.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec

	}
	
	@Test(priority = 3)
	public void ADD_NEW_PET() {
		System.out.println("Test Case Initiated for ADD_NEW_PET !!! ");
		
		driver.findElement(By.linkText("Add New Pet")).click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec
		
		WebElement petName = driver.findElement(By.id("name"));
		petName.clear();
		petName.sendKeys("Tommy");
		
		WebElement petBirthDate = driver.findElement(By.id("birthDate"));
		petBirthDate.clear();
		petBirthDate.sendKeys("2019-10-10");
		
		Select dropDown_petType = new Select(driver.findElement(By.xpath("//*[@id=\"type\"]")));
		dropDown_petType.selectByVisibleText("dog");
		
		
		WebElement btn_addPet = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button"));
		btn_addPet.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec

	}

	@Test(priority = 4)
	public void EDIT_PET() {
		System.out.println("Test Case Initiated for EDIT_PET !!! ");
		
		driver.findElement(By.linkText("Edit Pet")).click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 6 sec
		
		WebElement petBirthDate = driver.findElement(By.xpath("//*[@id=\"birthDate\"]"));
		petBirthDate.clear();
		petBirthDate.sendKeys("2019-07-08");
		
		WebElement btn_editPet = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button"));
		btn_editPet.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec

	}
	
	//@Test(priority = 5)
	public void ADD_VETERINARIAN_VISIT() {
		System.out.println("Test Case Initiated for ADD_VETERINARIAN_VISIT !!! ");
		
		driver.findElement(By.linkText("Add Visit")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // implicitly Wait 5 sec
		
		WebElement petVisitDescription = driver.findElement(By.xpath("//*[@id=\"description\"]"));
		petVisitDescription.clear();
		petVisitDescription.sendKeys("cold");
		
		WebElement btn_addVisit = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button"));
		btn_addVisit.click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec

	}
	
	@Test(priority = 6)
	public void LIST_OWNER() {
		System.out.println("Test Case Initiated for LIST_OWNER !!! ");
		
		driver.get(p.getProperty("owners").toString());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec
		
		driver.findElement(By.xpath("//*[@id=\"search-owner-form\"]/div[2]/div/button")).click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec
	}
	
	@Test(priority = 7)
	public void LIST_VETERINARIANS() {
		System.out.println("Test Case Initiated for LIST_VETERINARIANS !!! ");
		
		driver.findElement(By.xpath("//*[@id=\"main-navbar\"]/ul/li[3]/a")).click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); // implicitly Wait 5 sec
	}
	
	@AfterTest
	public void CLOSE_CHROME_WINDOW() throws InterruptedException {
	
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Thread.sleep(30000);

		driver.close();
		driver.quit();
	}

}
