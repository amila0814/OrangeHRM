package com.orangehr.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehr.base.TestBase;
import com.orangehr.utilities.ExcelUtils;



public class LoginUsingAdminUserTest extends TestBase {
	
	@Test(dataProvider="getData")
	public void LoginUsingAdminUser(String username, String password) {
		
		driver.findElement(By.id(OR.getProperty("loginuserid"))).sendKeys(username);
		driver.findElement(By.id(OR.getProperty("loginpassword"))).sendKeys(password);
		driver.findElement(By.id(OR.getProperty("loginBtn"))).click();
		
	}
	
	@DataProvider
	public Object [][] getData(){
		
		String excelPath = "D:/Amila/Automation/orangehr/src/test/resources/excel/testdata.xlsx";

		Object data[][] = loadData(excelPath, "LoginUsingAdminUserTest");
		return data;
		
	}
	
	public static Object[][] loadData(String excelPath, String sheetName) {

		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data[][] = new Object[rowCount-1][colCount];

		for(int i=1; i<rowCount; i++ ) {

			for (int j=0; j<colCount; j++) {

				String cellData = excel.getCellDataString(i, j);
				//	System.out.print(cellData+" | ");
				data[i-1][j] = cellData;

			}

			//	System.out.println();
		}

		return data;

	}

}
