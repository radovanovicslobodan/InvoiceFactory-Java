package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClientsList extends PageObject {
	
	@FindBy(css = "[href='/clients/form']")
	WebElement addNewClientBtn;

	public ClientsList(WebDriver driver) {
		super(driver);
	}
	
	public boolean isAt() {
		return wait.until(ExpectedConditions.visibilityOf(addNewClientBtn)).isDisplayed();
	}

}
