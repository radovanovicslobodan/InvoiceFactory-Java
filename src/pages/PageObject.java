package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
	public WebDriver driver;
	public WebDriverWait wait;
	
	public PageObject(WebDriver driver) {
		this.driver = driver;
		
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}
}