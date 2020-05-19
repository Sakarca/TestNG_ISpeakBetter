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
import com.speak.pages.SpeakCoursePage;
import com.speak.pages.SpeakHomePage;
import com.speak.util.Constants;
import com.speak.util.ElementUtil;

@Listeners(com.speak.listeners.TestAllureListener.class)
public class SpeakCourseTest {
	//Logger log = Logger.getLogger("HotelsMainTest");
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	SpeakHomePage homePage;
	SpeakCoursePage coursePage;
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		//homePage= new SpeakHomePage(driver);
		coursePage= new SpeakCoursePage(driver);
	}
    @Test(priority=1, description="Verifies Page Header")
    public void verifyCoursePageHeader(){
    		coursePage.goToCourses();
    		String header= coursePage.getCoursesHeader();
    		Assert.assertEquals(header, Constants.COURSES_PAGE_HEADER);
    }
    
	@Test (priority=2)
	public void listCourses() {
		coursePage.listCourses();	
	}
	
	
	@Test (priority=3, enabled=true)
	public void verifyEnroll(){	
		Assert.assertTrue(coursePage.checkEnroll());
	}
	
	
	@AfterTest
	public void tearDown(){
		basePage.quitBrowser();
	}
}