package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {
	
	@FindBy(className = "title")
	private WebElement signInTitle;
	
	@FindBy(id = "login-form-email")
	private WebElement emailFld;
	
	@FindBy(id = "login-pass")
	private WebElement passwordFld;
	
	@FindBy(id = "login-form-btn")
	private WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isAt() {
		return signInTitle.isDisplayed();
	}
	
	// Login method, returns HeaderMenu object
	public HeaderMenu login(String email, String password) {
		emailFld.clear();
		passwordFld.clear();
		emailFld.sendKeys(email);
		passwordFld.sendKeys(password);
		loginBtn.click();
		
		return new HeaderMenu(driver);
	}
	
	

}
