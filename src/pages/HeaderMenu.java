package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderMenu extends PageObject {	
	
	// Menu link to 'Invoices List'
	@FindBy(id = "nav-bar-invoices")
	private WebElement invoicesMenuItem;
	
	// Menu link to 'Business List'
	@FindBy(id = "nav-bar-business")
	private WebElement businessMenuItem;
	
	// Menu link to 'Clients List'
	@FindBy(id = "nav-bar-clients")
	private WebElement clientsMenuItem;
	
	// User name label for logged in users
	@FindBy(id = "home-page-user")
	private WebElement userName;
	
	// Drop-down list with 'Activity Log', and 'Logout' items
	@FindBy(id = "navbar-logout")
	private WebElement expandMoreMenuItem;
	
	// Menu link to 'Activity Log'
	@FindBy(id = "navbar-activities")
	private WebElement activityLogMenuItem;
	
	// 'Logout' button
	@FindBy(id = "navbar-logout-btn")
	private WebElement logoutMenuItem;
	
	// Confirm logout button on modal
	@FindBy(id = "logut-yes")
	private WebElement confirmLogoutBtn;
	
	// Title on 'Activity Log' page
	@FindBy(xpath = "//h4[contains(text(),'Activity Log')]")
	private WebElement activityLogTitle;

	public HeaderMenu(WebDriver driver) {
		super(driver);
	}
	
	public boolean isAt() {
		return userName.isDisplayed();
	}
	
	public InvoicesList goToInvoicesList() {
		invoicesMenuItem.click();
		return new InvoicesList(driver);
	}
	
	public BusinessList goToBusinessList() {
		businessMenuItem.click();
		return new BusinessList(driver);
	}
	
	public ClientsList goToClientsList() {
		clientsMenuItem.click();
		return new ClientsList(driver);
	}
	
	public boolean goToActivityLog() {
		expandMoreMenuItem.click();
		wait.until(ExpectedConditions.visibilityOf(activityLogMenuItem)).click();
		return wait.until(ExpectedConditions.visibilityOf(activityLogTitle)).isDisplayed();
	}
	
	public LoginPage logOut() {
		expandMoreMenuItem.click();
		wait.until(ExpectedConditions.visibilityOf(logoutMenuItem)).click();
		wait.until(ExpectedConditions.visibilityOf(confirmLogoutBtn)).click();
		return new LoginPage(driver);
	}

}
