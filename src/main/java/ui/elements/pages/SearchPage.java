package ui.elements.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ui.base.SearchFilter;
import ui.elements.pageFragments.*;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class SearchPage {

    @FindBy(css = ".search-result-page")
    private SelenideElement searchResultsPage;

    @FindBy(css = "div.products-list")
    private ProductsList productsList;

    @FindBy(css = "div.input-group")
    private InputGroup inputGroup;

    @FindBy(css = "#ui-id-2")
    private SelenideElement relatedContentField;

    @FindBy(css = "#ui-id-2 > .searchresults-section")
    private List<SearchResultsSection> searchResults;

    @FindBy(css = "#product-facet")
    private FiltersBlock filtersBlock;

    @Step("Check Search page data")
    public void checkSearchPageData() {
        searchResultsPage.shouldBe(Condition.visible);
        productsList.checkProductItemsData();
    }

    @Step("check filter items data")
    public void checkFilterItemsData(SearchFilter searchFilter) {
        filtersBlock.checkSubFilterItemsData(searchFilter);
    }

}
