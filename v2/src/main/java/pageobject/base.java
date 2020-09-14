package pageobject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class base   {
	 public WebDriver driver;
	 public Properties prop ;
	//initializeNrowser method is used to identify which browser to use as mentioned in property file
	public WebDriver initializeNrowser() throws IOException {
		
		
		// Importing Properties class to read prop file data
		 prop = new Properties();
		 String currentDirectory = System.getProperty("user.dir");
		    
		//FileInputStream class is used to initialize property file
		FileInputStream fis = new FileInputStream(currentDirectory+"\\src\\main\\java\\resources\\data.properties");
		//loading file in properties object
		prop.load(fis);
		//Storing property value to string
		String browserName = prop.getProperty("browser");
		//using if loop to itrate as per browser mentioned 
		System.out.println(browserName);
		String chromedriver = currentDirectory+"\\src\\chromedriver.exe";
		if (browserName.equalsIgnoreCase("chrome")) //block of chrome
		{
			System.setProperty("webdriver.chrome.driver", chromedriver);
			 driver = new ChromeDriver();
			
		}
		else if (browserName== "firefox"){
			//block of firefox
			
		}
		else if (browserName == "IE") {
			
			//block of IE
		}
		return driver;
	}

}
