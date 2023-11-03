package com.biblioteca.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biblioteca.entities.User;
import com.biblioteca.repositories.AutorRepository;
import com.biblioteca.repositories.CopiaRepository;
import com.biblioteca.repositories.LectorRepository;
import com.biblioteca.repositories.LibroRepository;
import com.biblioteca.repositories.MultaRepository;
import com.biblioteca.repositories.PrestamoRepository;
import com.biblioteca.repositories.UserRepository;
import com.biblioteca.services.LectorService;
import com.biblioteca.services.UserService;

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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LectorService lectorService;
	
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
		User user1 = new User();
		user1.setEmail("admin1@gmail.com");
		user1.setPassword("12345");
		user1.setRole("ADMIN");
		userService.agregar(user1);
		
		User user2 = new User();
		user2.setEmail("admin2@gmail.com");
		user2.setPassword("12345");
		user2.setRole("ADMIN");
		userService.agregar(user2);
		
		User user3 = new User();
		user3.setEmail("admin3@gmail.com");
		user3.setPassword("12345");
		user3.setRole("ADMIN");
		userService.agregar(user3);

		// Creamos los elementos de los tests para la base de datos:
		// TODO hacerlo
	}
	
	/**
	 * Probamos que el metodo agregar agrega a un usuario y un lector con el mismo ID.
	 */
	@Test
	public void testAgregar() {
		User newUser = new User();
		newUser.setEmail("newUser@gmail.com");
		newUser.setPassword("12345");
		newUser.setRole("ADMIN");
		
		User userAdded = userService.agregar(newUser);
		assertEquals(newUser.getEmail(), userAdded.getEmail());
		assertEquals(newUser.getRole(), userAdded.getRole());
	}
	
	/**
	 * Probamos que el metodo agregar agrega a un usuario y un lector con el mismo ID.
	 */
	@Test
	public void testEliminar() {
		User newUser = new User();
		newUser.setEmail("newUserToDelete@gmail.com");
		newUser.setPassword("12345");
		newUser.setRole("ADMIN");
		userService.agregar(newUser);
		
		long id = newUser.getId();
		
		userService.delete(newUser.getId());
		
		assertNull(userService.listarId(id));
	}
	
	
}
