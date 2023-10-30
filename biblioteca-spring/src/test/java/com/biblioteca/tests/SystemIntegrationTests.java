package com.biblioteca.tests;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.biblioteca.BibliotecaSpringApplication;
import com.biblioteca.repositories.UserRepository;

import junit.framework.Assert;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BibliotecaSpringApplication.class)
public class SystemIntegrationTests {

	@Autowired
	private UserRepository userRepository;

	static WebDriver driver = new EdgeDriver();

	// Antes de cada prueba
	@BeforeAll
	static public void setUp() {
		// Reseteamos la base de datos
		initDb();

		// Navegamos a la URL /home de la aplicacion
		driver.navigate().to("http://localhost:8080/home");
	}

	/**
	 * Método destinado a establecer todos los usuarios de la base de datos para la
	 * consecuente ejecución de las pruebas
	 */
	private static void initDb() {

//		userRepository.deleteAll();
	}

	@AfterEach
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Al finalizar la última prueba
	@AfterAll
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	@Test
	public void testEdgeDriver() {
		// Comprobamos que entramos en la página de bienvenida
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Acceder')]"));
		Assert.assertTrue("Text not found!", list.size() > 0);
	}
}
