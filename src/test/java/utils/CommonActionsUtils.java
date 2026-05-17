package utils;

import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CommonActionsUtils {

    Page page;

    // Soft Assert Object
    SoftAssert softAssert = new SoftAssert();

    // Constructor
    public CommonActionsUtils(Page page) {

        this.page = page;
    }

    // ==============================
    // CLICK
    // ==============================

    public void click(String locator) {

        try {

            page.locator(locator).click();

            System.out.println("Clicked on element: " + locator);

        } catch (Exception e) {

            System.out.println("Unable to click element: " + locator);

            e.printStackTrace();
        }
    }

    // ==============================
    // JAVASCRIPT CLICK
    // ==============================

    public void javascriptClick(String locator) {

        try {

            Locator element = page.locator(locator);

            element.evaluate("element => element.click()");

            System.out.println("JavaScript click performed on: " + locator);

        } catch (Exception e) {

            System.out.println("JavaScript click failed on: " + locator);

            e.printStackTrace();
        }
    }

    // ==============================
    // ENTER TEXT
    // ==============================

    public void enterText(String locator, String text) {

        try {

            page.locator(locator).fill(text);

            System.out.println("Entered text into: " + locator);

        } catch (Exception e) {

            System.out.println("Unable to enter text: " + locator);

            e.printStackTrace();
        }
    }

    // ==============================
    // GET TEXT
    // ==============================

    public String getText(String locator) {

        String text = "";

        try {

            text = page.locator(locator).textContent();

            System.out.println("Fetched text from: " + locator);

        } catch (Exception e) {

            System.out.println("Unable to fetch text");

            e.printStackTrace();
        }

        return text;
    }

    // ==============================
    // IS VISIBLE
    // ==============================

    public boolean isVisible(String locator) {

        boolean status = false;

        try {

            status = page.locator(locator).isVisible();

            System.out.println("Visibility status checked for: " + locator);

        } catch (Exception e) {

            System.out.println("Element not visible: " + locator);

            e.printStackTrace();
        }

        return status;
    }

    // ==============================
    // SCROLL TO ELEMENT
    // ==============================

    public void scrollToElement(String locator) {

        try {

            page.locator(locator).scrollIntoViewIfNeeded();

            System.out.println("Scrolled to element: " + locator);

        } catch (Exception e) {

            System.out.println("Unable to scroll to element");

            e.printStackTrace();
        }
    }

    // ==============================
    // SCROLL + CLICK
    // ==============================

    public void scrollAndClick(String locator) {

        try {

            page.locator(locator).scrollIntoViewIfNeeded();

            page.locator(locator).click();

            System.out.println("Scrolled and clicked element: " + locator);

        } catch (Exception e) {

            System.out.println("Scroll and click failed");

            e.printStackTrace();
        }
    }

    // ==============================
    // DYNAMIC WAIT
    // ==============================

    public void waitForElement(String locator) {

        try {

            page.locator(locator).waitFor();

            System.out.println("Waited for element: " + locator);

        } catch (Exception e) {

            System.out.println("Wait failed for element");

            e.printStackTrace();
        }
    }

    // ==============================
    // RETRY CLICK MECHANISM
    // ==============================

    public void retryClick(String locator, int retryCount) {

        for (int i = 1; i <= retryCount; i++) {

            try {

                page.locator(locator).click();

                System.out.println("Clicked successfully on attempt: " + i);

                break;

            } catch (Exception e) {

                System.out.println("Retrying click attempt: " + i);

                if (i == retryCount) {

                    System.out.println("Click failed after retries");

                    e.printStackTrace();
                }
            }
        }
    }

    // ==============================
    // PAGE TITLE
    // ==============================

    public String getPageTitle() {

        return page.title();
    }

    // ==============================
    // NAVIGATE URL
    // ==============================

    public void navigateURL(String url) {

        page.navigate(url);

        System.out.println("Navigated to URL: " + url);
    }

    // ==============================
    // SOFT ASSERTION
    // ==============================

    public void softAssertVisible(String locator) {

        try {

            softAssert.assertTrue(
                    page.locator(locator).isVisible());

            System.out.println("Soft assert passed for: " + locator);

        } catch (Exception e) {

            System.out.println("Soft assert failed");

            e.printStackTrace();
        }
    }

    // ==============================
    // ASSERT ALL
    // ==============================

    public void assertAll() {

        softAssert.assertAll();
    }
}