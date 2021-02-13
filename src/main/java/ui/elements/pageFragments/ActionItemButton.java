package ui.elements.pageFragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static org.testng.AssertJUnit.assertTrue;

public class ActionItemButton extends ElementsContainer {

    @FindBy(css = ".small-button")
    private SelenideElement buttonObject;

    @FindBy(css = "a.small-button")
    private SelenideElement buttonLink;

    @FindBy(css = "button.small-button")
    private SelenideElement button;

    @FindBy(css = "div.product-button .addToCartTitle")
    private SelenideElement buttonTitle;

    @FindBy(css = "form")
    private SelenideElement formData;

    @Step("Get button title")
    public String getButtonTitle() {
        buttonObject.shouldBe(Condition.visible);
        return button.isDisplayed() ? button.getText() : buttonLink.getText();
    }

    @Step("Add to cart button - displayed")
    public void addToCartButtonDisplayed() {
        buttonTitle.shouldBe(Condition.visible);
    }
}
