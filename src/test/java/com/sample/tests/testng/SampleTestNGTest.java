package com.sample.tests.testng;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;

public class SampleTestNGTest {

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		Configuration.load();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        cap.setCapability("platformVersion", Configuration.get("platformVersion"));
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", new File(Configuration.get("app_path")).getAbsolutePath());
        cap.setCapability("udid", Configuration.get("udid"));
        cap.setCapability("deviceName", Configuration.get("deviceName"));
        cap.setCapability("commandTimeout", Configuration.get("commandTimeout"));
        cap.setCapability("appActivity", Configuration.get("appActivity"));
        cap.setCapability("appPackage", Configuration.get("appPackage"));
        cap.setCapability("appWaitActivity", Configuration.get("appActivity"));
        cap.setCapability("appWaitPackage", Configuration.get("appPackage"));
        cap.setCapability("fullReset", true);
        WebDriver driver = new AndroidDriver<WebElement>(new URL(Configuration.get("driver_url")), cap);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        Driver.add(driver);
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		Driver.current().quit();
	}

	@DataProvider(name = "source")
	public static Object[][] getParameters() {
        return new Object[][] {
        		{"London", true },
        		{"Manchester", false},
        };
    }
	
	@Test(dataProvider="source")
	public void testSample(String destination, boolean isBusiness) throws Exception {
		Driver.current().findElement(By.id("com.booking:id/btn_start_search")).click();
		Driver.current().findElement(By.id("com.booking:id/search_searchInput")).click();
		Driver.current().findElement(By.id("com.booking:id/disam_search")).sendKeys(destination);
		Thread.sleep(3000);
		Driver.current().findElements(By.id("com.booking:id/disam_list_root")).get(0).click();
		Driver.current().findElement(By.xpath("(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]")).click();
		if (isBusiness) {
			Driver.current().findElement(By.id("com.booking:id/business_purpose_business")).click();
		} else {
			Driver.current().findElement(By.id("com.booking:id/business_purpose_leisure")).click();
		}
		Driver.current().findElement(By.id("com.booking:id/search_search")).click();
		String actualTitle = Driver.current().findElement(By.id("com.booking:id/subtitle_layout_text")).getText();
		Assert.assertEquals(actualTitle, destination);
	}
}
