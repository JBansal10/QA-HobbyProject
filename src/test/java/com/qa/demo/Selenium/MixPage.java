package com.qa.demo.Selenium;

import java.sql.Time;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class MixPage {

	@FindBy(xpath = "/html/body/div/button[1]")
	private WebElement readBtn;

	@FindBy(xpath = "/html/body/form[1]/div[1]/input")
	private WebElement mixName;

	@FindBy(xpath = "/html/body/form[1]/div[2]/input")
	private WebElement mixLength;

	@FindBy(xpath = "/html/body/form[1]/button[1]")
	private WebElement createBtn;

	@FindBy(xpath = "/html/body/form[2]/div[1]/input")
	private WebElement updateMixID;

	@FindBy(xpath = "/html/body/form[2]/div[2]/input")
	private WebElement updateMixName;

	@FindBy(xpath = "/html/body/form[2]/div[3]/input")
	private WebElement updateMixLength;

	@FindBy(xpath = "/html/body/form[2]/button[1]")
	private WebElement updateBtn;
	
	@FindBy(xpath = "/html/body/form[3]/div[1]/input")
	private WebElement deleteMixID;
	
	@FindBy(xpath = "/html/body/form[3]/button[1]")
	private WebElement deleteBtn;

	public void createTest() {
		this.readBtn.click();
		this.mixName.sendKeys("This is a test mix");
		this.mixLength.sendKeys("00:56:56");

		this.createBtn.click();
		this.readBtn.click();
	}

	public void updateTest() {
		this.updateMixID.sendKeys("1");
		this.updateMixName.sendKeys("update test mix");
		this.updateMixLength.sendKeys("00:23:56");

		this.updateBtn.click();
	}
	
	public void deleteTest() {
		this.deleteMixID.sendKeys("2");
		
		this.deleteBtn.click();
	}

//	@AfterAll
//	public static void cleanUp() {
//		driver.quit();
//		LOGGER.info("=== LOGGING OFF OF THE DRIVER ===");
//	}
}
