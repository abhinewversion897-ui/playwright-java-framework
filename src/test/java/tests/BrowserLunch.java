package tests;

import java.util.Arrays;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BrowserLunch {
	Playwright playwright;
	BrowserContext context;
	Browser browser;
	Page page;

	@BeforeMethod
	public void BrowserSetup() {
		playwright = Playwright.create();

		browser = playwright.chromium()
				.launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
		context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
		page = browser.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/");

	}

	@Test
	public void demoappLunch() {

		page.locator("//input[@name='username']").fill("Admin");
		page.locator("//input[@name='password']").fill("admin123A");
		page.locator("//button[@type='submit']").click();
		System.out.println(page.title());
		assertThat(page.locator("//div[@role='alert']//child::p[1]")).isVisible();

	}

	@Test
	public void navigate_To_Demoapp() {

		page.locator("//input[@name='username']").fill("Admin");
		page.locator("//input[@name='password']").fill("admin123");
		page.locator("//button[@type='submit']").click();
		System.out.println(page.title());
		page.locator("//h6[.='Dashboard']").waitFor();
		assertThat(page.locator("//h6[text()='Dashboard']")).isVisible();

	}

	@AfterMethod
	public void close_Browser() {

		browser.close();
		playwright.close();
	}

}
