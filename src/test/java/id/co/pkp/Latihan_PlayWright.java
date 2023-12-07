package id.co.pkp;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Latihan_PlayWright {
    @Test
    @DisplayName("Test Login Positive Test")
    public void loginPositiveTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://practicetestautomation.com/practice-test-login/");

        page.locator("#username").type("student");
        page.locator("#password").type("Password123");
        page.locator("#submit").click();


        page.close();
        browser.close();
        playwright.close();
    }
}
