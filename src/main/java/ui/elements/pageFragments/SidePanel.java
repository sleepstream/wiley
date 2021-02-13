package ui.elements.pageFragments;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SidePanel extends ElementsContainer {

    @FindBy(css = "header h4")
    private SelenideElement sidePanelTitle;

    @FindBy(css = "ul > li")
    private ElementsCollection sidePanelLinks;

    public String getSidePanelTitle() {
        return sidePanelTitle.getText().trim();
    }

    public void checkPanelLinks(List<String> expectedLinks) {
        sidePanelLinks.shouldHaveSize(expectedLinks.size());

        sidePanelLinks.shouldHave(CollectionCondition.textsInAnyOrder(expectedLinks));
    }
}
