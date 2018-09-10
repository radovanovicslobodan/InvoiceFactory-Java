package parameters;

import org.testng.annotations.DataProvider;

// Doesn't really need @DataProvider. For learning purpose :-)
public class DataProviderClass {	
	@DataProvider(name = "LoginProvider")
	public static Object[][] getLoginDataFromDataprovider() {
		return new Object[][] {
			{ "", "" },
			{ "wrongemail@mail.com", "wrongpassword" },
			// Enter your credentials
			{ "your_email", "your_password" }
		};
	}
	
	@DataProvider(name = "HeaderProvider")
	public static Object[][] getHeaderDataFromDataprovider() {
		return new Object[][] {
			// Enter your credentials
			{ "your_email", "your_password" }
		};
	}
	
	@DataProvider(name = "NewInvoiceProvider")
	public static Object[][] getNewInvoiceDataFromDataprovider() {
		return new Object[][] {
			{ "Test title", "test basis", "test service", "test note", "8", "5" }
		};
	}
}
