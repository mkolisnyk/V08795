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

public class SampleTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        cap.setCapability("platformVersion", "4.4");
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", new File("./base.apk").getAbsolutePath());
        cap.setCapability("deviceName", "Any");
        cap.setCapability("commandTimeout", "60");
        cap.setCapability("appActivity", ".activity.OnboardingActivity");
        cap.setCapability("appPackage", "com.booking");
        cap.setCapability("appWaitActivity", ".activity.OnboardingActivity");
        cap.setCapability("appWaitPackage", "com.booking");
        cap.setCapability("fullReset", true);
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
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
