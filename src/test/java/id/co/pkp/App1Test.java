package id.co.pkp;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class App1Test {
    @Test
    @DisplayName("Test Web PKP")
    public void test1() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("https://www.google.co.id");
        System.out.println("Page Title ini adalah " + page.title());
    }

    @Test
    @DisplayName("Check URL or Check HTTPS")
    public void testCheckHTTPS() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://10.8.11.1:8761/");
        String currentUrl = page.url();
        String expectedUrl = "https://10.8.11.1:8761/";
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("URL is correct: " + currentUrl);
        } else {
            System.out.println("URL is incorrect. Expected: " + expectedUrl + ", but got: " + currentUrl);
        }
        // System.out.println(currentUrl);
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Check Place Holder")
    public void checkPlaceHolder() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com/user/login");

        Locator searchBar = page.locator("#edit-keys");
        String placeText = searchBar.getAttribute("placeholder");

        if (placeText.contains("Enter the terms you wish to search for")) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL! No such texts");
        }
    }

    @Test
    @DisplayName("Assert Check Box")
    public void assertCheckBox() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/dropdown1/");
        Locator locator = page.locator("select.custom-select option >> nth=-2");
        String attributeV = locator.getAttribute("value");

        if (attributeV.equals("item3")) {
            System.out.println("Attribute value is correct!");
        } else {
            System.out.println("Attribute value is incorrect.");
        }
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Tooltip Check Test")
    public void tooltipCheckTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();

        Page page = browser.newPage();
        page.navigate("https://jqueryui.com/tooltip/");
        FrameLocator frameOne = page.frameLocator(".demo-frame");
        Locator ageBox = frameOne.locator("#age");
        Locator toolTipText = frameOne.locator(".ui-tooltip-content");
        ageBox.hover();
        String textContent = toolTipText.textContent();
        System.out.println(textContent);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Soft Assertion Test")
    public void softAssertionTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com/user/login");
        page.locator("#edit-name").type("yuji");
        page.locator("#edit-pass").type("yuji");
        page.locator("(//input[@type='submit'])[2]").click();
        String actualText = page.locator("//a[normalize-space()='Forgot your password?']").textContent();
        System.out.println(actualText);
        String expectedText = "Forgot your password?";
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(actualText, expectedText, "Matched");

        System.out.println("This part is executed");
        soft.assertAll();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Assert Title Test")
    public void assertTitleTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("http://www.programsbuzz.com");
        String title = page.title();
        String expectedTitle = "ProgramsBuzz - Online Technical Courses";
        if (title.equalsIgnoreCase(expectedTitle)) {
            System.out.println("Title Match Verfied");
        } else {
            System.out.println("Not a match!!");
        }

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Assert Text On Web Page Test")
    public void assertTextOnWebPageTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("http://www.programsbuzz.com");
        Locator body = page.locator("body");
        String bodyText = body.textContent();
        Assert.assertFalse(bodyText.contains("Spam Message"), "Spam Text Not Found!!");

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Get Current URL Test")
    public void getCurrentURL() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("http://www.programsbuzz.com/user/login");
        page.locator("#edit-name").type("Naruto");
        page.locator("#edit-pass").type("uzumaki");

        String currentUrl = page.url();
        System.out.println(currentUrl);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Click Browser Back and Forward Button")
    public void clickBrowserBackNForwardButtonTest() {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext();
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com");
        page.locator("#edit-submit--3").click();
//        page.locator("//i[@class='fas fa-search']").click();
        page.locator("//input[@id='edit-keys']").type("Playwright");
        page.locator("//input[@id='edit-submit']").click();
        page.goBack();
        page.goForward();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Navigate to URL")
    public void navigateToURL() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/upload1/");

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("How to Refresh Page")
    public void howtoRefreshPageTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/popup/");

        page.reload();
        page.locator("//a[normalize-space()='JQuery Popup Model']").click();
        String textContent = page.locator("//p[normalize-space()='This is Sample Popup.']").textContent();
        System.out.println(textContent);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Maximize Browser")
    public void maximizeBrowserTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        browser.newContext(new Browser.NewContextOptions().setViewportSize(800, 600));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

//        otomatis pengaturan ukuran window
//        int width = (int) screenSize.getWidth();
//        int height = (int) screenSize.getHeight();
//        BrowserContext newContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
//        Page page = browser.newPage();

        Page page = browser.newPage();
        page.navigate("https://www.google.co.id");

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Get First and Last Element in Playwright")
    public void getFirstAndLastElementTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        //      Find the Fisrt Element using the first method
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEls = page.locator("//h3[@class='search-result__title']");
        listEls.first().click();

//        Find the first Element using Nth Method
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle2 = page.locator("//h3[@class='search-result__title']");
        listEle2.nth(0).click();

//        Find the Last Element using the last method
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle3 = page.locator("//h3[@class='search-result__title']");
        listEle3.last().click();

//        Find the Last Element using Nth Method
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle4 = page.locator("//h3[@class='search-result__title']");
        listEle4.nth(-1).click();

        page.close();
        browser.close();
        playwright.close();
    }


    @Test
    @DisplayName("Get List of Elements")
    public void getListofElementTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//      Get the count of elements present
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        Locator listEle = page.locator("//h3[@class='search-result__title']");
        int count = listEle.count();
        Assert.assertEquals(count, 10);

//      Nth content filter
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        String textContent = listEle.nth(1).textContent();
        System.out.println(textContent);

//      Display a list of texts
        page.navigate("https://www.programsbuzz.com/search/node?keys=playwright+java");
        List<String> allTextContents = listEle.allTextContents();
        System.out.println(allTextContents);
        allTextContents.forEach(System.out::println);

        for (int i = 0; i < allTextContents.size(); i++) {
            System.out.println(i + 1 + "." + allTextContents.get(i));
        }

        page.close();
        browser.close();
        playwright.close();
    }


    @Test
    @DisplayName("Using XPath")
    public void usingXPathTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://www.programsbuzz.com/user/login");
//        page.locator("xpath=//input[@id= 'edit-name']").type("Naruto");
        page.locator("//input[@id= 'edit-name']").type("Naruto");
        page.locator("//input[@id= 'edit-pass']").type("Sasuke");
        page.locator("//input[@id= 'edit-submit']").click();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Using Combo Box")
    public void usingComboBoxTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//        Dropdown
        page.navigate("http://autopract.com/selenium/dropdown1/");
        page.selectOption(".custom-select", "item2");

//        Dynamic Dropdown
        page.navigate("http://autopract.com/selenium/dropdown4/");
        page.locator("//span[@class='caret']").click();
        Locator countries = page.locator("//div[@role='combobox']");
        List<String> allInnerTexts = countries.allInnerTexts();

        allInnerTexts.forEach(System.out::println);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Autocomplete")
    public void handleAutocompleteTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        String expectedText = "Indonesia";
        page.navigate("https://demo.automationtesting.in/AutoComplete.html");
        Locator autoC = page
                .locator("//div[@class='ui-autocomplete-multiselect ui-state-default ui-widget ui-state-active']");
        int autoCcount = autoC.count();
        System.out.println("autoCcount: " + autoCcount);

        page.pause();

        for (int i = 0; i < autoCcount; i++) {
            String autoCText = autoC.nth(i).textContent();
            System.out.println("Yang dicari: " + autoCcount);
            if (autoCText == expectedText) {
                autoC.nth(i).click();
                break;
            }
        }

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Check and Uncheck Checkbox")
    public void checkandUncheckCheckboxTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//        Using Click Method
        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='two']").click();

//        Using Check
//        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='four']").check();

//        Using UnCheck
//        page.navigate("http://autopract.com/selenium/form5//");
        page.locator("//input[@value='four']").uncheck();

//        Click Radio Button
//        page.navigate("http://autopract.com/selenium/form5/");
        page.locator("input[value='CA']").click();
        page.locator("input[value='mac']").check();

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Frame")
    public void handleFrameText() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//        link tidak dapat diakses
        page.navigate("http://www.maths.surrey.ac.uk/explore/nigelspages/frame2.html");

        FrameLocator middleFrame = page.frameLocator("//frame[@src='message.htm']");

        middleFrame.locator("//input[@name='name']").type("Naruto Uzumaki");
        middleFrame.locator("//textarea[@name='suggestions']").type("I Am Inside The Frame");

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Nested Frames")
    public void handleNestedFramesTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://the-internet.herokuapp.com/nested_frames");
        FrameLocator parentFrame = page.frameLocator("//frame[@name='frame-top']");
        FrameLocator middleFrame = parentFrame.frameLocator("//frame[@name='frame-middle']");
        String textContent = middleFrame.locator("body").textContent();
        System.out.println(textContent);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Multiple Tabs")
    public void handleMultipleTabsTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//        Link tidak bisa diakses
        page.navigate("https://www.programsbuzz.com/");
        page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(p -> p.context().pages().size() == 2), () -> {
            page.locator("a[href='https://www.ivagus.com']").click();
        });

        List<Page> pages = page.context().pages();

        for (Page tabs : pages) {

            tabs.waitForLoadState();
            System.out.println(tabs.url());
        }

        Page pbPage = pages.get(0);
        Page ivagusPage = pages.get(1);

        System.out.println(pbPage.url());
        System.out.println(ivagusPage.url());

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Alert Button")
    public void handleAlertButtonTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext newContext = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
        Page page = newContext.newPage();

        page.navigate("http://autopract.com/selenium/alert5/");
        page.onDialog(Dialog::accept);
        page.locator("#alert-button").click();

        newContext.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Alert Confirm")
    public void handleAlertConfirmTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext newContext = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
        Page page = newContext.newPage();

        page.navigate("http://autopract.com/selenium/alert5/");
        page.onDialog(Dialog::dismiss);
        page.locator("#confirm-button").click();

        newContext.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle Alert Prompt")
    public void handleAlertPromptTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext newContext = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(Paths.get("Videos/")).setRecordVideoSize(1280, 720));
        Page page = newContext.newPage();

        page.navigate("http://autopract.com/selenium/alert5/");
        page.onDialog(dialog -> {
            dialog.accept("20");
        });
        page.locator("#prompt-button").click();

        newContext.close();
        playwright.close();
    }

    @Test
    @DisplayName("Handle PopUp")
    public void handlePopupTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("http://autopract.com/selenium/popup/");
        Page popUp = page.waitForPopup(() -> {
            page.locator("//a[normalize-space()='Open Link in Popup']").click();
        });
        popUp.waitForLoadState();
        System.out.println(popUp.title());

        page.navigate("http://autopract.com/selenium/popup/");
        page.locator("//a[normalize-space()='JQuery Popup Model']").click();
        String textContent = page.locator("//p[normalize-space()='This is Sample Popup.']").textContent();
        System.out.println(textContent);

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Take Screenshot in Playwright Java")
    public void takeScreenshotTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/upload1/");

//        page.setInputFiles("//input[@type='file']",
//                Paths.get("C:\\Users\\arili\\git\\Assignments\\Selenium\\target\\CRED.xlsx"));
//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("C:\\Users\\arili\\git\\Assignments\\Selenium\\target\\uploadPage.png")));

//        get final screenshot
//        Path screenshotPath = Paths.get("Test_" + System.currentTimeMillis() + ".jpg");
        LocalDateTime dateTime = LocalDateTime.now();
//        System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

        String tanggal = dateTime.format(DateTimeFormatter.ofPattern("ddMMuuuu_HHmmss"));
        System.out.println(tanggal);
        Path screenshotPath = Paths.get("Test1_" + tanggal + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true));

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Record Video in Playwright Java")
    public void recordVideoTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext newContext = browser.newContext(
                new Browser.NewContextOptions().setRecordVideoDir(
                        Paths.get("Video/")).setRecordVideoSize(1280, 720));

        Page page = newContext.newPage();

        page.navigate("https://www.programsbuzz.com/user/login");

        page.locator("#edit-name").type("Naruto");
        page.locator("#edit-pass").type("Madara");

        newContext.close();
        playwright.close();
    }

    @Test
    @DisplayName("Download File in Playwright Java")
    public void downloadFileTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://demo.automationtesting.in/FileDownload.html");
        Download waitForDownload = page.waitForDownload(page.locator("a.btn.btn-primary")::click);

        waitForDownload.saveAs(Paths.get("Downloads/", waitForDownload.suggestedFilename()));

        System.out.println(waitForDownload.url());
        System.out.println(waitForDownload.page().title());
        System.out.println(waitForDownload.path().toString());

        page.close();
        browser.close();
        playwright.close();

    }
}

