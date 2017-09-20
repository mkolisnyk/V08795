package com.sample.tests.pages;

import java.io.UnsupportedEncodingException;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.utils.SystemUtils;


public class PlayStorePage extends Page {

    public PlayStorePage(WebDriver driverValue) {
        super(driverValue);
        // TODO Auto-generated constructor stub
    }

    @FindBy(locator = "//android.widget.ImageView[contains(@resource-id, 'id/title_thumbnail')]")
    public Control iconTitle;

    @FindBy(locator = "//*[contains(@text, 'INSTALL')]", excludeFromSearch = true)
    public Control buttonInstall;

    @FindBy(locator = "//*[contains(@text, 'UNINSTALL')]", excludeFromSearch = true)
    public Control buttonUninstall;

    @FindBy(locator = "//*[@text='ACCEPT']", excludeFromSearch = true)
    public Control buttonAccept;

    @FindBy(locator = "//*[@text='OPEN']", excludeFromSearch = true)
    public Control buttonOpen;

    @Override
    public Page navigate() {
        SystemUtils.openDeepLink("market://details?id=com.thetrainline");
        return this;
    }
}
