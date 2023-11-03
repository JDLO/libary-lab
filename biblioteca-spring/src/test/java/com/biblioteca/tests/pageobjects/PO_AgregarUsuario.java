package com.biblioteca.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_AgregarUsuario {

	static public void fillFormAdmin(WebDriver driver, String usernamep, String passwordp) {
		WebElement dni = driver.findElement(By.name("email"));
		dni.click();
		dni.clear();
		dni.sendKeys(usernamep);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		// Pulsar el botón
		By boton = By.xpath("/html/body/div/form/div[4]/div/button");
		driver.findElement(boton).click();
	}

}
