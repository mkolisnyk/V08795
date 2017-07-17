package com.sample.framework;

import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;

public final class Driver {

	private Driver() {
	}

	private static ConcurrentHashMap<String, WebDriver> driverThreadMap = new ConcurrentHashMap<String, WebDriver>();

	public static String getThreadName() {
		return Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
	}
    public static void add(WebDriver driver) throws Exception {
        String threadName = getThreadName();
        driverThreadMap.put(threadName, driver);
    }
   public static WebDriver current() {
		String threadName = getThreadName();
		return driverThreadMap.get(threadName);
	}
}
