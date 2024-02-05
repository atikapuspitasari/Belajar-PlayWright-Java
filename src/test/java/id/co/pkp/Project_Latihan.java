package id.co.pkp;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Project_Latihan {
    @Test
    @DisplayName("Test Login")
    public void loginPositiveTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://motif-dev.fifgroup.co.id/#/");

        page.getByPlaceholder("Masukan Username").fill("15958");
        page.getByPlaceholder("Masukan Password").fill("password");

        page.locator("#btn btn-fif btn-block btn-flat").click();

        page.close();
        browser.close();
        playwright.close();
    }
}
