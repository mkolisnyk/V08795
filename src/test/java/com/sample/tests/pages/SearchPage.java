package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

public class SearchPage extends Page {
	public Control buttonDestination;
	public Control buttonTodaysDate;
	public Control radioBusiness;
	public Control radioLeisure;
	public Control buttonSearch;
	public SearchPage(WebDriver driverValue) {
		super(driverValue);
		buttonDestination = new Control(this, By.id("com.booking:id/search_searchInput"));
	    buttonTodaysDate = new Control(this,
	    		By.xpath("(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]"));
	    radioBusiness = new Control(this, By.id("com.booking:id/business_purpose_business"));
	    radioLeisure = new Control(this, By.id("com.booking:id/business_purpose_leisure"));
	    buttonSearch = new Control(this, By.id("com.booking:id/search_search"));
	}

}
