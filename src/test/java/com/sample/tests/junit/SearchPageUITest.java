package com.sample.tests.junit;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;

public class SearchPageUITest extends TestCommon {

    public SearchPageUITest() {
        // TODO Auto-generated constructor stub
    }


    @Test
    public void testVerifyUIOnSearchPage() throws Exception {
    		SearchPage searchPage;
    		if (Configuration.platform().isWeb()) {
    			searchPage = PageFactory.init(Driver.current(), SearchPage.class);
    		} else {
    		    LandingPage landingPage = PageFactory.init(Driver.current(), LandingPage.class);
    		    searchPage = landingPage.buttonStartSearch.click(SearchPage.class);
    		}

	    Assert.assertTrue(searchPage.buttonDestination.exists());
        Assert.assertTrue(searchPage.radioBusiness.exists());
        Assert.assertTrue(searchPage.radioLeisure.exists());
        Assert.assertTrue(searchPage.buttonSearch.exists());
    }
}
