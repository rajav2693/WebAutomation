package com.webAutomation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utill.ExtentManager;

public class NDTV_hrefTest {
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	WebDriver driver = null;
	
	@Test
	public void webapptopMenuTest() throws InterruptedException, MalformedURLException {

    System.setProperty("webdriver.chrome.driver","C:\\Users\\Rajesh.Veeranna\\Desktop\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    
	test=rep.startTest("webapptopMenuTest");
	test.log(LogStatus.INFO, "Strarting the Test");
    
    driver.get("https://www.ndtv.com/business");
    
	takeScreenshot();
	test.log(LogStatus.INFO, "Application Launched Successfully");
      
	 //Get list of web-elements with tagName  - a  TOP MENU
	 List<WebElement> allLinks = driver.findElements(By.xpath("//div[@class='nglobalnav_wrap']//descendant::a"));
	
	 //Traversing through the list and printing its text along with link address
	 
	test.log(LogStatus.INFO, "-----All the Top MENU with href link displayed---------");

	 for(WebElement link:allLinks){
	 System.out.println(link.getText() + " - " + link.getAttribute("href"));
	 
		test.log(LogStatus.INFO, link.getText() + " - " + link.getAttribute("href"));
	 }
	 
	 Thread.sleep(300);
     JavascriptExecutor js = (JavascriptExecutor) driver;
     js.executeScript("window.scrollBy(0,8000)");

     takeScreenshot();
	 //Get list of web-elements with tagName  - a  TOP MENU
	 List<WebElement> allLinks1 = driver.findElements(By.xpath("//li[@class='more']/a"));
	
	 //Traversing through the list and printing its text along with link address
	test.log(LogStatus.INFO, "------All the Bottom MENU with href link displayed-------");

	 for(WebElement link1:allLinks1){
	 System.out.println(link1.getText() + " - " + link1.getAttribute("href"));
	 
		test.log(LogStatus.INFO, link1.getText() + " - " + link1.getAttribute("href"));
		
	 }
		test.log(LogStatus.PASS, "Test Passed");

	 } 
	  

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void takeScreenshot() {
		
		Date d = new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		String path="E:\\AutomationReports\\"+"screenshots\\"+screenshotFile;
		// take screenshot
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//add screenshot to report
		test.log(LogStatus.INFO,"Snapshot below: ("+screenshotFile+")"+
		test.addScreenCapture(path));
	}
	
	@AfterMethod
	public void quit() {
		if (rep!=null) {
			rep.endTest(test);
			rep.flush();	
		}
		if (driver!=null)
			driver.quit();
	}  
	
		
	}
    

