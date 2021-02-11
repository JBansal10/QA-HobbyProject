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

public class MixPageTests {

	private static WebDriver driver;
	
	private static Logger LOGGER = Logger.getGlobal();
	
	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	@Test
	public void creatingAMix() throws Exception {
		LOGGER.warning("Connecting to the MIX page now...");
		driver.get("http://localhost:8081/mixes.html");
		
		Thread.sleep(2000);
		MixPage website = PageFactory.initElements(driver, MixPage.class);
		
		website.createTest();
		driver.findElement(By.xpath("/html/body/div/button[2]")).click();
		driver.findElement(By.xpath("/html/body/div/button[1]")).click();
		
		String requiredText = "This is a test mix";
		assertEquals(true, 
				driver.findElement(By.xpath("/html/body/div/div")).getText().contains(requiredText));
		
		Thread.sleep(10000);
		
	}
	
	@Test
	public void updatingAMix() throws Exception {
		LOGGER.warning("Connecting to the MIX page now...");
		driver.get("http://localhost:8081/mixes.html");
		
		Thread.sleep(2000);
		MixPage website = PageFactory.initElements(driver, MixPage.class);
		
		website.updateTest();
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("/html/body/div/button[2]")).click();
		driver.findElement(By.xpath("/html/body/div/button[1]")).click();
		
		String requiredText = "update test mix";
		assertEquals(true, 
				driver.findElement(By.xpath("/html/body/div/div")).getText().contains(requiredText));
		
		
		Thread.sleep(10000);
	}
	
	@Test
	public void deletingAMix() throws Exception {
		LOGGER.warning("Connecting to the MIX page now...");
		driver.get("http://localhost:8081/mixes.html");
		
		Thread.sleep(2000);
		MixPage website = PageFactory.initElements(driver, MixPage.class);
		
		website.deleteTest();
		Thread.sleep(10000);
	}
	
	@AfterAll
	public static void cleanUp() {
		driver.quit();
		LOGGER.info("=== LOGGING OFF OF THE DRIVER ===");
	
	}
}
