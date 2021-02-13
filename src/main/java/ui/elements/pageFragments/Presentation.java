package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class Presentation extends ElementsContainer {

    @FindBy(css = "a")
    private SelenideElement switcherLink;

    @FindBy(css = "a > .productButtonGroupName")
    private SelenideElement productButtonGroupName;

    @FindBy(css = "a > .productButtonPrice")
    private SelenideElement productButtonPrice;

    @Step("Switch tab")
    public void switchTab() {
        switcherLink.click();
    }

    @Step("Get title of current tab")
    public String getTitleOfCurrentTab() {
        return productButtonGroupName.getText().trim();
    }
}
