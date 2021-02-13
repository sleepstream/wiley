package ui.elements.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ui.elements.pageFragments.InputGroup;
import ui.elements.pageFragments.NavBar;
import ui.elements.pageFragments.SearchResultsSection;
import ui.elements.pageFragments.SidePanel;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class EducationPage {

    @FindBy(css = "#main-header-navbar")
    public NavBar mainHeaderNavbar;

    @FindBy(css = "div.input-group")
    public InputGroup inputGroup;

    @FindBy(css = "#ui-id-2")
    public SelenideElement relatedContentField;

    @FindBy(css = "#ui-id-2 > .searchresults-section")
    public List<SearchResultsSection> searchResults;

    @FindBy(css = ".side-panel")
    private SidePanel sidePanel;

    @Step("Check side panel links")
    public void checkPanelLinks(List<String> expectedLinks) {
        sidePanel.checkPanelLinks(expectedLinks);
    }
}
