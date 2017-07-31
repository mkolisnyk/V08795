package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Driver;
import com.sample.framework.Platform;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.controls.LocationLookupEdit;

public class SearchPage extends Page {
	@FindBy(locator = "com.booking:id/search_searchInput", platform = Platform.ANDROID_NATIVE)
	@FindBy(locator = "ss")
	public LocationLookupEdit editDestination;
	@FindBy(locator = "//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]")
	@FindBy(locator = "xpath=(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]",
		platform = Platform.ANDROID_NATIVE)
	public Control buttonTodaysDate;
	@FindBy(locator = "com.booking:id/business_purpose_business", platform = Platform.ANDROID_NATIVE)
	@FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[2]")
	public Control radioBusiness;
	@FindBy(locator = "com.booking:id/business_purpose_leisure", platform = Platform.ANDROID_NATIVE)
	@FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[1]")
	public Control radioLeisure;
	@FindBy(locator = "com.booking:id/search_search", platform = Platform.ANDROID_NATIVE)
	@FindBy(locator = "//button[@type='submit']")
	public Control buttonSearch;
	public SearchPage(WebDriver driverValue) {
		super(driverValue);
	}
}
