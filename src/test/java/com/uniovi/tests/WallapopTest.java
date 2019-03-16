package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.entities.type.Rol;
import com.uniovi.repositories.MessageRepository;
import com.uniovi.repositories.OfferRepository;
import com.uniovi.repositories.UserRepository;
import com.uniovi.services.OffersService;
import com.uniovi.services.UserService;
import com.uniovi.tests.utils.TestUtil;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)

@SpringBootTest
public class WallapopTest {

	private TestUtil testUtil;
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	final static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	// helen
	//final static String Geckdriver022 = "C:\\Users\\ediaz\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	// astrid
	 static String Geckdriver022 = "C:\\Users\\astri\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox64, Geckdriver022);
	String URL = "http://localhost:8090";

	@Autowired
	private OffersService offerService;
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private MessageRepository messageRepository;

	private static void initDriver() {
		System.setProperty("webdriver.firefox.bin", PathFirefox64);
		System.setProperty("webdriver.gecko.driver", Geckdriver022);
		driver = new FirefoxDriver();
	}

	@Before
	public void setUp() {

		initdb();
		testUtil = new TestUtil(driver);
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	// Cerramos el navegador al finalizar las pruebas
	@AfterClass
	static public void end() {
		driver.quit();
	}

	public void initdb() {
		messageRepository.deleteAll();
		offerRepository.deleteAll();
		userRepository.deleteAll();

		// Ahora las volvemos a crear
		User user = new User();
		user.setRol(Rol.ROLE_ADMIN);
		user.setEmail("admin@email.com");
		user.setPassword("admin");
		user.setPassword2("admin");
		user.setActive(true);

		User user1 = new User();
		user1.setRol(Rol.ROLE_USER);
		user1.setEmail("user1@email.com");
		user1.setPassword("user1");
		user1.setPassword2("user1");
		user1.setName("Juan");
		user1.setSurname("Diaz");
		user1.setActive(true);

		User user2 = new User();
		user2.setRol(Rol.ROLE_USER);
		user2.setEmail("user2@email.com");
		user2.setPassword("user2");
		user2.setPassword2("user2");
		user2.setName("Lucia");
		user2.setSurname("Fernandez");
		user2.setActive(true);

		User user3 = new User();
		user3.setRol(Rol.ROLE_USER);
		user3.setEmail("user3@email.com");
		user3.setPassword("user3");
		user3.setPassword2("user3");
		user3.setName("Alba");
		user3.setSurname("García");
		user3.setActive(true);

		User user4 = new User();
		user4.setRol(Rol.ROLE_USER);
		user4.setEmail("user4@email.com");
		user4.setPassword("user4");
		user4.setPassword2("user4");
		user4.setName("Cuatroneno");
		user4.setSurname("Fouracio");
		user4.setActive(true);

		User user5 = new User();
		user5.setRol(Rol.ROLE_USER);
		user5.setEmail("user5@email.com");
		user5.setPassword("user5");
		user5.setPassword2("user5");
		user5.setName("Cincuento");
		user5.setSurname("Fivement");
		user5.setActive(true);

		userService.addUser(user);
		userService.addUser(user1);
		userService.addUser(user2);
		userService.addUser(user3);
		userService.addUser(user4);
		userService.addUser(user5);

		List<Offer> user1Offers = new ArrayList<Offer>();
		for (int i = 1; i < 4; i++)
			user1Offers.add(new Offer("Offer 1", "seminuevo", i * 1.0, user1));

		offerService.saveAll(user1Offers);

		List<Offer> user2Offers = new ArrayList<Offer>();
		for (int i = 1; i < 4; i++)
			user2Offers.add(new Offer("Offer 2", "muy bueno", i * 1.0, user2));
		offerService.saveAll(user2Offers);

		List<Offer> user3Offers = new ArrayList<Offer>();
		for (int i = 1; i < 6; i++)
			user3Offers.add(new Offer("Offer 3", "seminuevo", i * 1.0, user3));
		offerService.saveAll(user1Offers);

		List<Offer> user4Offers = new ArrayList<Offer>();
		for (int i = 1; i < 5; i++)
			user4Offers.add(
					new Offer("Offer 4", "muy buena calidad", i * 1.0, user4));
		offerService.saveAll(user4Offers);

		List<Offer> user5Offers = new ArrayList<Offer>();
		for (int i = 1; i < 4; i++)
			user5Offers.add(new Offer("Offer 5", "excelente", i * 1.0, user5));
		offerService.saveAll(user5Offers);

	}

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Prueba1. Registro de Usuario con datos válidos /
	@Test
	public void Prueba01() {
		initdb();
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.linkText("Registrate")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Registrate", true);
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("aaaa@uniovi.es");
		driver.findElement(By.name("name")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Julio");
		driver.findElement(By.name("surname")).click();
		driver.findElement(By.name("surname")).clear();
		driver.findElement(By.name("surname")).sendKeys("Fernandez");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("password2")).click();
		driver.findElement(By.name("password2")).clear();
		driver.findElement(By.name("password2")).sendKeys("123456");
		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Registrate", false);
	}

	// Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos)
	@Test
	public void Prueba02() {
		driver.get("http://localhost:8090/signup?lang=ES");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("password2")).click();
		driver.findElement(By.name("password2")).clear();
		driver.findElement(By.name("password2")).sendKeys("123456");
		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Registrate", true);
		testUtil.searchText("Desconectar", false);
	}

	// Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida)
	@Test
	public void Prueba03() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("signup")).click();
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("aaa@uniovi.es");
		driver.findElement(By.name("name")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("anabel");
		driver.findElement(By.name("surname")).click();
		driver.findElement(By.name("surname")).clear();
		driver.findElement(By.name("surname")).sendKeys("gonzalez");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("65746356424");
		driver.findElement(By.name("password2")).click();
		driver.findElement(By.name("password2")).clear();
		driver.findElement(By.name("password2")).sendKeys("472665653427547");
		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Registrate", true);
		testUtil.searchText("Desconectar", false);
	}

	// Registro de Usuario con datos inválidos (email existente).
	@Test
	public void Prueba04() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("signup")).click();
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("user1@email.com");
		driver.findElement(By.name("name")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("anabel");
		driver.findElement(By.name("surname")).click();
		driver.findElement(By.name("surname")).clear();
		driver.findElement(By.name("surname")).sendKeys("gonzalez");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("65746356424");
		driver.findElement(By.name("password2")).click();
		driver.findElement(By.name("password2")).clear();
		driver.findElement(By.name("password2")).sendKeys("65746356424");
		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Registrate", true);
		testUtil.searchText("Desconectar", false);

	}
	// 2

	// Inicio de sesión con datos válidos (administrador).
	@Test
	public void Prueba05() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("admin@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");

		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("ID", false);
		testUtil.searchText("Desconectar", false);

	}

	// Inicio de sesión con datos válidos (usuario ).
	@Test
	public void Prueba06() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");

		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("ID", false);
		testUtil.searchText("Desconectar", false);
	}

	// Inicio de sesión con datos inválidos (usuario estándar, campo email y
	// contraseña vacíos).
	@Test
	public void Prueba07() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(" ");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(" ");

		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("ID", true);
		testUtil.searchText("Desconectar", false);

	}

	// Inicio de sesión con datos válidos (usuario estándar, email existente,
	// pero contraseñ incorrecta).
	@Test
	public void Prueba08() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("1234");

		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("ID", true);
		testUtil.searchText("Desconectar", false);

	}

	// Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación).
	@Test
	public void Prueba09() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("userxdxd@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");

		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("ID", true);
		testUtil.searchText("Desconectar", false);
	}

	// Hacer click en la opción de salir de sesión y comprobar que se redirige a
	// la página de inicio de sesión (Login).
	@Test
	public void Prueba10() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");
		driver.findElement(By.id("send")).click();
		driver.findElement(By.id("logout")).click();
		testUtil.searchText("ID", true);
		testUtil.searchText("Desconectar", false);

	}

	// Comprobar que el botón cerrar sesión no está visible si el usuario no
	// está autenticado.
	@Test
	public void Prueba11() {
		testUtil.searchText("ID", false);
		testUtil.searchText("Desconectar", false);
	}
	// Mostrar el listado de usuarios y comprobar que se muestran todos los que
	// existen en el

	@Test
	public void Prueba12() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("admin@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.id("send")).click();
		driver.findElement(By.id("usermanage")).click();
		driver.findElement(By.id("usersee")).click();
		testUtil.searchText("user1", true);
		testUtil.searchText("user2", true);
		testUtil.searchText("user3", true);
	}

	// Admin.Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualizay dicho usuario desaparece.
	@Test
	public void Prueba13() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("admin@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		driver.findElement(By.id("usermanage")).click();
		driver.findElement(By.id("usersee")).click();
		testUtil.waitChangeWeb();
		driver.findElement(By.id("usermanage")).click();
		driver.findElement(By.id("usersee")).click();
		driver.findElements(By.className("check")).get(0).click();
		driver.findElement(By.id("DeleteButton")).click();
		testUtil.searchText("Usuarios", true);
		testUtil.searchText("user1@email.com", false);

	}

	// Admin.Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualizay dicho usuario desaparece.
	@Test
	public void Prueba14() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("admin@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.id("send")).click();
		driver.findElement(By.id("usermanage")).click();
		driver.findElement(By.id("usersee")).click();
		List<WebElement> usuarios = driver.findElements(By.className("check"));
		usuarios.get(usuarios.size() - 1).click();
		driver.findElement(By.id("DeleteButton")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Usuarios", true);
		testUtil.searchText("user5@email.com", false);

	}

	// Admin.Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la
	// lista se actualiza y dichosusuarios desaparecen.
	@Test
	public void Prueba15() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("admin@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.id("send")).click();
		driver.findElement(By.id("usermanage")).click();
		driver.findElement(By.id("usersee")).click();
		driver.findElements(By.className("check")).get(0).click();
		driver.findElements(By.className("check")).get(1).click();
		driver.findElements(By.className("check")).get(2).click();
		driver.findElement(By.id("DeleteButton")).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Usuarios", true);
		testUtil.searchText("user1@email.com", false);
		testUtil.searchText("user2@email.com", false);
		testUtil.searchText("user3@email.com", false);

	}

	// Ir al formulario de alta de oferta, rellenarla con datos válidos y pulsar
	// el botón Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario
	@Test
	public void Prueba16() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");
		driver.findElement(By.id("send")).click();

		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offeradd")).click();

		driver.findElement(By.name("title")).click();
		driver.findElement(By.name("title")).clear();
		driver.findElement(By.name("title")).sendKeys("Diamantes");
		driver.findElement(By.name("description")).click();
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("25 Karats");
		driver.findElement(By.name("price")).click();
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys("25");

		driver.findElement(By.id("add")).click();

		testUtil.searchText("Disponibles", true);
		testUtil.searchText("Agregar", false);
	}

	// Ir al formulario de alta de oferta, rellenarla con datos inválidos (campo
	// título vacío) y pulsarel botón Submit. Comprobar que se muestra el
	// mensaje de campo obligatorio.
	@Test
	public void Prueba17() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");
		driver.findElement(By.id("send")).click();

		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offeradd")).click();

		driver.findElement(By.name("title")).click();
		driver.findElement(By.name("title")).clear();
		driver.findElement(By.name("title")).sendKeys(" ");
		driver.findElement(By.name("description")).click();
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(" ");
		driver.findElement(By.name("price")).click();
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys(" ");
		driver.findElement(By.id("add")).click();

		testUtil.searchText("Añadir", true);

	}

	@Test
	public void Prueba18() {
		// login user
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");
		driver.findElement(By.id("send")).click();
		// adding offer
		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offeradd")).click();
		driver.findElement(By.name("title")).click();
		driver.findElement(By.name("title")).clear();
		driver.findElement(By.name("title")).sendKeys("Purpurina");
		driver.findElement(By.name("description")).click();
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("Kunfu MEtal");
		driver.findElement(By.name("price")).click();
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys("2009");
		driver.findElement(By.id("add")).click();

		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offerselling")).click();

		testUtil.searchText("Purpurina", true);
	}

	// User. Dar de baja una oferta
	// Ir a la lista de ofertas, borrar la primera oferta de la lista, comprobar
	// que la lista se actualiza y que la oferta desaparece.
	@Test
	public void Prueba19() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user2@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user2");
		driver.findElement(By.id("send")).click();
		testUtil.waitChangeWeb();
		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offerselling")).click();
		testUtil.waitChangeWeb();
		List<WebElement> elements = driver
				.findElements(By.className("eliminar"));
		int size = elements.size();
		driver.findElements(By.className("eliminar")).get(0).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Ofertas", true);
		elements = driver.findElements(By.className("eliminar"));
		assertTrue(size == elements.size() + 1);

	}

	// User.Dar de baja una oferta
	// Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar
	// que la lista se actualiza y que la oferta desaparece.
	@Test
	public void Prueba20() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user2@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user2");
		driver.findElement(By.id("send")).click();
		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offerselling")).click();
		List<WebElement> elements = driver
				.findElements(By.className("eliminar"));
		int size = elements.size();
		driver.findElements(By.className("eliminar")).get(size - 1).click();
		testUtil.waitChangeWeb();
		testUtil.searchText("Ofertas", true);
		elements = driver.findElements(By.className("eliminar"));
		assertTrue(size == elements.size() + 1);

	}

	// Hacer una búsqueda con el campo vacío y comprobar que se muestra la
	// página que corresponde con el listado de las ofertas existentes en el
	// sistema
	@Test
	public void Prueba21() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");
		driver.findElement(By.id("send")).click();
		// adding offer
		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offersearch")).click();

		testUtil.searchText("Offer 1", true);
		testUtil.searchText("Offer 2", true);
	}

	// Hacer una búsqueda escribiendo en el campo un texto que no exista y
	// comprobar que se
	// muestra la página que corresponde, con la lista de ofertas vacía.
	@Test
	public void Prueba22() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");
		driver.findElement(By.id("send")).click();

		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offersearch")).click();

		driver.findElement(By.name("searchText")).click();
		driver.findElement(By.name("searchText")).clear();
		driver.findElement(By.name("searchText")).sendKeys("wowowo");
		driver.findElement(By.id("submit")).click();

		testUtil.searchText("Oferta 1", false);
		testUtil.searchText("Oferta 2", false);
		testUtil.searchText("Oferta 3", false);
		testUtil.searchText("Oferta 4", false);
		testUtil.searchText("Oferta 5", false);
	}

	// @Test
	public void Prueba23() {

	}

	// @Test
	public void Prueba24() {

	}

	// @Test
	public void Prueba25() {

	}

	// @Test
	public void Prueba26() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");
		driver.findElement(By.id("send")).click();

		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offerbought")).click();
		testUtil.searchText("Oferta 1", false);
		testUtil.searchText("Oferta 2", false);
		testUtil.searchText("Oferta 3", false);
		testUtil.searchText("Oferta 4", false);
		testUtil.searchText("Oferta 5", false);
	}

	@Test
	// Visualizar al menos cuatro páginas en Español/Inglés/Español (comprobando
	// que algunas
	// de las etiquetas cambian al idioma correspondiente).
	public void Prueba27() {
		driver.get("http://localhost:8090/?lang=ES");

		driver.findElement(By.id("login")).click();
		testUtil.searchText("Identíficate", true);
		driver.findElement(By.id("btnLanguage")).click();
		driver.findElement(By.id("btnEnglish")).click();
		testUtil.searchText("Identíficate", false);
		testUtil.searchText("Log", true);
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");

		driver.findElement(By.id("send")).click();

		testUtil.searchText("Bienvenidos", false);
		testUtil.searchText("Welcome", true);
		driver.findElement(By.id("btnLanguage")).click();
		driver.findElement(By.id("btnSpanish")).click();
		testUtil.searchText("Welcome", false);

		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offersearch")).click();
		testUtil.searchText("Ofertas", true);
		driver.findElement(By.id("btnLanguage")).click();
		driver.findElement(By.id("btnEnglish")).click();
		testUtil.searchText("Ofertas", false);
		testUtil.searchText("Offers", true);

		driver.findElement(By.id("offermanage")).click();
		driver.findElement(By.id("offeradd")).click();
		testUtil.searchText("Ofertas", false);
		driver.findElement(By.id("btnLanguage")).click();
		driver.findElement(By.id("btnSpanish")).click();
		testUtil.searchText("Ofertas", true);
		testUtil.searchText("Offers", false);

		testUtil.searchText("taza", false);
		testUtil.searchText("teclado", false);
	}

	// Intentar acceder sin estar autenticado a la opción de listado de usuarios
	// del administrador. Se
	@Test
	public void Prueba28() {
		driver.get("http://localhost:8090/?lang=ES");
		testUtil.searchText("Ofertas", false);
		testUtil.searchText("Ver Usuarios", false);
		testUtil.searchText("Identíficate", true);
		driver.get("http://localhost:8090/user/list");
		testUtil.searchText("Identíficate", true);

	}

	@Test
	// ] Intentar acceder sin estar autenticado a la opción de listado de
	// ofertas propias de un usuario
	// estándar. Se deberá volver al formulario de login.
	public void Prueba29() {
		driver.get("http://localhost:8090/?lang=ES");
		testUtil.searchText("Ofertas", false);
		testUtil.searchText("Ver Usuarios", false);
		testUtil.searchText("Identíficate", true);
		driver.get("http://localhost:8090/offer/selling");
		testUtil.searchText("Identíficate", true);
	}

	@Test
	// Estando autenticado como usuario estándar intentar acceder a la opción de
	// listado de
	// usuarios del administrador. Se deberá indicar un mensaje de acción
	// prohibida.
	public void Prueba30() {
		driver.get("http://localhost:8090/?lang=ES");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("user1@email.com");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("user1");
		driver.findElement(By.id("send")).click();
		driver.get("http://localhost:8090/user/list");
		testUtil.searchText("Forbidden", true);

	}

}
