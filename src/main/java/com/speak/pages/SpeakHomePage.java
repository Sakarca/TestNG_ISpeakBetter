package com.speak.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.speak.base.BasePage;
import com.speak.util.Constants;
import com.speak.util.ElementUtil;
import com.speak.util.JavaScriptUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SpeakHomePage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	Properties prop;
	
	
	By header= By.xpath("//h1[@class='rsp']");
	By googlePlay = By.xpath("//img[@src='img/google-play.png']");
	By appStore = By.xpath("//img[@src='img/apple.png']");
	By language = By.id("dropdownMenuLink");
	By langs = By.className("dropdown-item");
	By turkish = By.xpath("//a[@class='dropdown-item'][2]");
	By chatOpen= By.id("zsiq_agtpic");
	By chatFrame= By.id("siqiframe");
	By chatClose= By.xpath("//div[@documentclick='min_iframe']");
	By duration= By.id("class_duration");
	By weeks= By.id("package_length"); 
	By classes= By.id("per_week_class_number");
	By program= By.id("course_program"); 
	By price= By.id("result-price");
	By confirmtext= By.id("lesson_number_remark");
	By buyBtn= By.id("cmdPurchase");
	By signUpBtn= By.id("signup");
	By firstName= By.id("fname");
	By lastName= By.id("lname");
	By email= By.id("email");		
	By pswd= By.id("password");
	By confPswd= By.id("confirmPass");
	By tzone= By.id("timezone");
	By terms= By.id("check_term");
	By next= By.id("cmdSignupNext");
	By day1= By.xpath("//input[@value='mo']");
	By day2= By.xpath("//input[@value='we']");
	By day3= By.xpath("//input[@value='fr']");
	By submit= By.id("btn-submit-0");
	By tutor=By.xpath("//div[@class='owl-stage']//div[1]//div[1]//div[1]//img[1]");
	By tutorName=By.xpath("//h1");
	
	public SpeakHomePage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		prop= initialize_properties();
	}
	
	public String getHomePageTitle(){
		return elementUtil.waitForGetPageTitle(Constants.HOME_PAGE_TITLE);
	}
	
	public String getHomeHeader() {
		elementUtil.waitForElementPresentBy(header);
		return elementUtil.doGetText(header);
	}
	public String goGooglePlay() {
		elementUtil.doClick(googlePlay);
		return elementUtil.waitForGetPageTitle(Constants.GOOGLEPLAY_TITLE);
	}
	public String goAppStore() {
		elementUtil.doClick(appStore);
		Set<String> window = driver.getWindowHandles();
        Iterator<String> it = window.iterator();
        String homePage = it.next();
        driver.switchTo().window(it.next());
        String title = elementUtil.waitForGetPageTitle(Constants.APPSTORE_TITLE);
        driver.close();
        driver.switchTo().window(homePage);
        System.out.println(title);
        return title;
	}
	
	public boolean selectLanguage()  {
		elementUtil.waitForElementPresentBy(language);
		elementUtil.doClick(language);
		List<WebElement> languages= driver.findElements(langs);
		List<String> langNames = new ArrayList<String>();
		
		for(WebElement e:languages){
		String lname= e.getText();
		   langNames.add(lname);
		}
		elementUtil.waitForElementPresentBy(turkish);
		elementUtil.doClick(turkish);
		return langNames.contains("Turkish");  //returns true if Turkish is in the list
	}
	
	public String getTurkisPageTitle() throws InterruptedException{
		String title=elementUtil.waitForGetPageTitle(Constants.TURKISHPAGE_TITLE);
		driver.navigate().back();
		//Thread.sleep(3000);
		return title;
	}
	
	public boolean clickChatbox() {
		elementUtil.waitForElementPresentBy(chatOpen);
		boolean b=elementUtil.getElement(chatOpen).isDisplayed();
		elementUtil.doClick(chatOpen); 
	 	WebDriverWait wait= new WebDriverWait(driver, 10);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(chatFrame));
	 	driver.switchTo().frame(driver.findElement(chatFrame));
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(chatClose));
	 	driver.findElement(chatClose).click(); 
	 	driver.switchTo().defaultContent();
		return b;
	}
	
	public String selectPackage(String min, String week, String classNo, String prog){
		JavaScriptUtil.scrollByPixel(driver, "0", "1800");
		elementUtil.selectOption(duration, min);
		elementUtil.selectOption(weeks, week);
		elementUtil.selectOption(classes, classNo);
		elementUtil.selectOption(program, prog);
		
		return elementUtil.doGetText(price);
	}
	
	public String getConfirmText(){
		return elementUtil.doGetText(confirmtext);
	}
	
	public boolean clickBuyButton(){
		boolean b= elementUtil.getElement(buyBtn).isEnabled();
		elementUtil.doClick(buyBtn);
		return b;
	}
	
	public void signUp(){
		elementUtil.doClick(signUpBtn);
		elementUtil.doSendKeys(firstName, prop.getProperty("fname"));
		elementUtil.doSendKeys(lastName, prop.getProperty("lname"));
		elementUtil.doSendKeys(email, prop.getProperty("email"));
		elementUtil.doSendKeys(pswd, prop.getProperty("password"));
		elementUtil.doSendKeys(confPswd, prop.getProperty("password"));
		elementUtil.selectOption(tzone, prop.getProperty("timezone"));
		elementUtil.doClick(terms);
		elementUtil.doClick(next);	
	}
	
	public void selectDays(){
		elementUtil.waitForElementPresentBy(day1);
		elementUtil.doClick(day1);
		elementUtil.doClick(day2);
		elementUtil.doClick(day3);
		elementUtil.doClick(submit);
	}
	
	public String selectTutor() {
		elementUtil.waitForElementPresentBy(tutor);
		elementUtil.doClick(tutor);
		elementUtil.waitForElementPresentBy(tutorName);
		String name=elementUtil.doGetText(tutorName);
		return name;
	}
	
}
