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
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.pages.DestinationLookupPage;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

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
	    LandingPage landingPage = PageFactory.init(Driver.current(), LandingPage.class);
	    SearchPage searchPage = landingPage.buttonStartSearch.click(SearchPage.class);
	    DestinationLookupPage destinationLookupPage = searchPage.editDestination.click(DestinationLookupPage.class);
	    destinationLookupPage.editDestinationInput.setText(destination);
	    Thread.sleep(3000);
	    destinationLookupPage.itemDestinationResult.element(0).click();
	    
	    searchPage.buttonTodaysDate.click();

		if (isBusiness) {
			searchPage.radioBusiness.click();
		} else {
			searchPage.radioLeisure.click();
		}
		SearchResultsPage searchResultsPage = searchPage.buttonSearch.click(SearchResultsPage.class);
		String actualTitle = searchResultsPage.textSubTitle.getText();
		Assert.assertEquals(actualTitle, destination);
		searchResultsPage.captureScreenShot("./build/sample-" + destination + "-testng.png");
	}
}
