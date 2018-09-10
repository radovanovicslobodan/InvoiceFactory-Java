package page_elements;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.PageObject;

public class DatePicker extends PageObject {
	
	// 'OK' button on 'From' date picker
	@FindBy(id = "filter-from-ok")
	private WebElement fromOkBtn;
	
	// 'OK' button on 'To' date picker
	@FindBy(id = "filter-to-ok")
	private WebElement toOkBtn;
	
	// Locator for 'Date picker' header using as activator for selecting year and month
	@FindBy(css = ".menuable__content__active .accent--text")
	private WebElement dateHeaderAct;
	
	// Select all days in the month
	@FindBy(css = ".menuable__content__active td button")
	private List <WebElement> days;
	
	// Select all months in the year
	@FindBy(css = ".menuable__content__active td button")
	private List <WebElement> months;
	
	// Select all years from the list
	@FindBy(css = ".v-date-picker-years li")
	private List <WebElement> years;

	public DatePicker(WebDriver driver) {
		super(driver);
	}
	
	// Enter year in 'Date picker'
	public void enterYear(int year) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(dateHeaderAct)).click();
		// Doesn't work without 'sleep'. Need review!
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(dateHeaderAct)).click();
		
		// 2118 is the year list starts with. (2118 - year) is the year we set.
		wait.until(ExpectedConditions.visibilityOfAllElements(years)).get(2118 - year).click();
	}
	
	public void enterMonth(int month) {
		months.get(month - 1).click();
	}
	
	public void enterDay(int day) {
		days.get(day - 1).click();
	}
	
	// Enter date. Parameter is string in "yyyy-mm-dd" form
	public void enterDate(String date) throws InterruptedException {
		LocalDate tempDate = null;
		try {
			// Parse date string into LocalDate format
			tempDate = LocalDate.parse(date);
		} catch(Exception e) {
			
		}
		
		int year = tempDate.getYear();
		int month = tempDate.getMonthValue();
		int day = tempDate.getDayOfMonth();
		
		enterYear(year);
		enterMonth(month);
		enterDay(day);
	}
	
	public void setFromDate(String date) throws InterruptedException {				
		enterDate(date);
		fromOkBtn.click();
	}
	
	public void setToDate(String date) throws InterruptedException {
		enterDate(date);
		toOkBtn.click();
	}
}
