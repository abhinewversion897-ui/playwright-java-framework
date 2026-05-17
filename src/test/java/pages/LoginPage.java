package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

	Page page;

	// Private locators
	private Locator username;

	private Locator password;

	private Locator loginButton;

	private Locator dashboard;

	private Locator invalidErrorMessage;

	private Locator usernameBlankErrorMessage;

	private Locator passwordBlankErrorMessage;

	private Locator logoutDropdownbutton;

	private Locator logoutLink;
	
	private Locator loginText;

	// Constructor
	public LoginPage(Page page) {

		this.page = page;

		username = page.locator("//input[@name='username']");

		password = page.locator("//input[@name='password']");

		loginButton = page.locator("//button[@type='submit']");

		invalidErrorMessage = page.getByText("Invalid credentials");

		usernameBlankErrorMessage = page.locator("//label[text()='Username']//ancestor::div[2]//child::span");

		passwordBlankErrorMessage = page.locator("//label[text()='Password']//ancestor::div[2]//child::span");

		dashboard = page.locator("//h6[text()='Dashboard']");
		
		logoutDropdownbutton = page.locator(".oxd-userdropdown");
		
		logoutLink = page.getByRole(AriaRole.MENUITEM,new Page.GetByRoleOptions().setName("Logout"));
		
		loginText = page.locator("//h5[.='Login']");
	}

	// Public methods
	
	public boolean isDisplayedloginText() {
		
		return loginText.isVisible();
		
	}
	
	public void enterUsername(String user) {

		username.fill(user);
	}
	
	public Locator getusernameLocator() {

		return username;
	}

	public void enterPassword(String pass) {

		password.fill(pass);
	}

	public void clickLogin() {

		loginButton.click();
	}

	public void login(String user, String pass) {

		enterUsername(user);

		enterPassword(pass);

		clickLogin();
	}

	public Locator getLoginErrormessageLocator() {

		return invalidErrorMessage;
	}
	
	public String getLoginErrormessage() {

		return invalidErrorMessage.textContent();
	}


	public boolean getUsernameBlankMsg() {

		return usernameBlankErrorMessage.isVisible();

	}

	public boolean isDisplayedpasswordBlankErrorMessage() {

		return passwordBlankErrorMessage.isVisible();
	}

	public String getpasswordBlankErrorMessage() {

		return passwordBlankErrorMessage.textContent();
	}

	public Locator getlogoutDropdownbutton() {

		return logoutDropdownbutton;
	}
	
	
	
	public Locator getlogoutLink() {
		
		return logoutLink;
	}

	public Locator getDashboard() {

		return dashboard;
	}

	public boolean isDashboardVisible() {

		return dashboard.isVisible();
	}
}