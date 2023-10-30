package com.biblioteca.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.biblioteca.BibliotecaSpringApplication;
import com.biblioteca.repositories.AutorRepository;
import com.biblioteca.repositories.CopiaRepository;
import com.biblioteca.repositories.LectorRepository;
import com.biblioteca.repositories.LibroRepository;
import com.biblioteca.repositories.MultaRepository;
import com.biblioteca.repositories.PrestamoRepository;
import com.biblioteca.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BibliotecaSpringApplication.class)
public class UnitTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LectorRepository lectorRepository;
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private CopiaRepository copiaRepository;
	
	@Autowired
	private MultaRepository multaRepository;
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		initDb();
	}

	/**
	 * Método destinado a establecer todos los usuarios de la base de datos para la
	 * consecuente ejecución de las pruebas
	 */
	private void initDb() {
		// Borramos todo lo utilizado para las pruebas
		multaRepository.deleteAll();
		prestamoRepository.deleteAll();
//		lectorRepository.deleteAll();
//		copiaRepository.deleteAll();
//		libroRepository.deleteAll();
//		autorRepository.deleteAll();
//		userRepository.deleteAll();

		// Creamos los elementos de los tests para la base de datos:
		// TODO hacerlo
	}
	
	@Test
	public void test1() {
		fail();
	}
}
