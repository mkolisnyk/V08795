package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;

public class LandingPage extends Page {

	public Control buttonStartSearch;
	
	public LandingPage(WebDriver driverValue) {
		super(driverValue);
		buttonStartSearch = new Control(this, By.id("com.booking:id/btn_start_search"));
	}

}
