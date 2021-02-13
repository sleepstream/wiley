package ui.elements.pageFragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import ui.base.SearchFilter;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class FilterItem extends ElementsContainer {

    @FindBy(css = ".facets-panel-block-title")
    private SelenideElement title;

    @FindBy(css = ".facet-list.facets-panel-list.js-facet-list > .facets-panel-item")
    private List<SubFilterItems> subFilterItemList;

    @FindBy(css = ".facet-list.facets-panel-list.js-facet-list")
    private SelenideElement subItemBlock;

    public String getTitle() {
        return title.getText();
    }

    @Step("Click filter header")
    public void clickHeaderItem() {
        title.click();
    }

    @Step("Check sub filter items data")
    public void checkSubFilterItems(SearchFilter subFilterItems) {
        assertEquals("Expected count of sub filter items not equals to " + subFilterItems.getSubFilterCount(),
                (int)subFilterItems.getSubFilterCount(), subFilterItemList.size());
        assertThat("Expected filter list not equal to actual",
                subFilterItemList.stream().map(SubFilterItems::getFilterLink).collect(Collectors.toList()),
                hasItems(subFilterItems.getSubFilterItems().toArray()));
    }

    @Step("Check filter item title")
    public void checkFilterItemHeader(String rootFilterTitle) {
        title.shouldHave(Condition.exactText(rootFilterTitle));
    }
}
