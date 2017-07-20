package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

public class DestinationLookupPage extends Page {
    public Edit editDestinationInput;
    public Control itemDestinationResult;

	public DestinationLookupPage(WebDriver driverValue) {
		super(driverValue);
	    editDestinationInput = new Edit(this, By.id("com.booking:id/disam_search"));
	    itemDestinationResult = new Control(this, By.id("com.booking:id/disam_list_root"));
	}

}
