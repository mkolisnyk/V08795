package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

public class SearchResultsPage extends Page {

	public Control textSubTitle;
	public SearchResultsPage(WebDriver driverValue) {
		super(driverValue);
		textSubTitle = new Control(this, By.id("com.booking:id/subtitle_layout_text"));
	}

}
