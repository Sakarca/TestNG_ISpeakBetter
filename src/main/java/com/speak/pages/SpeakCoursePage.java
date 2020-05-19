package com.speak.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.speak.base.BasePage;
import com.speak.util.ElementUtil;

public class SpeakCoursePage extends BasePage{
	

		WebDriver driver;
		ElementUtil elementUtil;
		Properties prop;
		
		
		By coursesPage= By.xpath("//span[text()='Courses']");
		By coursesHeader = By.xpath("//h1"); //Courses provided
		By courses = By.xpath("//h4[@class='text-center']");
		By enrolls= By.xpath("//h4[@class='text-center']//a");
		
		public SpeakCoursePage(WebDriver driver){
			this.driver = driver;
			elementUtil = new ElementUtil(driver);
			prop= initialize_properties();
		}
		
		public void goToCourses(){
			elementUtil.doClick(coursesPage);
		}
		
		public String getCoursesHeader(){
			elementUtil.waitForElementPresentBy(coursesHeader);
			return elementUtil.doGetText(coursesHeader);
		}
		
		public void listCourses(){
			List<WebElement> courseNames= driver.findElements(courses);
//			List<WebElement> enroll= driver.findElements(enrolls);
//			System.out.println(courseNames.removeAll(enroll));
		
			for(WebElement e:courseNames){
				String name=e.getText();
				if(!name.contains("ENROLL"))
				System.out.println(name);
			}			
		}
		
		public boolean checkEnroll(){
			boolean b=false;
			List<WebElement> enroll= driver.findElements(enrolls);
			for(WebElement e:enroll){
				if(e.isEnabled()){
					b=true;
					continue;
				}
				else{
					b= false;
					break;
				}
			}
			return b;
		}
		
		
}
