package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddNewInvoice extends PageObject {
	
	// 'Add New Invoice' button
	@FindBy(id = "add-new-invoice")
	private WebElement addNewInvoiceBtn;
	
	// 'Back' button
	@FindBy(id = "inv-form-back-btn")
	private WebElement backBtn;
	
	// 'Invoice Title' field activator (on click)
	@FindBy(id = "click-to-enter")
	private WebElement invoiceTitleAct;
	
	// 'Invoice Title' field
	@FindBy(id = "invoice-title-content")
	private WebElement invoiceTitleFld;
	
	// 'To Client' field
	@FindBy(xpath = "//strong[contains(text(),'TO')]/following-sibling::div//div[@class='v-menu__activator']")
	private WebElement toClientFld;
	
	// 'From Business' field
	@FindBy(xpath = "//strong[contains(text(),'FROM')]/following-sibling::div//div[@class='v-menu__activator']")
	private WebElement fromBusinessFld;
	
	// Select list
	@FindBy(css = ".menuable__content__active .v-list__tile__title")
	private List<WebElement> selectList;
	
	@FindBy(id = "inv-form-client-address")
	private WebElement clientAddress;
	
	@FindBy(id = "inv-form-business-address")
	private WebElement businessAddress;
	
	@FindBy(id = "inv-form-basis")
	private WebElement basisFld;
	
	// 'Currency' field activator
	@FindBy(xpath = "//strong[contains(text(),'Currency')]/parent::div/following-sibling::div//div[@class='v-menu__activator']")
	private WebElement currencyAct;
	
	// 'Currency' field
	@FindBy(xpath = "//input[@id='inv-currency']/preceding-sibling::div")
	private WebElement currencyFld;
	
	// 'Bank' field activator
	@FindBy(xpath = "//label[@for='inv-form-select-bank']/parent::div/parent::div")
	private WebElement bankAct;
	
	// 'Add Invoice Content' button
	@FindBy(id = "add-content-btn")
	private WebElement addContentBtn;
	
	// 'Invoice Service' list
	@FindBy(css = "[id^='inv-form-service-']")
	private List<WebElement> invoiceServiceList;
	
	// 'Invoice note' list
	@FindBy(css = "[id^='inv-form-note-']")
	private List<WebElement> invoiceNoteList;
	
	// 'Invoice hours' list
	@FindBy(css = "[id^='inv-form-hours-']")
	private List<WebElement> invoiceHoursList;
	
	// 'Invoice rate' list
	@FindBy(css = "[id^='inv-form-rate-']")
	private List<WebElement> invoiceRateList;
	
	@FindBy(id = "inv-form-cancel-btn")
	private WebElement cancelBtn;
	
	@FindBy(id = "inv-form-save-btn")
	private WebElement saveBtn;
	
	@FindBy(id = "inv-form-export-pdf-btn")
	private WebElement exportPdfBtn;	

	public AddNewInvoice(WebDriver driver) {
		super(driver);
	}
	
	public boolean isAt() {
		return wait.until(ExpectedConditions.visibilityOf(backBtn)).isDisplayed();
	}
	
	public void enterTitle(String title) {
		wait.until(ExpectedConditions.visibilityOf(invoiceTitleAct)).click();
		wait.until(ExpectedConditions.visibilityOf(invoiceTitleFld)).sendKeys(title);
	}
	
	public void selectClient() {
		toClientFld.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(selectList)).get(0).click();
	}
	
	public void selectBusiness() {
		fromBusinessFld.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(selectList)).get(0).click();
	}
	
	public void selectCurrency() {
		currencyAct.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(selectList)).get(0).click();
	}
	
	public void selectBank() {
		bankAct.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(selectList)).get(0).click();
	}
	
	// Add invoice content. Need to use 'Keys.BACK_SPACE' because 0 as a placeholder remains after clicking.
	public void addInvoiceContent(String service, String note, String hours, String rate) {
		addContentBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(invoiceServiceList)).get(0).sendKeys(service);
		wait.until(ExpectedConditions.visibilityOfAllElements(invoiceNoteList)).get(0).sendKeys(note);
		wait.until(ExpectedConditions.visibilityOfAllElements(invoiceHoursList)).get(0).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.visibilityOfAllElements(invoiceHoursList)).get(0).sendKeys(hours);
		wait.until(ExpectedConditions.visibilityOfAllElements(invoiceRateList)).get(0).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.visibilityOfAllElements(invoiceRateList)).get(0).sendKeys(rate);		
	}
	
	// Add new invoice. Returns InvoicesList object.
	public InvoicesList addNewInvoice(String title, String basis,String service, String note, String hours, String rate) {
		enterTitle(title);
		selectClient();
		selectBusiness();
		basisFld.sendKeys(basis);
		selectCurrency();
		selectBank();
		addInvoiceContent(service, note, hours, rate);
		saveBtn.click();
		
		return new InvoicesList(driver);
	}

}
