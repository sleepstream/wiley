package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ui.base.SearchFilter;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class FiltersBlock extends ElementsContainer {

    @FindBy(css = "header > h2")
    private SelenideElement title;

    @FindBy(css = ".facets-panel > .js-facet")
    private List<FilterItem> filterItemList;

    @Step("Open filter list by name = {name}")
    public void openFilterListByName(String name) {
        List<FilterItem> list = filterItemList.stream().filter(item -> item.getTitle().equals(name)).collect(Collectors.toList());
        assertEquals("Expected filter item " + name +" not found, actual list " +
                        filterItemList.stream().map(FilterItem::getTitle).collect(Collectors.toList()), 1, list.size());
        list.get(0).clickHeaderItem();
    }

    @Step("Check sub filter items data")
    public void checkSubFilterItemsData(SearchFilter filterItem) {
        openFilterListByName(filterItem.getRootFilterTitle());
        List<FilterItem> list = filterItemList.stream().filter(item -> item.getTitle().equals(filterItem.getRootFilterTitle())).collect(Collectors.toList());

        list.get(0).checkSubFilterItems(filterItem);

    }
}
