package tests;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.InvoicesList;

public class InvoicesListTest extends Browser {

	InvoicesList invoicesList;
	
	@BeforeMethod
	public void setUp() {
		invoicesList = new InvoicesList(driver);
	}
	
	/*
	 * Test range 31-40
	 */
	
	@Test(priority = 31)
	public void canEditInvoice() {
		invoicesList.editInvoice();
		assertTrue(invoicesList.isAt());
	}
	
	@Test(priority = 32)
	public void canPreviewInvoice() {
		invoicesList.previewInvoice();
		
		assertTrue(invoicesList.getPreviewTitle().isDisplayed());
		
		invoicesList.closePreview();
	}
	
	// Testing delete. Comparing first invoice in list before and after deleting.
	@Test(priority = 33)
	public void canDeleteInvoice() {
		String prevInvNum = invoicesList.getInvoiceNumber(0);
		invoicesList.deleteInvoice();
		String nextInvNum = invoicesList.getInvoiceNumber(0);
		
		assertNotEquals(prevInvNum, nextInvNum);
	}
	
	@Test(priority = 34)
	public void canFilterClients() {
		assertTrue(invoicesList.isOnlyClientInList());
	}
	
	// Testing if dates in list are in the given range
	@Test(priority = 35)
	public void canFilterDateRange() throws InterruptedException {
		invoicesList.enterFromDate("2018-09-04");
		invoicesList.enterToDate("2018-09-07");
		assertTrue(invoicesList.isDateInList(invoicesList.getFromDate(), invoicesList.getToDate()));
	}
	
	@Test(priority = 36)
	public void canFilterThisMonth() throws InterruptedException {
		assertTrue(invoicesList.filterThisMonth());
	}
	
	@Test(priority = 37)
	public void canFilterLastMonth() throws InterruptedException {
		assertTrue(invoicesList.filterLastMonth());
	}
	
	@Test(priority = 38)
	public void canFilterReset() throws InterruptedException {
		assertTrue(invoicesList.resetDates());
	}
}
