package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HeaderMenu;
import pages.LoginPage;

public class LoginPageTest extends Browser {

	LoginPage loginPage;
	
	// Use parameter 'url' from testng.xml file
	@BeforeMethod
	@Parameters({"url"})
	public void setupDriver(String url) {
		driver.get(url);
		loginPage = new LoginPage(driver);
	}
	
	/*
	 * Test priority 0-10
	 */
	
	@Test(priority = 0)
	public void canOpenLoginPage() {
		assertTrue(loginPage.isAt());
	}

	@Test(priority = 1, dataProvider="LoginProvider", dataProviderClass=parameters.DataProviderClass.class)
	public void canLogin(String email, String password) {
		HeaderMenu headerMenu = loginPage.login(email, password);
		
		assertTrue(headerMenu.isAt());
	}
}
