package com.sample.framework.ui;

import io.appium.java_client.AppiumDriver;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import com.sample.framework.Driver;
import com.sample.framework.ui.controls.Control;

public class Page {
    private static final long TINY_TIMEOUT = 1;
    private static final long SHORT_TIMEOUT = 5;
    private static final int SCROLL_TOP_PART = 9;
    private static final int SCROLL_TOTAL_PARTS = 10;
    private WebDriver driver;

    public Page(WebDriver driverValue) {
        this.driver = driverValue;
    }

    public WebDriver getDriver() {
        return driver;
    }
    public Page navigate() {
        return this;
    }
    public boolean isTextPresent(String text) {
        String locator = String.format("//*[text()='%s' or contains(text(), %s)]", text, text);
        Control element = new Control(this, By.xpath(locator));
        return element.exists();
    }
    public byte[] captureScreenShot() throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(this.getDriver());
        byte[] data = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        return data;
    }
    public File captureScreenShot(String destination) throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(this.getDriver());
        File srcFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        File output = new File(destination);
        FileUtils.copyFile(srcFile, output);
        return output;
    }
    public String getSource() {
        return this.getDriver().getPageSource();
    }
    public Control getScrollable() {
        Control scrollable = new Control(this,
                By.xpath("(//*[@scrollable='true'])[1]"));
        return scrollable;
    }
    public Control getTextControl(String message) {
        Control text = null;
        String locator = "";
            locator = "//*[@text=\"" + message
                    + "\" or contains(@text,\"" + message
                    + "\") or contains(text(),\"" + message
                    + "\") or text()=\"" + message
                    + "\" or contains(@content-desc,\"" + message
                    + "\")]";
        text = new Control(this, By.xpath(locator));
        return text;
    }
    private static Rectangle getScreenSize() {
        Rectangle area = new Rectangle();
        Dimension size = Driver.current().manage().window().getSize();
        area.setBounds(0, 0, size.getWidth(), size.getHeight());
        return area;
    }
    public boolean swipeScreen(boolean vertical, boolean leftTop, boolean once) {
        return swipeScreen(vertical, leftTop, once, 2);
    }
    public boolean swipeScreen(boolean vertical, boolean leftTop, boolean once, int seconds) {
        Control scrollable = getScrollable();
        if (!scrollable.exists(SHORT_TIMEOUT)) {
            return false;
        }
        Rectangle area = scrollable.getRect();
        Rectangle screenArea = Page.getScreenSize();
        area.x = Math.max(area.x, screenArea.x);
        area.y = Math.max(area.y, screenArea.y);
        area.width = Math.min(area.width, screenArea.width - area.x);
        area.height = Math.min(area.height, screenArea.height - area.y);

        int startX = area.x + area.width / 2;
        int startY = 0;
        int endX = area.x + area.width / 2;
        int endY = 0;
        if (vertical) {
            startX = area.x + area.width / 2;
            endX = area.x + area.width / 2;
            if (leftTop) {
                startY = area.y + area.height / SCROLL_TOTAL_PARTS;
                endY = area.y + SCROLL_TOP_PART * area.height / SCROLL_TOTAL_PARTS;
            } else {
                startY = area.y + SCROLL_TOP_PART * area.height / SCROLL_TOTAL_PARTS;
                endY = area.y + area.height / SCROLL_TOTAL_PARTS;
            }
        } else {
            startY = area.y + area.height / 2;
            endY = area.y + area.height / 2;
            if (leftTop) {
                startX = area.x + SCROLL_TOP_PART * area.width / SCROLL_TOTAL_PARTS;
                endX = area.x + area.width / SCROLL_TOTAL_PARTS;
            } else {
                startX = area.x + area.width / SCROLL_TOTAL_PARTS;
                endX = area.x + SCROLL_TOP_PART * area.width / SCROLL_TOTAL_PARTS;
            }
        }
        String prevState = "";
        String currentState = this.getSource();
        int times = 0;
        final int maxTries = 50;
        while (!currentState.equals(prevState)) {
            ((AppiumDriver) this.getDriver()).swipe(startX, startY, endX, endY,
                    seconds * 1000);
            if (once || times > maxTries) {
                break;
            }
            times++;
            prevState = currentState;
            currentState = this.getSource();
        }
        return true;
    }

    public boolean scrollTo(Control control, boolean up) {
        if (control.exists(TINY_TIMEOUT)) {
            return true;
        }
        Control scrollable = getScrollable();
        if (!scrollable.exists(TINY_TIMEOUT)) {
            return false;
        }
        String prevState = "";
        String currentState = this.getSource();
        while (!currentState.equals(prevState) && this.swipeScreen(true, up, true)) {
            if (control.exists(TINY_TIMEOUT)) {
                return true;
            }
            prevState = currentState;
            currentState = this.getSource();
        }
        return false;
    }
    public boolean scrollTo(boolean up) throws Exception {
        return swipeScreen(true, up, false);
    }
    public boolean scrollTo(Control control, ScrollTo scrollDirection) {
        switch (scrollDirection) {
            case TOP_ONLY:
                return scrollTo(control, true);
            case TOP_BOTTOM:
                return scrollTo(control, true) || scrollTo(control, false);
            case BOTTOM_ONLY:
                return scrollTo(control, false);
            case BOTTOM_TOP:
                return scrollTo(control, false) || scrollTo(control, true);
            default:
                return scrollTo(control, true) || scrollTo(control, false);
        }
    }
    public boolean scrollTo(Control control) throws Exception {
        return scrollTo(control, ScrollTo.TOP_BOTTOM);
    }
    public boolean scrollTo(String text, boolean up) throws Exception {
        Control control = this.getTextControl(text);
        return this.scrollTo(control, up);
    }
    public boolean scrollTo(String text, ScrollTo scrollDirection) {
        Control control = this.getTextControl(text);
        return scrollTo(control, scrollDirection);
    }
    public boolean scrollTo(String text) {
        return scrollTo(text, ScrollTo.TOP_BOTTOM);
    }
}
