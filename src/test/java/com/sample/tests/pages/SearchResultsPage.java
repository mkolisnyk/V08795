package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

public class SearchResultsPage extends Page {

	@FindBy(locator = "com.booking:id/subtitle_layout_text")
	public Control textSubTitle;
	public SearchResultsPage(WebDriver driverValue) {
		super(driverValue);
	}

}
