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
import com.sample.framework.Driver;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.pages.DestinationLookupPage;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

@RunWith(Parameterized.class)
public class SampleTest {

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
        //cap.setCapability("udid", Configuration.get("udid"));
        cap.setCapability("deviceName", Configuration.get("deviceName"));
        cap.setCapability("commandTimeout", Configuration.get("commandTimeout"));
        cap.setCapability("appActivity", Configuration.get("appActivity"));
        cap.setCapability("appPackage", Configuration.get("appPackage"));
        cap.setCapability("appWaitActivity", Configuration.get("appActivity"));
        cap.setCapability("appWaitPackage", Configuration.get("appPackage"));
        cap.setCapability("fullReset", true);
        WebDriver driver = new AndroidDriver<WebElement>(new URL(Configuration.get("driver_url")), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.add(driver);
	}
	@After
	public void tearDown() {
		Driver.current().quit();
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
	    LandingPage landingPage = new LandingPage(Driver.current());
	    landingPage.buttonStartSearch.click();
	    
	    SearchPage searchPage = new SearchPage(Driver.current());
	    searchPage.buttonDestination.click();
	    
	    DestinationLookupPage destinationLookupPage = new DestinationLookupPage(Driver.current());
	    destinationLookupPage.editDestinationInput.setText(destination);
	    Thread.sleep(3000);
	    destinationLookupPage.itemDestinationResult.element(0).click();
	    
	    searchPage.buttonTodaysDate.click();

		if (this.isBusiness) {
			searchPage.radioBusiness.click();
		} else {
			searchPage.radioLeisure.click();
		}
		searchPage.buttonSearch.click();
		SearchResultsPage searchResultsPage = new SearchResultsPage(Driver.current());
		String actualTitle = searchResultsPage.textSubTitle.getText();
		Assert.assertEquals(actualTitle, this.destination);
	}
}
