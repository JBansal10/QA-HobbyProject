package com.qa.demo.Selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class TracksPageTest {

	private static WebDriver driver;

	private static Logger LOGGER = Logger.getGlobal();

	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void creatingATrack() throws Exception {
		LOGGER.warning("Connecting to the TRACK page now...");
		driver.get("http://localhost:8081/index.html");

		Thread.sleep(2000);
		TracksPage website = PageFactory.initElements(driver, TracksPage.class);

		website.createTest();
		driver.findElement(By.xpath("/html/body/div/button[2]")).click();
		driver.findElement(By.xpath("/html/body/div/button[1]")).click();

		String requiredText = "Test Track 1";
		assertEquals(true, driver.findElement(By.xpath("/html/body/div/div")).getText().contains(requiredText));

		Thread.sleep(10000);

	}

	@Test
	public void updatingATrack() throws Exception {
		LOGGER.warning("Connecting to the TRACK page now...");
		driver.get("http://localhost:8081/index.html");

		Thread.sleep(2000);
		TracksPage website = PageFactory.initElements(driver, TracksPage.class);

		website.updateTest();
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("/html/body/div/button[2]")).click();
		driver.findElement(By.xpath("/html/body/div/button[1]")).click();

		String requiredText = "Update test name";
		assertEquals(true, driver.findElement(By.xpath("/html/body/div/div")).getText().contains(requiredText));

		Thread.sleep(10000);
	}
	
	@Test
	public void deletingATrack() throws Exception {
		LOGGER.warning("Connecting to the TRACK page now...");
		driver.get("http://localhost:8081/index.html");
		
		Thread.sleep(2000);
		TracksPage website = PageFactory.initElements(driver, TracksPage.class);
		
		website.deleteTest();
	}

	@AfterAll
	public static void cleanUp() {
		driver.quit();
		LOGGER.info("=== LOGGING OFF OF THE DRIVER ===");

	}

}
