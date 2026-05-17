package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WaitUtils {

    Page page;

    // Constructor
    public WaitUtils(Page page) {
        this.page = page;
    }

    // Wait for element
    public void waitForElement(Locator locator) {

    	locator.waitFor();
    }

    // Static wait
    public void waitForSeconds(int seconds) {

        page.waitForTimeout(seconds * 1000);
    }

    // Wait for URL
    public void waitForURL(String url) {

        page.waitForURL(url);
    }

    // Wait for page load
    public void waitForPageLoad() {

        page.waitForLoadState();
    }

    // Wait for network idle
    public void waitForNetworkIdle() {

        page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
    }

    // Wait for DOM content loaded
    public void waitForDOMLoad() {

        page.waitForLoadState(com.microsoft.playwright.options.LoadState.DOMCONTENTLOADED);
    }

    // Wait for selector
    public void waitForSelector(String locator) {

        page.waitForSelector(locator);
    }
}