package Shadi.com.v2;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.base;
import pageobject.PageObject;

public class Excecution extends base {
	@Test(dataProvider = "getdata")
	public void get_panfield(String Testcaseno, String email, String password, String profile, String sex,String motherTongue)
			throws IOException, InterruptedException {
		 WebDriver driver = null;
		driver = initializeNrowser();
		//System.out.println(
		//		"testcasename :" + Testcaseno + "email :" + email + "password:" + password + "Profile" + profile + sex);

		driver.get(prop.getProperty("url"));//going to url whcih is mentioned in property file
		PageObject objectModel = new PageObject(driver); //creating object of Page object mode and using it
		objectModel.getLetBegin().click();
		Thread.sleep(1000);
		objectModel.getemail().sendKeys(email);
		objectModel.getpassword().sendKeys(password);	
		objectModel.getProfilDropDownClick().click();
		//used  to select any profile whcich is mentioned in excel.
		int count = objectModel.getprofiledropdown().size();
		for (int i = 0; i < count; i++) {
			String text = objectModel.getprofiledropdown().get(i).getText();
			if (text.equalsIgnoreCase(profile)) {
				objectModel.getprofiledropdown().get(i).click();
				break;

			}
		}
		//Used for only self category profile to select gender
		if (profile.equalsIgnoreCase("self")) {
			if (!(sex.equalsIgnoreCase(""))) {
				if (sex.equalsIgnoreCase("Male")) {
					objectModel.getSexMale().click();
				} else {
					objectModel.getSexFemale().click();
				}

			}

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		objectModel.getSubmit().click();
		//comparing the final mothertongue.
		String mothertongue = objectModel.getMotherTongue().getText();
		Assert.assertEquals(mothertongue, motherTongue);
		driver.quit();
		
			
		}



	
	
	@DataProvider
	//used for getting data from excel
	public static Object[][] getdata() {
		int totalrow = 0;
		int columnno = 0;
		int testCaseRowNum = 0;
		int column = 0;
		int totalTestDataRows = 0;

		String currentdir = System.getProperty("user.dir");
		String path = currentdir + "\\src\\main\\java\\resources\\Book2.xlsx";
		ExcelReader excel = new ExcelReader(path);
		//to find total rows
		totalrow = excel.gettotalrow("shadi");
		System.out.println("total rows :" + totalrow);
		//to check testcase start from which row
		for (testCaseRowNum = 1; testCaseRowNum <= totalrow; testCaseRowNum++) {
			String testcasename = excel.getdataofrow("shadi", 0, testCaseRowNum);
			if (testcasename.equalsIgnoreCase("Testcaseno")) {
				break;

			} else {
				if (testCaseRowNum == totalrow - 1)
					System.out.println("not found");
			}

		}
		System.out.println("Test case start row no:" + testCaseRowNum);

		int testDataStartFrom = testCaseRowNum + 1;

		column = testCaseRowNum;
		//To check how many rows are present in test
		while (!excel.getdataofrow("shadi", 0, testDataStartFrom + totalTestDataRows).equalsIgnoreCase("")) {

			totalTestDataRows++;
		}

		System.out.println("Total testcases are :" + totalTestDataRows);
		//to check how many column are present in test
		while (!excel.getdataofrow("shadi", columnno, column).equalsIgnoreCase("")) {

			columnno++;
		}
		System.out.println("total No of column:" + columnno);
		//to provide data in in 2D array
		Object[][] data = new Object[totalTestDataRows][columnno];
		for (int rNum = testDataStartFrom; rNum < (testDataStartFrom + totalTestDataRows); rNum++) {
			for (int cNum = 0; cNum < columnno; cNum++) {
				System.out.println(excel.getdataofrow("shadi", cNum, rNum));
				data[rNum - testDataStartFrom][cNum] = excel.getdataofrow("shadi", cNum, rNum);
			}

		}
		return data;
	}

}
