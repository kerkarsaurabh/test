package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObject {
	public WebDriver driver;

	public PageObject(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}
	
	private By LetBegin =By.xpath("//button[@data-testid='lets_begin']");
	private By email =By.xpath("//*[@data-testid='email']");
	private By password =By.xpath("//*[@data-testid='password1']");
	private By profilDropDownClick = By.xpath("//div[@class='Dropdown-control postedby_selector']");
	private By profiledropdown=By.xpath("//*[@class='Dropdown-option']");
	private By sexMale = By.xpath("//*[@id='gender_male']");
	private By sexFemale = By.xpath("//*[@id='gender_female']");
	private By submit = By.xpath("//*[@data-testid='next_button']");
	private By motherTongue =By.xpath("//div[@class='Dropdown-control mother_tongue_selector Dropdown-disabled']");
	
	
	public WebElement getLetBegin() {
		return driver.findElement(LetBegin);	
	}
	
	public WebElement getemail() {
		return driver.findElement(email);	
	}
	
	public WebElement getpassword() {
		return driver.findElement(password);
	}
	
	public WebElement getProfilDropDownClick() {
		return driver.findElement(profilDropDownClick);
	}
	
	public List<WebElement> getprofiledropdown() {
		return driver.findElements(profiledropdown);	
	}

	public WebElement getSexMale() {
		return driver.findElement(sexMale);
	}
	
	public WebElement getSexFemale() {
		return driver.findElement(sexFemale);
	}
	
	public WebElement getSubmit() {
		return driver.findElement(submit);
	}
	public WebElement getMotherTongue() {
		return driver.findElement(motherTongue);
	}
	
}
