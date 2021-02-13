package ui.elements.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import ui.elements.pageFragments.InputGroup;
import ui.elements.pageFragments.NavBar;
import ui.elements.pageFragments.SearchResultsSection;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ModalDialog {

    @FindBy(css = ".modal-dialog")
    @Getter
    private SelenideElement modalDialogWindow;

    @FindBy(css = ".changeLocationConfirmBtn")
    private SelenideElement confirmLocation;

    @Step("Press yes button")
    public void pressYesButton() {
        confirmLocation.click();
    }

}
