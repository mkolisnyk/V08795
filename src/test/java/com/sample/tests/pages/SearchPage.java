package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Driver;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

public class SearchPage extends Page {
	@FindBy(locator = "com.booking:id/search_searchInput")
	public Control buttonDestination;
	@FindBy(locator = "xpath=(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]")
	public Control buttonTodaysDate;
	@FindBy(locator = "com.booking:id/business_purpose_business")
	public Control radioBusiness;
	@FindBy(locator = "com.booking:id/business_purpose_leisure")
	public Control radioLeisure;
	@FindBy(locator = "com.booking:id/search_search")
	public Control buttonSearch;
	public SearchPage(WebDriver driverValue) {
		super(driverValue);
	}

}
