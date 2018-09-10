package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AddNewInvoice;

public class AddNewInvoiceTest extends Browser {

	AddNewInvoice addNewInvoice;
	
	@BeforeMethod
	public void setUp() {
		addNewInvoice = new AddNewInvoice(driver);
	}
	
	/*
	 * Test priority 21-30
	 */
	
	@Test(priority = 21, dataProvider="NewInvoiceProvider", dataProviderClass=parameters.DataProviderClass.class)
	public void canAddInvoice(String title, String basis, String service, String note, String hours, String rate) throws InterruptedException {
		addNewInvoice.addNewInvoice(title, basis, service, note, hours, rate);
	}
}
