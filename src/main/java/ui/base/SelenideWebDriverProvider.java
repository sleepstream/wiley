package ui.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static utils.TestProperties.getIntProperty;

public class SelenideWebDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1200,768");
        //options.addArguments("--lang=ru-RU");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("intl.accept_languages", "ru,ru-RU");
        options.setExperimentalOption("prefs", prefs);
        Configuration.timeout = getIntProperty("timeOutUI");
        return new ChromeDriver(options);
    }
}
