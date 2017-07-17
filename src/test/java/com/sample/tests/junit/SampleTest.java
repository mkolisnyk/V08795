package com.sample.tests.junit;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.sample.framework.Configuration;

@RunWith(Parameterized.class)
public class SampleTest {

	private WebDriver driver;

	private String destination;
	private boolean isBusiness;
	
	public SampleTest(String destination, boolean isBusiness) {
		super();
		this.destination = destination;
		this.isBusiness = isBusiness;
	}
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(
            new Object[][] {
            		{"London", true},
            		{"Manchester", false},
            });
    }
	
	@Test
	public void testSample() throws Exception {
		driver.findElement(By.id("com.booking:id/btn_start_search")).click();
		driver.findElement(By.id("com.booking:id/search_searchInput")).click();
		driver.findElement(By.id("com.booking:id/disam_search")).sendKeys(this.destination);
		Thread.sleep(3000);
		driver.findElements(By.id("com.booking:id/disam_list_root")).get(0).click();
		driver.findElement(By.xpath("(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]")).click();
		if (this.isBusiness) {
			driver.findElement(By.id("com.booking:id/business_purpose_business")).click();
		} else {
			driver.findElement(By.id("com.booking:id/business_purpose_leisure")).click();
		}
		driver.findElement(By.id("com.booking:id/search_search")).click();
		String actualTitle = driver.findElement(By.id("com.booking:id/subtitle_layout_text")).getText();
		Assert.assertEquals(actualTitle, this.destination);
	}
}
