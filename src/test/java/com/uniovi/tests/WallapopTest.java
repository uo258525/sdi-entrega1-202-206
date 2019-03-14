package com.uniovi.tests;



import java.util.HashSet;
import java.util.Set;

import org.hsqldb.server.WebServer;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.entities.type.Rol;
import com.uniovi.repositories.UserRepository;
import com.uniovi.services.RolesService;
import com.uniovi.services.UserService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_Properties;

//Ordenamos las pruebas por el nombre del método
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	@RunWith(SpringRunner.class)

@SpringBootTest	
public class WallapopTest {
	
	@Autowired
	private UserService usersService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private UserRepository usersRepository;
	
	@Before
	public void setUp(){
		
	//initdb();
	
	driver.navigate().to(URL);
	}
	public void initdb() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!1");
	//Borramos todas las entidades.
	usersRepository.deleteAll();
	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!2");
	//Ahora las volvemos a crear
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
	for(int i = 1; i <5; i++)
	 user1Offers.add(new Offer("Offer 1","bueno borito barato", i*1.0, user1));
	user1.setAnnouncedOffers(user1Offers);
	
	Set<Offer> user2Offers = new HashSet<Offer>();
	for(int i = 1; i <5; i++)
	 user2Offers.add(new Offer("Offer 2","bueno borito barato", i*1.0, user2));
	user2.setAnnouncedOffers(user2Offers);
	Set<Offer> user3Offers = new HashSet<Offer>();
	for(int i = 1; i <8; i++)
	 user3Offers.add(new Offer("Offer 3","bueno borito barato", i*1.0, user3));
	user3.setAnnouncedOffers(user3Offers);
	Set<Offer> user4Offers = new HashSet<Offer>();
	for(int i = 1; i <15; i++)
	 user4Offers.add(new Offer("Offer 4","bueno borito barato", i*1.0, user4));
	
	user4.setAnnouncedOffers(user4Offers);
	
	Set<Offer> user5Offers = new HashSet<Offer>();
	for(int i = 1; i <15; i++)
	 user5Offers.add(new Offer("Offer 5","bueno borito barato", i*1.0, user5));	
	user5.setAnnouncedOffers(user4Offers);
	
	usersService.addUser(user);
	usersService.addUser(user1);
	usersService.addUser(user2);
	usersService.addUser(user3);
	usersService.addUser(user4);
	usersService.addUser(user5);
	
	}
	
	
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
		// automáticas)):
		static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		//helen
		//static String Geckdriver022 = "C:\\Users\\ediaz\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
		//astrid
		static String Geckdriver022 = "C:\\Users\\astri\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

		static WebDriver driver = getDriver(PathFirefox64, Geckdriver022);
		static String URL = "http://localhost:8090";

		public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
			System.setProperty("webdriver.firefox.bin", PathFirefox);
			System.setProperty("webdriver.gecko.driver", Geckdriver);
			WebDriver driver = new FirefoxDriver();
			return driver;
		}

		// PR01. Acceder a la página principal /
		@Test
		public void PR01() {
			initdb();
			PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
		}

		// PR02. OPción de navegación. Pinchar en el enlace Registro en la página home
		@Test
		public void PR02() {
			PO_NavView.clickOption((WebServer) driver, "signup", "class", "btn btn-primary");
		}

		// PR03. OPción de navegación. Pinchar en el enlace Identificate en la página
		// home
		@Test
		public void PR03() {
			PO_NavView.clickOption((WebServer) driver, "login", "class", "btn btn-primary");
		}

		// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta a
		// Español
		@Test
		public void PR04() {
			PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
					PO_Properties.getENGLISH());
			// SeleniumUtils.esperarSegundos(driver, 2);
		}
}
