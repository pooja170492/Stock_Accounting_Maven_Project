package ApplicationLayer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SupplierPage {
WebDriver driver;
WebDriverWait wait;
public SupplierPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="(//a[contains(text(),'Suppliers')])[2]")
WebElement clicksupplier;
@FindBy(xpath="//div[@class='panel-heading ewGridUpperPanel']//span[@class='glyphicon glyphicon-plus ewIcon']")
WebElement clickadd;
@FindBy(name="x_Supplier_Number")
WebElement snumber;
@FindBy(name="x_Supplier_Name")
WebElement entersname;
@FindBy(name="x_Address")
WebElement enteraddress;
@FindBy(name="x_City")
WebElement entercity;
@FindBy(name="x_Country")
WebElement entercountry;
@FindBy(name="x_Contact_Person")
WebElement entercperson;
@FindBy(name="x_Phone_Number")
WebElement enterpnumber;
@FindBy(name="x__Email")
WebElement enteremail;
@FindBy(name="x_Mobile_Number")
WebElement entermnumber;
@FindBy(name="x_Notes")
WebElement enternotes;
@FindBy(name="btnAction")
WebElement clickadd1;
@FindBy(xpath="//button[contains(text(),'OK!')]")
WebElement clickOk;
@FindBy(xpath="(//button[text()='OK'])[6]")
WebElement clickalertok;
@FindBy(xpath="//body/div/div/div/div/div/div[2]/div[1]/button[1]")
WebElement clicksearchpanel;
@FindBy(name="psearch")
WebElement searchtext;
@FindBy(name="btnsubmit")
WebElement clicksearch;
@FindBy(xpath="//table[@id='tbl_a_supplierslist']")
WebElement table;
public String verifySupplier(String sname,String addes,String city,
String country,String cpaerson,String pnumber,String email,String mnumber,
String notes)throws Throwable
{
	String res=null;
wait= new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.visibilityOf(clicksupplier));
this.clicksupplier.click();
wait.until(ExpectedConditions.elementToBeClickable(clickadd));
this.clickadd.click();
wait.until(ExpectedConditions.visibilityOf(snumber));
//Capture snumber into expected variable
String suppliernumber=this.snumber.getAttribute("value");
this.entersname.sendKeys(sname);
this.enteraddress.sendKeys(addes);
this.entercity.sendKeys(city);
this.entercountry.sendKeys(country);
this.entercperson.sendKeys(cpaerson);
this.enterpnumber.sendKeys(pnumber);
this.enteremail.sendKeys(email);
this.entermnumber.sendKeys(mnumber);
this.enternotes.sendKeys(notes);
this.clickadd1.click();
wait.until(ExpectedConditions.elementToBeClickable(clickOk));
this.clickOk.click();
wait.until(ExpectedConditions.elementToBeClickable(clickalertok));
this.clickalertok.click();
Thread.sleep(4000);
if(!searchtext.isDisplayed())
this.clicksearchpanel.click();	
Thread.sleep(4000);
this.searchtext.clear();
this.searchtext.sendKeys(suppliernumber);
this.clicksearch.click();
Thread.sleep(4000);
List<WebElement>rows=table.findElements(By.tagName("tr"));
System.out.println("No of rows are::"+rows.size());
//capture supplier number from supplier table
String actual=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr/td[6]/div/span/span")).getText();
Reporter.log(suppliernumber+"           "+actual,true);
if(actual.equals(suppliernumber))
{
	Reporter.log("Supplier creation is success",true);
	res="pass";
	
}
else
{
	Reporter.log("Supplier creation is Fail",true);
	res="Fail";
}
return res;	
}
}











