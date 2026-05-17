package utils;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.playwright.Page;

public class ScreenshotUtils {

	Page page;

	// Constructor
	public ScreenshotUtils(Page page) {
		this.page = page;
	}

	// Capture screenshot
	public String takeScreenshot(String testName) {
		page.waitForTimeout(1000);

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		String path = "test-output/screenshots/" + testName + "_" + timeStamp + ".png";

		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		return "screenshots/" + testName + "_" + timeStamp + ".png";
	}
}