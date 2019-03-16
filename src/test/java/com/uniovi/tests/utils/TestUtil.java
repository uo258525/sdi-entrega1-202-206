package com.uniovi.tests.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestUtil {

	private WebDriver driver;

	private static final int TIME = 2;

	public TestUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void waitChangeWeb() {
		try { 
			TimeUnit.SECONDS.sleep(TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void searchText(String text, boolean exists) {
		List<WebElement> list = driver
				.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
		if (exists) {
			assertTrue(text, list.size() > 0);
		} else {
			assertFalse(text, list.size() > 0);
		}

	}

}
