package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {
WebDriver driver;
WebDriverWait wait;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(linkText="Logout")
WebElement clickLogout;
@FindBy(name="btnsubmit")
WebElement loginbuuton;
public String verifyLogout()
{
	String res=null;
	wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.elementToBeClickable(clickLogout));
	this.clickLogout.click();
	wait.until(ExpectedConditions.visibilityOf(loginbuuton));
	if(loginbuuton.isDisplayed())
	{
	res="Logout Success";	
	}
	else
	{
	res="Logout Fail";
	}
	return res;
}
}








