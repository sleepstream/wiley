package ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import listeners.SelenideListener;
import org.testng.annotations.BeforeTest;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static utils.TestProperties.*;

public abstract class SelenideBase  {

    @BeforeTest
    public void beforeTest() {
        //set up browser settings
        Configuration.fileDownload = FOLDER;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1200,768");
        options.addArguments("--lang=ru-RU");
        //to avoid 403 error in headless mode
        options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        Configuration.browserCapabilities = new MutableCapabilities(options);
        Configuration.screenshots = true;
        Configuration.timeout = getIntProperty("timeOutUI");
        SelenideLogger.addListener("allure", new SelenideListener());
    }
}