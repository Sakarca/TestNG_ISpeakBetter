package com.speak.tests;

import java.util.Properties;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.speak.base.BasePage;
import com.speak.pages.SpeakHomePage;
import com.speak.util.Constants;
import com.speak.util.ElementUtil;

@Listeners(com.speak.listeners.TestAllureListener.class)
public class SpeakHomeTest {
	//Logger log = Logger.getLogger("HotelsMainTest");
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	SpeakHomePage homePage;
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		homePage= new SpeakHomePage(driver);
	}
    @Test(priority=1, description="Verifies Page Title")
    public void verifyMainPageTitle(){
    		//log.info("getting Main Page Title");
    		String title= homePage.getHomePageTitle();
    		//log.info("verifying page title");
    		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
    }
    
	@Test (priority=2)
	public void verifyHeader() {
		String header= homePage.getHomeHeader();
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	@Test (priority=3, enabled=false)
	public void verifyAppStore(){
		String appstore=homePage.goAppStore();
		Assert.assertEquals(appstore, Constants.APPSTORE_TITLE);
	}
	@Test (priority=4)
	public void verifyLanguage() throws InterruptedException{
		Assert.assertTrue(homePage.selectLanguage());
		Assert.assertEquals(homePage.getTurkisPageTitle(), Constants.TURKISHPAGE_TITLE);
	}
	@Test (priority=5)
	public void verifyChatbox(){
		Assert.assertTrue(homePage.clickChatbox());		
	}
	
	@Test (priority=6)
	public void verifyPackage(){
		String price= homePage.selectPackage("60", "4 weeks", "3 Classes", "TOEFL");
		Assert.assertEquals(price, prop.getProperty("price"));
		Assert.assertEquals(homePage.getConfirmText(), Constants.CONFIRM_TEXT);
	}
	

	@Test (priority=7)
	public void verifyBuyButton(){
		Assert.assertTrue(homePage.clickBuyButton());	
	}
	 
	@Test (priority=8)
	public void doSignUp() {
		homePage.signUp();
		homePage.selectDays();
		Assert.assertEquals(homePage.selectTutor(), "V., Anne" );	
	}
	
	@AfterTest
	public void tearDown(){
		basePage.quitBrowser();
	}
}
