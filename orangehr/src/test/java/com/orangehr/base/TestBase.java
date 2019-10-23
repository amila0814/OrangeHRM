package com.orangehr.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = LogManager.getLogger(TestBase.class);


	@BeforeSuite
	public void setUp() {

		String projectPath = System.getProperty("user.dir");

		if(driver == null) {

			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file loaded !!!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("firefox") ) {

				System.setProperty("webdriver.gecko.driver", projectPath + "/src/test/resources/executables/geckodriver/geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox Launched !!!!!");

			} else if (config.getProperty("browser").equals("chrome") ) {

				System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/executables/chromedriver/chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!!!");

			} 

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : "+ config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);

		}
	}
	
	
	public boolean isElementPrasent(By by) {
		
		try {
			
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e) {
			
			return false;
			
		}
		
		
	}



	@AfterSuite
	public void tearDown() {
		
		if(driver != null) {
			driver.quit();
		}
		
		log.debug("Test Execution Completed");

	}



}
