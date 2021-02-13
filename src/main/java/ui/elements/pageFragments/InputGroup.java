package ui.elements.pageFragments;


import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

public class InputGroup extends ElementsContainer {

    @FindBy(css = "#js-site-search-input")
    @Getter
    private SelenideElement inputField;

    @FindBy(css = ".input-group-btn > button")
    private SelenideElement searchButton;


    @Step("Enter data {data} in input field")
    public void enterDataIntoInputField(String data) {
        inputField.sendKeys(data);
    }
    @Step("Click search enter button")
    public void clickSearchButton() {
        searchButton.click();
    }
}
