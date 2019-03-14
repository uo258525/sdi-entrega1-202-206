package com.uniovi.tests;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import com.uniovi.services.RolesService;
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
	final static String Geckdriver022 = "C:\\Users\\ediaz\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	// astrid
	// static String Geckdriver022 =
	// "C:\\Users\\astri\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox64, Geckdriver022);
	String URL = "http://localhost:8090";

	@Autowired
	private UserService usersService;
	@Autowired
	private RolesService rolesService;

	@Autowired
	private UserRepository usersRepository;

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

		// initdb();
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
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!1");
		// Borramos todas las entidades.
		messageRepository.deleteAll();
		offerRepository.deleteAll();
		usersRepository.deleteAll();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!2");
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

		Set<Offer> user1Offers = new HashSet<Offer>();
		for (int i = 1; i < 5; i++)
			user1Offers.add(new Offer("Offer 1", "bueno borito barato", i * 1.0,
					user1));
		user1.setAnnouncedOffers(user1Offers);

		Set<Offer> user2Offers = new HashSet<Offer>();
		for (int i = 1; i < 5; i++)
			user2Offers.add(new Offer("Offer 2", "bueno borito barato", i * 1.0,
					user2));
		user2.setAnnouncedOffers(user2Offers);
		Set<Offer> user3Offers = new HashSet<Offer>();
		for (int i = 1; i < 8; i++)
			user3Offers.add(new Offer("Offer 3", "bueno borito barato", i * 1.0,
					user3));
		user3.setAnnouncedOffers(user3Offers);
		Set<Offer> user4Offers = new HashSet<Offer>();
		for (int i = 1; i < 15; i++)
			user4Offers.add(new Offer("Offer 4", "bueno borito barato", i * 1.0,
					user4));

		user4.setAnnouncedOffers(user4Offers);

		Set<Offer> user5Offers = new HashSet<Offer>();
		for (int i = 1; i < 15; i++)
			user5Offers.add(new Offer("Offer 5", "bueno borito barato", i * 1.0,
					user5));
		user5.setAnnouncedOffers(user4Offers);

		usersService.addUser(user);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);

	}

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Prueba1. Registro de Usuario con datos válidos /
	@Test
	public void Prueba1() {
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
	public void Prueba2() {
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
	public void Prueba3() {
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
	
	//Registro de Usuario con datos inválidos (email existente).
	@Test
	public void Prueba4() {
		
	}

}
