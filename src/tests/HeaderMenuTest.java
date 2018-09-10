package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.BusinessList;
import pages.ClientsList;
import pages.HeaderMenu;
import pages.InvoicesList;
import pages.LoginPage;

public class HeaderMenuTest extends Browser {

	HeaderMenu headerMenu;
	
	@BeforeMethod
	public void setupDriver() {
		headerMenu = new HeaderMenu(driver);
	}
	
	/*
	 * Test priority 11-20
	 */
	@Test(priority = 11)
	public void canGoToInvoicesList() {
		InvoicesList invoicesList = headerMenu.goToInvoicesList();
		
		assertTrue(invoicesList.isAt());
	}
	
	@Test(priority = 12)
	public void canGoToBusinessList() {
		BusinessList businessList = headerMenu.goToBusinessList();
		
		assertTrue(businessList.isAt());
	}
	
	@Test(priority = 13)
	public void canGoToClientsList() {
		ClientsList clientsList = headerMenu.goToClientsList();
		
		assertTrue(clientsList.isAt());
	}
	
	@Test(priority = 14)
	public void canGoToActivityLog() {
		assertTrue(headerMenu.goToActivityLog());
	}
	
	@Test(priority = 15, dataProvider="HeaderProvider", dataProviderClass=parameters.DataProviderClass.class)
	public void canLogOut(String email, String password) {
		LoginPage loginPage = headerMenu.logOut();
		
		assertTrue(loginPage.isAt());
		
		HeaderMenu headerMenu = loginPage.login(email, password);
		InvoicesList invoicesList = headerMenu.goToInvoicesList();
		invoicesList.goToAddNewInvoice();
	}
}
