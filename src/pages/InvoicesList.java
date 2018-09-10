package pages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page_elements.DatePicker;

public class InvoicesList extends PageObject {
	
	@FindBy(id = "add-new-invoice")
	private WebElement addNewInvoiceBtn;
	
	// Activator for 'client list' field
	@FindBy(css = ".client-name-select .v-menu__activator")
	private WebElement clientListAct;
	
	// Value of 'selected client' field
	@FindBy(css = ".client-name-select .v-select__selection")
	private WebElement selectedClient;
	
	// Selects all elements from 'client list'
	@FindBy(css = ".menuable__content__active .v-list__tile--link")
	private List<WebElement> selectClientList;
	
	@FindBy(xpath = "//input[@id='filter-from']//ancestor::div[@class='v-menu__activator']")
	private WebElement filterFrom;
	
	@FindBy(xpath = "//input[@id='filter-to']//ancestor::div[@class='v-menu__activator']")
	private WebElement filterTo;
	
	@FindBy(id = "filter-period")
	private WebElement filterPeriodBtn;
	
	@FindBy(id = "this-month")
	private WebElement thisMonthBtn;
	
	@FindBy(id = "last-month")
	private WebElement lastMonthBtn;
	
	@FindBy(id = "reset-dates")
	private WebElement resetDatesBtn;
	// List of invoices items
	@FindBy(className = "invoice-list-items")
	private List<WebElement> invoicesItemsList;
	
	// List of clients name
	@FindBy(css = ".invoice-list-items [id^='inv-client']")
	private List<WebElement> clientsList;
	
	// List of invoice's submitted dates
	@FindBy(css = ".invoice-list-items [id^='inv-submitted']")
	private List<WebElement> submittedList;
	
	// List of invoice's id numbers
	@FindBy(css = "[id^='inv-num-']")
	private List <WebElement> invoiceNumber;
	
	// 'Show more' button, when more than 15 items in list
	@FindBy(xpath = "//div[string()='Show More']")
	private WebElement showMoreBtn;	
	
	// Button for activating drop-down menu on invoice item in list
	@FindBy(css = ".v-menu__activator [id^='context-']")
	private List <WebElement> threeStops;
	
	@FindBy(css = ".menuable__content__active [id^='context-edit-']")
	private WebElement editInvoice;
	
	@FindBy(id = "invoice-title-content")
	private WebElement invoiceTitle;
	
	@FindBy(id = "inv-form-save-btn")
	private WebElement editBtn;
	
	@FindBy(css = ".menuable__content__active [id^='context-preview-']")
	private WebElement previewInvoice;
	
	@FindBy(xpath = "//*[text()[contains(.,'Preview Invoice')]]")
	private WebElement previewInvoiceTitle;
	
	@FindBy(xpath = "//*[text()[contains(.,'close')]]")
	private WebElement closeInvoicePreview;
	
	@FindBy(css = ".menuable__content__active [id^='context-delete-']")
	private WebElement deleteInvoice;
	
	@FindBy(css = ".v-dialog__content--active button.red--text")
	private WebElement confirmDeleteInvoice;

	public InvoicesList(WebDriver driver) {
		super(driver);
	}
	
	public boolean isAt() {
		return wait.until(ExpectedConditions.visibilityOf(addNewInvoiceBtn)).isDisplayed();
	}
	
	public AddNewInvoice goToAddNewInvoice() {
		wait.until(ExpectedConditions.visibilityOf(addNewInvoiceBtn)).click();		
		return new AddNewInvoice(driver);		
	}
	
	// Get size of invoice list. If there is 'Show more' button, click on them first.
	public int getInvoicesListSize() {
		try {
			showMoreBtn.click();
			return wait.until(ExpectedConditions.visibilityOfAllElements(threeStops)).size();
		} catch(NoSuchElementException e) {
			return wait.until(ExpectedConditions.visibilityOfAllElements(threeStops)).size();
		}
	}	
	
	// Get invoice number at given index.
	public String getInvoiceNumber(int index) {
		return invoiceNumber.get(index).getText();
	}
	
	// Go to 'Edit invoice' form, make some change, and save. 
	public InvoicesList editInvoice() {
		wait.until(ExpectedConditions.visibilityOfAllElements(threeStops)).get(0).click();
		wait.until(ExpectedConditions.visibilityOf(editInvoice)).click();
		invoiceTitle.clear();
		invoiceTitle.sendKeys("Edited Title");
		editBtn.click();
		return new InvoicesList(driver);
	}
	
	// Open 'Preview invoice'
	public void previewInvoice() {
		wait.until(ExpectedConditions.visibilityOfAllElements(threeStops)).get(0).click();
		wait.until(ExpectedConditions.visibilityOf(previewInvoice)).click();
	}
	
	public WebElement getPreviewTitle() {
		return wait.until(ExpectedConditions.visibilityOf(previewInvoiceTitle));
	}
	
	public void closePreview() {
		closeInvoicePreview.click();
	}
	
	// Delete invoice from list
	public void deleteInvoice() {
		wait.until(ExpectedConditions.visibilityOfAllElements(threeStops)).get(0).click();
		wait.until(ExpectedConditions.visibilityOf(deleteInvoice)).click();
		wait.until(ExpectedConditions.visibilityOf(confirmDeleteInvoice)).click();
	}
	
	/*
	 * Search filters on Invoices List page
	 */
	
	// Enter client. Opens client list on click, selects element with index 1 (index 0 is 'All clients') and click.
	public void enterClient() {
		clientListAct.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(selectClientList)).get(1).click();
	}
	
	// Check that selected client is the only in displayed list
	public boolean isOnlyClientInList() {
		enterClient();
		String enteredClient = wait.until(ExpectedConditions.visibilityOf(selectedClient)).getText();
		for(WebElement client : clientsList) {
			if(!(client.getText()).equals(enteredClient)) {
				return false;
			}
		}		
		return true;
	}

	public void enterFromDate(String date) throws InterruptedException {
		filterFrom.click();
		DatePicker datePicker = new DatePicker(driver);
		datePicker.setFromDate(date);
	}
	
	public void enterToDate(String date) throws InterruptedException {
		filterTo.click();
		DatePicker datePicker = new DatePicker(driver);
		datePicker.setToDate(date);
	}
	
	// Getting value from 'From' date field. Cannot used '@FindBy' to locate. 
	public String getFromDate() {
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		return js.executeScript("return document.querySelector('#filter-from')._value;").toString();
	}
	
	// Getting value from 'To' date field. Cannot used '@FindBy' to locate.
	public String getToDate() {
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		return js.executeScript("return document.querySelector('#filter-to')._value;").toString();
	}
	
	// Check that date is in list
	public boolean isDateInList(String from, String to) {
		List<LocalDate> submittedDates = new ArrayList<LocalDate>();		
		for(WebElement date : submittedList) {
		    submittedDates.add(LocalDate.parse(date.getText()));
		}		
		LocalDate fromDate = LocalDate.parse(from);
		LocalDate toDate = LocalDate.parse(to);		
		for(LocalDate tempDate : submittedDates) {
			if(tempDate.isBefore(fromDate) && tempDate.isAfter(toDate)) {
				return false;
			}
		}		
		return true;
	}
	
	// Check 'This month' selecting correct date range. 
	public boolean filterThisMonth() {
		filterPeriodBtn.click();
		wait.until(ExpectedConditions.visibilityOf(thisMonthBtn)).click();
		
		LocalDate now = LocalDate.now();		
		LocalDate firstDay = now.withDayOfMonth(1);
		LocalDate lastDay = now.withDayOfMonth(now.lengthOfMonth());
		
		LocalDate fromDate = LocalDate.parse(getFromDate());
		LocalDate toDate = LocalDate.parse(getToDate());
		
		return firstDay.isEqual(fromDate) && lastDay.isEqual(toDate);
		
	}
	
	// Check that 'Last month' selecting correct date range.
	public boolean filterLastMonth() {
		filterPeriodBtn.click();
		wait.until(ExpectedConditions.visibilityOf(lastMonthBtn)).click();
		
		// Use 'minusMonths' method for previous month 
		LocalDate lastMonth = LocalDate.now().minusMonths(1);
		LocalDate firstDay = lastMonth.withDayOfMonth(1);
		LocalDate lastDay = lastMonth.withDayOfMonth(lastMonth.lengthOfMonth());
		
		LocalDate fromDate = LocalDate.parse(getFromDate());
		LocalDate toDate = LocalDate.parse(getToDate());
		
		return firstDay.isEqual(fromDate) && lastDay.isEqual(toDate);
	}
	
	// Check that dates is reseted
	public boolean resetDates() throws InterruptedException {
		filterPeriodBtn.click();
		wait.until(ExpectedConditions.visibilityOf(resetDatesBtn)).click();
		
		try {
			// Trying to get string from null. Should not succeed if everything OK 
			getFromDate();
			return false;
		} catch(NullPointerException e) {
			// If field is null, it's OK
			return true;
		}
	}
}
