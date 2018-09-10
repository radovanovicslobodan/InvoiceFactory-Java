package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BusinessList extends PageObject {
	
	@FindBy(id = "add-new-business")
	WebElement addNewBusinessBtn;

	public BusinessList(WebDriver driver) {
		super(driver);
	}
	
	public boolean isAt() {
		return wait.until(ExpectedConditions.visibilityOf(addNewBusinessBtn)).isDisplayed();
	}

}