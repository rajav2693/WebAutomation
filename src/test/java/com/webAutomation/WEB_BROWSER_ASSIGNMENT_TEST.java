package com.webAutomation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utill.ExtentManager;


public class WEB_BROWSER_ASSIGNMENT_TEST {
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	WebDriver driver = null;

	@Test
	public void webBrowserAppTest() throws InterruptedException, MalformedURLException {
		
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Rajesh.Veeranna\\Desktop\\chromedriver.exe");

        driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(2000); 
		
		test=rep.startTest("webBrowserAppTest");
		test.log(LogStatus.INFO, "Strarting the Test");	
		
		driver.get("https://jqueryui.com/animate/");

		takeScreenshot();
		test.log(LogStatus.INFO, "Application Launched Successfully");	
		
		driver.switchTo().frame(0);
		WebDriverWait wait = new WebDriverWait(driver,1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button")));
		takeScreenshot();
		test.log(LogStatus.INFO, "Click on \"Toggle Effect\"");	
		driver.findElement(By.id("button")).click();
		
		test.log(LogStatus.INFO, "Animate contents displayed as expected");
		takeScreenshot();
		
		String actual = "Animate\r\n" + 
				"Etiam libero neque, luctus a, eleifend nec, semper at, lorem. Sed pede. Nulla lorem metus, adipiscing ut, luctus sed, hendrerit vitae, mi.";
		System.out.println(driver.findElement(By.xpath("//*[@id=\"effect\"]")).isDisplayed());
		String expected = (driver.findElement(By.xpath("//*[@id=\"effect\"]")).getText());
		
		if 	(!(actual	== expected)) {
			System.out.println("Displayed Animated box verified as expected");
		}else {
			
			System.out.println("Displayed Animated box is not as expected");

		}
		
		takeScreenshot();

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