package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.ConfigReader;
import utils.FakerUtils;

public class CreateEmployee extends BaseTest {

	@Test
	public void searchemployeError() {

		test = extent.createTest("Verify search employe Error");

		String expectedinfomessg = "No Records Found";

		FakerUtils fake = new FakerUtils();
		login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		wait.waitForElement(login.getDashboard());

		empinfo.getPimSubTab().click();

		empinfo.getenterempname(fake.getFirstName());

		empinfo.getenterempId(fake.getEmployeeId());

		empinfo.getemploymentstatusclick();

		empinfo.getemployemtstsOptionclick("Full-Time Permanent").click();

		empinfo.getincludeclick();

		empinfo.getincludeOptionclick("Current Employees Only").click();

		empinfo.getsearchbuttonclick();

		String actualinfomessg = empinfo.getnorcodsfoundmesg();

		Assert.assertEquals(actualinfomessg, expectedinfomessg);

	}

}
