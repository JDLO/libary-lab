package com.biblioteca.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverSetup {

	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\luiferna\\Documents\\msedgedriver.exe");
		
		WebDriver driver = new EdgeDriver();
		
		driver.get("https://www.google.in");
	}
	
}
