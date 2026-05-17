package base;

import java.util.Arrays;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ScreenshotUtils;
import utils.WaitUtils;

import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;

import pages.LoginPage;
import pages.EmployeeInfo;
import utils.ConfigReader;
import utils.ExtentManager;

public class BaseTest {

	protected static ThreadLocal<Playwright> playwright = new ThreadLocal<>();

	protected static ThreadLocal<Browser> browser = new ThreadLocal<>();

	protected static ThreadLocal<BrowserContext> context = new ThreadLocal<>();

	protected static ThreadLocal<Page> page = new ThreadLocal<>();
	protected LoginPage login;
	protected WaitUtils wait;
	protected EmployeeInfo empinfo;
	protected ConfigReader config;

	public static ExtentReports extent;

	public static ExtentTest test;

	@BeforeSuite
	public void startReport() {

		extent = ExtentManager.getReporter();
	}

	@BeforeMethod
	public void setup() {

		playwright.set(Playwright.create());

		browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true)));

		context.set(browser.get().newContext());

		page.set(context.get().newPage());

		config = new ConfigReader();

		page.get().navigate(ConfigReader.getProperty("url"));

		login = new LoginPage(page.get());

		empinfo = new EmployeeInfo(page.get());

		wait = new WaitUtils(page.get());
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		ScreenshotUtils screenshot = new ScreenshotUtils(page.get());

		String screenshotPath = screenshot.takeScreenshot(result.getName());

		try {

			if (result.getStatus() == ITestResult.SUCCESS) {

				test.pass(result.getName() + " Passed").addScreenCaptureFromPath(screenshotPath);
			} else if (result.getStatus() == ITestResult.FAILURE) {

				test.fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);

			} else if (result.getStatus() == ITestResult.SKIP) {

				test.skip("Test Skipped");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			if (page.get() != null)
				page.get().close();
			if (context.get() != null)
				context.get().close();
			if (browser.get() != null)
				browser.get().close();
			if (playwright.get() != null)
				playwright.get().close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			page.remove();
			context.remove();
			browser.remove();
			playwright.remove();
		}
	}

	@AfterSuite
	public void flushReport() {

		extent.flush();
	}
}