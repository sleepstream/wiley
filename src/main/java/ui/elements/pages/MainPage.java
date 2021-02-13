package ui.elements.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ui.elements.pageFragments.InputGroup;
import ui.elements.pageFragments.NavBar;
import ui.elements.pageFragments.SearchResultsSection;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class MainPage  {

    @FindBy(css = "#main-header-navbar")
    public NavBar mainHeaderNavbar;

    @FindBy(css = "div.input-group")
    public InputGroup inputGroup;

    @FindBy(css = "#ui-id-2")
    public SelenideElement relatedContentField;

    @FindBy(css = "#ui-id-2 > .searchresults-section")
    public List<SearchResultsSection> searchResults;

    @Step("Check position of related content")
    public void checkPositionOfRelatedContent() {
        relatedContentField.shouldBe(Condition.visible);
        int top = relatedContentField.getLocation().y;
        int bottom = inputGroup.getInputField().getLocation().y + inputGroup.getInputField().getSize().height;

        assertTrue("Related content not right under serach field", top == bottom);
    }

}
