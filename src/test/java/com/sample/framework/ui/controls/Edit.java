package com.sample.framework.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.Page;

public class Edit extends Control {

    public Edit(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    public void setText(String value) throws Exception {
        this.click();
        this.element().clear();
        this.element().sendKeys(value);
    }
}
