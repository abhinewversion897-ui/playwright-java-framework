package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class EmployeeInfo {

	Page page;

	private Locator pimSubtab;

	private Locator enterempname;

	private Locator enterempId;

	private Locator include;

	private Locator employmentStats;
	
	private Locator searchbutton;
	
	private Locator norcodsfound;

	public EmployeeInfo(Page page) {

		this.page = page;

		pimSubtab = page.locator("//ul[@class='oxd-main-menu']//li[2]//span");

		enterempname = page.locator("//label[text()='Employee Name']/following::input[1]");

		enterempId = page.locator("//label[text()='Employee Id']/following::input[1]");

		include = page.locator("//label[text()='Include']/parent::div/parent::div//i");

		employmentStats = page.locator("//label[text()='Employment Status']/parent::div/parent::div//i");
		
		searchbutton = page.locator("//button[@type='submit']");
		
		norcodsfound = page.locator("//div[@id='oxd-toaster_1']//p[text()='No Records Found']");

	}

	public Locator getPimSubTab() {

		return pimSubtab;
	}

	public void getenterempname(String empname) {

		enterempname.fill(empname);
	}

	public void getenterempId(String empId) {

		enterempId.fill(empId);
	}

	public void getincludeclick() {

		include.click();
	}

	public void getemploymentstatusclick() {

		employmentStats.click();
	}
	
	public void getsearchbuttonclick() {

		searchbutton.click();
	}
	
	
	public String getnorcodsfoundmesg() {

		return norcodsfound.textContent();
	}



	public Locator getemployemtstsOptionclick(String optionText) {
		return page.locator("//label[normalize-space()='Employment Status']//following::span[contains(text(),'"
				+ optionText + "')]");
	}

	public Locator getincludeOptionclick(String optionText) {
		return page.locator(
				"//label[normalize-space()='Include']//following::span[contains(text(),'" + optionText + "')]");
	}
}