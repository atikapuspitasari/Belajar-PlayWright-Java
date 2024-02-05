package id.co.pkp;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

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

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Submit")).last().click();

//        assertThat(page.locator
//                ("wp-block-button__link has-text-color has-background has-very-dark-gray-background-color"))
//                .containsText("Log out");
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
//                .setName("Log out")).last().click();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Test Negative Username Test")
    public void negativeUsernameTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://practicetestautomation.com/practice-test-login/");

        page.locator("#username").type("incorrectUser");
        page.locator("#password").type("Password123");
        page.locator("#submit").click();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Submit")).last().click();

        Path screenshotPath = Paths.get("TEST CASE 2" + System.currentTimeMillis() + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Test Negative Password Test")
    public void passwordNegativeTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://practicetestautomation.com/practice-test-login/");

        page.locator("#username").type("student");
        page.locator("#password").type("incorrectPassword ");
        page.locator("#submit").click();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Submit")).last().click();

//        assertThat(page.getByText("Your username is invalid!")).isVisible();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Test Exceptions")
    public void exceptionTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://practicetestautomation.com/practice-test-exceptions/");

        page.locator("#row1").type("Pizza");
        page.locator("#add_btn").click();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Test Element Not Interactable Exception")
    public void elementNotInteractableExceptionTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://practicetestautomation.com/practice-test-exceptions/");

        page.locator("#add_btn").click();
//        page.getByLabel("Row 2").fill("Kentang");
        page.getByLabel("Row 2").type("Kentang");
        page.locator("#save_btn").click();

        page.close();
        browser.close();
        playwright.close();
    }


}

