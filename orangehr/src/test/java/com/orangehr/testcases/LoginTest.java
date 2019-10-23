package com.orangehr.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehr.base.TestBase;

public class LoginTest extends TestBase {
	
	@Test
	public void loginAsAdminUser() throws InterruptedException {
		
		log.debug("Inside Login Test");
	//	driver.findElement(By.id(OR.getProperty("loginBtn")));
		Assert.assertTrue(isElementPrasent(By.id(OR.getProperty("loginpagevalidation"))),"Validation Error");
		log.debug("Login Successfully Executed");
	}

}
