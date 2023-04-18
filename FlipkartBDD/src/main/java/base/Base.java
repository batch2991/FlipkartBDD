package base;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{

	public static WebDriver driver;	
	
	public void setUp(String br)
	{
		System.out.println("setup is called");
		if(br.matches("firefox"))
		{
			driver=new FirefoxDriver();			
		}
		if(br.matches("chrome"))
		{
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");        
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(option);			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void openUrl(String url)
	{
		try 
		{
			int respCode=200;		
			HttpURLConnection huc = (HttpURLConnection)(new URL(url).openConnection());            //try to connect to the URL
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();//get the response code if response code is <400 it is valid link else broken link/dead lin
			System.out.println(respCode);
			if(respCode >= 400)
			{
				System.exit(0);
			} 
		}
		catch(Exception e) 
		{
			System.out.println("Url is wrong");
			System.exit(0);
		}
		driver.get(url);
		Assert.assertEquals(driver.getTitle(), "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}	
	public void tearDown()
	{
		driver.quit();
	}	
	public void takeScreenshot(String path)
	{
		try {
		File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File d=new File("./Reports/images/"+path);
		FileUtils.copyFile(f,d);
		}catch(Exception e) {}
	}
}
