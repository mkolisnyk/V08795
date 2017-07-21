package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

public class DestinationLookupPage extends Page {
	@FindBy(locator = "com.booking:id/disam_search")
    public Edit editDestinationInput;
	@FindBy(locator = "com.booking:id/disam_list_root")
    public Control itemDestinationResult;

	public DestinationLookupPage(WebDriver driverValue) {
		super(driverValue);
	}

}
