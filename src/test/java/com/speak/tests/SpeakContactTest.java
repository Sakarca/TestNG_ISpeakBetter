package com.speak.tests;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.speak.base.BasePage;
import com.speak.pages.SpeakContactPage;
import com.speak.pages.SpeakHomePage;
import com.speak.util.Constants;

public class SpeakContactTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	SpeakContactPage contactPage;
	
	By phone = By.className("icon-phone"); 
	By map = By.className("icon-map-pin");
	By mail = By.className("icon-envelope");
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		contactPage= new SpeakContactPage(driver);
		contactPage.goToContactPage();
	}
    
	
	@Test (priority=1)
	public void verifyPhone() {
		
		Assert.assertEquals(contactPage.checkElementClickable(phone), false); 
	}
		
	@Test (priority=2)
	public void verifyAddress(){
		Assert.assertEquals(contactPage.checkElementClickable(map), false);	
	}
	
	@Test (priority=3)
	public void verifyEmail(){
		Assert.assertEquals(contactPage.checkElementClickable(mail), false);	
	}
	@Test (priority=4, enabled=false)
	public void contactCourse(){
		Assert.assertEquals(contactPage.sendMessage(), "Thanks for contacting us!");
	}
	
	@AfterTest
	public void tearDown(){
		basePage.quitBrowser();
	}

}
