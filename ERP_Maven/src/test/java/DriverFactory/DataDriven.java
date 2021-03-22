package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ApplicationLayer.LoginPage;
import ApplicationLayer.SupplierPage;
import Utilities.ExcelFileUtil;

public class DataDriven {
WebDriver driver;
String inputpath="E:\\10OClockOJT\\ERP_Maven\\TestInput\\TestData.xlsx";
String outputpath="E:\\10OClockOJT\\ERP_Maven\\TestOutput\\datadriven.xlsx";
@BeforeTest
public void setUp()throws Throwable
{
System.setProperty("webdriver.chrome.driver", "E:\\10OClockOJT\\ERP_Maven\\CommonDrivers\\chromedriver.exe");
driver= new ChromeDriver();
driver.navigate().to("http://projects.qedgetech.com/webapp");
driver.manage().window().maximize();
Thread.sleep(5000);
//call login page
LoginPage login=PageFactory.initElements(driver, LoginPage.class);
String result=login.verifyLogin("admin", "master");
Reporter.log(result,true);
}
@Test
public void supplier()throws Throwable
{
	//call supplier page class
SupplierPage supplierc=PageFactory.initElements(driver, SupplierPage.class);
//create object for excelfileutil 
ExcelFileUtil xl= new ExcelFileUtil(inputpath);
//count no of rows in Supplier sheet
int rc=xl.rowCount("Supplier");
Reporter.log("No of rows::"+rc,true);
for(int i=1;i<=rc;i++)
{
//read all columns from Supplier sheet
String sname=xl.getCellData("Supplier", i, 0);
String address=xl.getCellData("Supplier", i, 1);
String city=xl.getCellData("Supplier", i, 2);
String country=xl.getCellData("Supplier", i, 3);
String cperson=xl.getCellData("Supplier", i, 4);
String pnumber=xl.getCellData("Supplier", i, 5);
String mail=xl.getCellData("Supplier", i, 6);
String mnumber=xl.getCellData("Supplier", i, 7);
String note=xl.getCellData("Supplier", i, 8);
String results=supplierc.verifySupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, note);
Reporter.log(results,true);
//write into results
xl.setCellData("Supplier", i, 9, results, outputpath);
}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}






