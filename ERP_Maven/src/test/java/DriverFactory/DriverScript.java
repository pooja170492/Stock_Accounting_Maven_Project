package DriverFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import CommonFunctions.FunctionLibrary;
import Utilities.ExcelFileUtil;
public class DriverScript {
	WebDriver driver;
String inputpath="E:\\10OClockOJT\\ERP_Maven\\TestInput\\InputSheet.xlsx";
String outputpath="E:\\10OClockOJT\\ERP_Maven\\TestOutput\\hybrid.xlsx";
ExtentReports report;
ExtentTest test;
Logger log;
public void startTest()throws Throwable
{
	log=Logger.getLogger(getClass());
	//load log4j propertyfile
	PropertyConfigurator.configure("E:\\10OClockOJT\\ERP_Maven\\PropertyFile\\Log4j.properties");		
	//creating excel object to access excel utilities
	ExcelFileUtil xl= new ExcelFileUtil(inputpath);
	//iterate all rows in Mastertestcases sheet
	for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
	{
		String moduleStatus="";	
	if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
	{
		//Define Module Name
		String TCModule=xl.getCellData("MasterTestCases", i, 1);
//define path of extetnts Reports
report= new ExtentReports("./ExtentReports/"+TCModule+FunctionLibrary.generateDate()+".html");		
		//iterate all rows in TCModule
		for(int j=1;j<=xl.rowCount(TCModule);j++)
		{
			test=report.startTest(TCModule);
			//read all cells from TCModule
		String Description= xl.getCellData(TCModule, j, 0);
		String Function_Name=xl.getCellData(TCModule, j, 1);
		String Locator_Type= xl.getCellData(TCModule, j, 2);
		String Locator_Value= xl.getCellData(TCModule, j, 3);
		String Test_Data= xl.getCellData(TCModule, j, 4);
		//calling the test step functions
		try {
			if(Function_Name.equalsIgnoreCase("startBrowser"))
			{
			driver=FunctionLibrary.startBrowser(driver);
			test.log(LogStatus.INFO, Description);
			log.info("Executing startBrowser");
			}
			else if(Function_Name.equalsIgnoreCase("openApplication"))
			{
				FunctionLibrary.openApplication(driver);
				test.log(LogStatus.INFO, Description);
				log.info("Executing openApplication");
			}
			else if(Function_Name.equalsIgnoreCase("waitForElement"))
			{
	FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
	test.log(LogStatus.INFO, Description);
	log.info("Executing waitForElement");
			}
			else if(Function_Name.equalsIgnoreCase("typeAction"))
			{
				FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
				test.log(LogStatus.INFO, Description);
				log.info("Executing typeAction");
			}
			else if(Function_Name.equalsIgnoreCase("clickAction"))
			{
				FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
				test.log(LogStatus.INFO, Description);
				log.info("Executing clickAction");
			}
			else if(Function_Name.equalsIgnoreCase("closeBrowser"))
			{
				FunctionLibrary.closeBrowser(driver);
				test.log(LogStatus.INFO, Description);
				log.info("Executing closeBrowser");
			}
			//call capturedata method
			else if(Function_Name.equalsIgnoreCase("captureData"))
			{
				FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
				test.log(LogStatus.INFO, Description);
				log.info("Executing captureData");
			}
			else if(Function_Name.equalsIgnoreCase("stableValidation"))
			{
				FunctionLibrary.stableValidation(driver, Test_Data);
				test.log(LogStatus.INFO, Description);
				log.info("Executing stableValidation");
			}
			//calling for stock table
			else if(Function_Name.equalsIgnoreCase("stockCategories"))
			{
				FunctionLibrary.stockCategories(driver);
				test.log(LogStatus.INFO, Description);
				log.info("Executing stockCategories");
			}
			else if(Function_Name.equalsIgnoreCase("sttableValidation"))
			{
				FunctionLibrary.sttableValidation(driver, Test_Data);
				test.log(LogStatus.INFO, Description);
				log.info("Executing sttableValidation");
			}
			//write as pass into status cell in TCModule
			xl.setCellData(TCModule, j, 5, "Pass", outputpath);
			test.log(LogStatus.PASS, Description);
				moduleStatus="true";
			
		}catch(Exception e)
		{
		System.out.println(e.getMessage());
		//write as fail into status cell in TCModule
		xl.setCellData(TCModule, j, 5, "Fail", outputpath);
		test.log(LogStatus.FAIL, Description);
		moduleStatus="false";
		}
		if(moduleStatus.equalsIgnoreCase("true"))
		{
			xl.setCellData("MasterTestCases", i, 3, "Pass", outputpath);
		}
		if(moduleStatus.equalsIgnoreCase("false"))
		{
		xl.setCellData("MasterTestCases", i, 3, "Fail", outputpath);	
		}
		report.endTest(test);
		report.flush();
		}
	}
	else
	{
		//write as not executed status into MasterTestCases sheet
		xl.setCellData("MasterTestCases", i, 3, "Not Executed", outputpath);
	}
	}
}
}
