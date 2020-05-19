package com.speak.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.speak.base.BasePage;
import com.speak.util.ElementUtil;

public class SpeakContactPage extends BasePage{
	WebDriver driver;
	ElementUtil elementUtil;
	Properties prop;
	
	By contactPage= By.xpath("//span[text()='Contact']");
	By phone = By.className("icon-phone"); 
	By map = By.className("icon-map-pin");
	By mail = By.className("icon-envelope");
	By name = By.xpath("//input[@name='name']");
	By email = By.xpath("//input[@name='email']");
	By subject= By.xpath("//select[@name='subject']");// Others
	By message= By.name("message");
	By contactUs= By.xpath("//input[@value='Contact us']");
	By alert= By.xpath("//div[@class='alert alert-info']");//Thanks for contacting us!
	public SpeakContactPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		prop= initialize_properties();
	}
	
	public void goToContactPage(){
		elementUtil.doClick(contactPage);
	}
	public boolean checkElementClickable(By locator){
		elementUtil.waitForElementPresentBy(locator);
		return driver.findElement(locator).isEnabled();
	}
	
	public String sendMessage(){
		elementUtil.doSendKeys(name, prop.getProperty("fname"));
		elementUtil.doSendKeys(email, prop.getProperty("email"));
		elementUtil.selectOption(subject, "Others");
		elementUtil.doSendKeys(message, "Project is done!");
		elementUtil.doClick(contactUs);
		String alertMessage= elementUtil.doGetText(alert);
		System.out.println(alertMessage);
		return alertMessage;
	}

	
}
