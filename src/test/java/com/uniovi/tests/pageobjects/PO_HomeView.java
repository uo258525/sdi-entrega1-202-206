package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.utils.SeleniumUtils;

public class PO_HomeView extends PO_View{
	static public void checkWelcome(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}
	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String
			textIdiom2, int locale1, int locale2 ) {
			//Esperamos a que se cargue el saludo de bienvenida en Español
			PO_HomeView.checkWelcome(driver, locale1);
			//Cambiamos a segundo idioma
			PO_NavView.changeIdiom(driver, textIdiom2);
			//COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
			PO_HomeView.checkWelcome(driver, locale2);
			//Volvemos a Español.
			PO_NavView.changeIdiom(driver, textIdiom1);
			//Esperamos a que se cargue el saludo de bienvenida en Español
			PO_HomeView.checkWelcome(driver, locale1);
			}
	public static void clickOption(WebDriver driver, String textOption, String criterio,
			String textoDestino) {
			//CLickamos en la opción de registro y esperamos a que se cargue el enlace de
			
			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href",
			textOption, getTimeout());
			//Tiene que haber un sólo elemento.
			assertTrue(elementos.size()==1);
			//Ahora lo clickamos
			elementos.get(0).click();
			//Esperamos a que sea visible un elemento concreto
			elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino,
			getTimeout());
			//Tiene que haber un sólo elemento.
			assertTrue(elementos.size()==1);
			}
}
