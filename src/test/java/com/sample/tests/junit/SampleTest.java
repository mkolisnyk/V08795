package com.sample.tests.junit;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.sample.framework.Configuration;

public class SampleTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		Configuration.load();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        cap.setCapability("platformVersion", Configuration.get("platformVersion"));
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", new File(Configuration.get("app_path")).getAbsolutePath());
        cap.setCapability("deviceName", Configuration.get("deviceName"));
        cap.setCapability("commandTimeout", Configuration.get("commandTimeout"));
        cap.setCapability("appActivity", Configuration.get("appActivity"));
        cap.setCapability("appPackage", Configuration.get("appPackage"));
        cap.setCapability("appWaitActivity", Configuration.get("appActivity"));
        cap.setCapability("appWaitPackage", Configuration.get("appPackage"));
        cap.setCapability("fullReset", true);
        driver = new AndroidDriver<WebElement>(new URL(Configuration.get("driver_url")), cap);
	}
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testSample() {
		driver.findElement(By.id("com.booking:id/btn_start_search")).click();
		driver.findElement(By.id("com.booking:id/search_searchInput")).click();
		driver.findElement(By.id("com.booking:id/disam_search")).sendKeys("Manchester");
		driver.findElements(By.id("com.booking:id/disam_list_root")).get(0).click();
		driver.findElement(By.xpath("(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]")).click();
		driver.findElement(By.id("com.booking:id/business_purpose_business")).click();
		driver.findElement(By.id("com.booking:id/search_search")).click();
		String actualTitle = driver.findElement(By.id("com.booking:id/subtitle_layout_text")).getText();
		Assert.assertEquals(actualTitle, "Manchester");
		
		
	}
}
