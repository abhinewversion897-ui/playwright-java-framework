package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.ConfigReader;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

	@Test(retryAnalyzer = utils.RetryAnalyzer.class)
	public void Login_withExcel_Test() {

		test = extent.createTest("Verify Login Test with excel");

		ExcelUtils excel = new ExcelUtils(
				"C:/Users/Lenovo/eclipse-workspace/Ego/sumsung/src/main/resources/testdata/LoginData.xlsx", "Sheet1");

		String username = excel.getCellData(1, 0);

		String password = excel.getCellData(1, 1);

		login.login(username, password);
		wait.waitForElement(login.getDashboard());

		Assert.assertTrue(login.isDashboardVisible());

	}

	@Test
	public void valid_Login_Test() {

		test = extent.createTest("Verify Login Test");

		login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		wait.waitForElement(login.getDashboard());

		Assert.assertTrue(login.isDashboardVisible());

	}

	@Test
	public void invalid_Login_Test() {

		test = extent.createTest("Invalid Login Test");
		String expectedErrorMessage = "Invalid credentials";

		login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("invalidPassword"));

		wait.waitForElement(login.getLoginErrormessageLocator());
		String actualErrorMessage = login.getLoginErrormessage();

		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}

	@Test
	public void verifyBlankUsernameErrorMessage() {

		test = extent.createTest("verify Blank Username ErrorMessage is displayed");

		login.login(ConfigReader.getProperty("blankusername"), ConfigReader.getProperty("password"));

		login.clickLogin();

		Assert.assertTrue(login.getUsernameBlankMsg());
	}

	@Test
	public void verifyBlankpasswordErrorMessage() {

		test = extent.createTest("verify Blank Password ErrorMessage is displayed");

		String expectedErrorMessage = "Required";

		login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("blankPassword"));

		login.clickLogin();

		String actualErrorMessage = login.getpasswordBlankErrorMessage();

		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}

	@Test
	public void valid_Logout() {

		test = extent.createTest("Verify Logout Test");

		login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		wait.waitForElement(login.getDashboard());

		login.getlogoutDropdownbutton().click();
		login.getlogoutLink().click();

		wait.waitForElement(login.getusernameLocator());

		Assert.assertTrue(login.isDisplayedloginText());

	}

}