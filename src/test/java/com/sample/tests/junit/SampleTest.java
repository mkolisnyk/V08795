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
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.pages.DestinationLookupPage;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

@RunWith(Parameterized.class)
public class SampleTest extends TestCommon {

	private String destination;
	private boolean isBusiness;
	
	public SampleTest(String destination, boolean isBusiness) {
		super();
		this.destination = destination;
		this.isBusiness = isBusiness;
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
	    LandingPage landingPage = PageFactory.init(Driver.current(), LandingPage.class);
	    SearchPage searchPage = landingPage.buttonStartSearch.click(SearchPage.class);
	    DestinationLookupPage destinationLookupPage = searchPage.buttonDestination.click(DestinationLookupPage.class);
	    destinationLookupPage.editDestinationInput.setText(destination);
	    Thread.sleep(3000);
	    destinationLookupPage.itemDestinationResult.element(0).click();
	    
	    searchPage.buttonTodaysDate.click();

		if (this.isBusiness) {
			searchPage.radioBusiness.click();
		} else {
			searchPage.radioLeisure.click();
		}
		SearchResultsPage searchResultsPage = searchPage.buttonSearch.click(SearchResultsPage.class);
		String actualTitle = searchResultsPage.textSubTitle.getText();
		Assert.assertEquals(actualTitle, this.destination);
		searchResultsPage.captureScreenShot("./build/sample-" + destination + ".png");
	}
}
