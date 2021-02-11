package com.qa.demo.Selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TracksPage {

	@FindBy(xpath = "/html/body/div/button[1]")
	private WebElement readBtn;

	@FindBy(xpath = "/html/body/form[1]/div[1]/input")
	private WebElement trackName;

	@FindBy(xpath = "/html/body/form[1]/div[2]/input")
	private WebElement trackArtist;

	@FindBy(xpath = "/html/body/form[1]/div[3]/input")
	private WebElement trackLength;

	@FindBy(xpath = "/html/body/form[1]/div[4]/input")
	private WebElement trackMixID;

	@FindBy(xpath = "/html/body/form[1]/button[1]")
	private WebElement createBtn;

	@FindBy(xpath = "/html/body/form[2]/div[1]/input")
	private WebElement updateID;

	@FindBy(xpath = "/html/body/form[2]/div[2]/input")
	private WebElement updateTrackName;

	@FindBy(xpath = "/html/body/form[2]/div[3]/input")
	private WebElement updateTrackArtist;

	@FindBy(xpath = "/html/body/form[2]/div[4]/input")
	private WebElement updateTrackLength;

	@FindBy(xpath = "/html/body/form[2]/div[5]/input")
	private WebElement updateTrackMixID;

	@FindBy(xpath = "/html/body/form[2]/button[1]")
	private WebElement updateBtn;

	@FindBy(xpath = "/html/body/form[3]/div[1]/input")
	private WebElement deleteID;

	@FindBy(xpath = "/html/body/form[3]/button[1]")
	private WebElement deleteBtn;

	public void createTest() {

		this.trackName.sendKeys("Test Track 1");
		this.trackArtist.sendKeys("Test Artist");
		this.trackLength.sendKeys("00:05:23");
		this.trackMixID.sendKeys("1");

		this.createBtn.click();

	}

	public void updateTest() {
		
		this.updateID.sendKeys("3");
		this.updateTrackName.sendKeys("Update test name");
		this.updateTrackArtist.sendKeys("Update artist name");
		this.updateTrackLength.sendKeys("00:4:29");
		this.updateTrackMixID.sendKeys("1");
		
		this.updateBtn.click();
	}
	
	public void deleteTest() {
		this.deleteID.sendKeys("1");
		this.deleteBtn.click();
	}
	
}
