package ui.elements.pageFragments;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsSection extends ElementsContainer {

    @FindBy(css = "h3")
    private SelenideElement suggestions;

    @FindBy(css = "div.searchresults-item ")
    private List<SearchResult> searchResults;
}
