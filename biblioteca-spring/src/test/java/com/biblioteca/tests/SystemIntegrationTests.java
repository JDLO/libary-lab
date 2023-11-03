package com.biblioteca.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.biblioteca.repositories.UserRepository;
import com.biblioteca.tests.pageobjects.PO_AgregarUsuario;
import com.biblioteca.tests.pageobjects.PO_LoginView;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BibliotecaSpringApplication.class)
public class SystemIntegrationTests {

	@Autowired
	private UserRepository userRepository;

	static WebDriver driver;

	// Antes de cada prueba
	@BeforeAll
	static public void setUp() {
		driver = new EdgeDriver();

		// Reseteamos la base de datos
		initDb();

		// Navegamos a la URL /home de la aplicacion
		driver.navigate().to("http://localhost:8080");
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
		driver.quit();
	}

	/**
	 * Autenticación. Comprobamos que estamos en la vista de inicio de sesión.
	 */
	@Test
	public void testLoginPage() {
		// Comprobamos que entramos en la página de login
		try {
			// Etiqueta de email
			driver.findElement(By.id("username"));
			// Etiqueta de password
			driver.findElement(By.id("password"));
		} catch (NoSuchElementException e) {
			fail();
		}
	}

	/**
	 * Autenticación. Iniciar sesión con unos datos válidos.
	 */
	@Test
	public void testLoginValidUser() {
		// Iniciamos sesion
		PO_LoginView.fillForm(driver, "manolito@gmail.com", "12345");

		// Comprobamos que entramos en la página de bienvenida
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Bienvenido/a')]"));
		assertTrue("Text not found!", list.size() > 0);
	}

	/**
	 * Autenticación. Iniciar sesión con unas credenciales que no existen en el
	 * sistema.
	 */
	@Test
	public void testLoginInvalidUser() {
		// Rellenamos el formulario del login con un usuario que no está en el sistema
		PO_LoginView.fillForm(driver, "usuarioInventado", "passwordCualquiera");

		// Comprobamos que se muestra el mensaje de datos inválidos
		List<WebElement> list = driver
				.findElements(By.xpath("//*[contains(text(),'El email o la password son incorrectos')]"));
		assertTrue("Text not found!", list.size() > 0);
	}

	/**
	 * Ver usuarios. Ver los usuarios activados y desactivados del sistema
	 */
	@Test
	public void testSeeUsers() {
		// Iniciamos sesion con un administrador
		PO_LoginView.fillForm(driver, "manolito@gmail.com", "12345");

		// Hacemos clic para que aparezcan las opciones
		WebElement menuDesplegable = driver.findElement(By.xpath("//*[@id=\"user-management-menu\"]/a"));
		menuDesplegable.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Hacemos click en ver usuarios
		WebElement verUsuariosActivados = driver.findElement(By.xpath("//*[@id=\"user-management-menu\"]/ul/li[2]/a"));
		verUsuariosActivados.click();

		// Comprobamos que entramos en la página de ver usuarios
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Ver usuarios')]"));
		assertTrue("Text not found!", list.size() > 0);

		// Hacemos clic para que aparezcan las opciones
		menuDesplegable = driver.findElement(By.xpath("//*[@id=\"user-management-menu\"]/a"));
		menuDesplegable.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Hacemos click en ver usuarios desactivados
		WebElement verUsuariosDesactivados = driver
				.findElement(By.xpath("//*[@id=\"user-management-menu\"]/ul/li[3]/a"));
		verUsuariosDesactivados.click();

		// Comprobamos que entramos en la página de ver usuarios desactivados
		list = driver.findElements(By.xpath("//*[contains(text(),'Ver usuarios desactivados')]"));
		assertTrue("Text not found!", list.size() > 0);
	}

	/**
	 * Ver usuarios. Ver los usuarios activados y desactivados del sistema
	 */
	@Test
	public void testAgregarUsuario() {
		// Iniciamos sesion con un administrador
		PO_LoginView.fillForm(driver, "manolito@gmail.com", "12345");

		// Hacemos clic para que aparezcan las opciones
		WebElement menuDesplegable = driver.findElement(By.xpath("//*[@id=\"user-management-menu\"]/a"));
		menuDesplegable.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// Hacemos click en agregar usuario
		WebElement agregarUsuario = driver.findElement(By.xpath("//*[@id=\"user-management-menu\"]/ul/li[1]/a"));
		agregarUsuario.click();

		// Rellenamos el formulario
		PO_AgregarUsuario.fillFormAdmin(driver, "nuevoadmin@gmail.com", "12345");

		// Comprobamos que nos redirecciona a la página de ver usuarios
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Ver usuarios')]"));
		assertTrue("Text not found!", list.size() > 0);

		// Comprobamos que en la lista aparece el nuevo usuario creado
		list = driver.findElements(By.xpath("//*[contains(text(),'nuevoadmin@gmail.com')]"));
		assertTrue("Text not found!", list.size() > 0);
	}

}
