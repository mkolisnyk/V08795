package com.sample.tests.junit;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.utils.SystemUtils;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;

public class TestCommon {
    public SearchPage searchPage;

	public TestCommon() {
		// TODO Auto-generated constructor stub
	}
    @BeforeClass
    public static void beforeSuite() throws IOException {
        System.out.println("Before class");
        Configuration.load();
        if (Configuration.platform().isAndroidNative()) {
            System.out.println("Uninstall app");
            SystemUtils.uninstallApp(Configuration.get("appPackage"));
            System.out.println("Done");
        }
    }
    public void setUp(boolean reset) throws Exception {
        Configuration.load();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, Configuration.get("browser"));
        cap.setCapability("platformVersion", Configuration.get("platformVersion"));
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", new File(Configuration.get("app_path")).getAbsolutePath());
        //cap.setCapability("udid", Configuration.get("udid"));
        cap.setCapability("deviceName", Configuration.get("deviceName"));
        cap.setCapability("commandTimeout", Configuration.get("commandTimeout"));
        //cap.setCapability("appActivity", Configuration.get("appActivity"));
        //cap.setCapability("appPackage", Configuration.get("appPackage"));
        cap.setCapability("appWaitActivity", Configuration.get("appActivity"));
        cap.setCapability("appWaitPackage", Configuration.get("appPackage"));
        cap.setCapability("fullReset", false);
        cap.setCapability("noReset", true);
        cap.setCapability("fullReset", false);
        cap.setCapability("dontStopAppOnReset", true);
        if (Configuration.platform().isAndroidNative() && reset) {
            SystemUtils.setSystemTime();
            SystemUtils.resetAppData();
        }
        WebDriver driver = Driver.init(Configuration.get("driver_url"), Configuration.platform(), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.add(driver);
        if (Configuration.platform().isWeb()) {
                Driver.current().get(Configuration.get("url"));
        }
        if (Configuration.platform().isWeb()) {
            searchPage = PageFactory.init(Driver.current(), SearchPage.class);
        } else {
            LandingPage landingPage = PageFactory.init(Driver.current(), LandingPage.class);
            searchPage = landingPage.buttonStartSearch.click(SearchPage.class);
        }
    }
	@Before
	public void setUp() throws Exception {
		setUp(true);
	}
	@After
	public void tearDown() {
		Driver.current().quit();
	}

}
