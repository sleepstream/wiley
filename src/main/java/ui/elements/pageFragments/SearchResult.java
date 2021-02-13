package ui.elements.pageFragments;


import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class SearchResult extends ElementsContainer {

    @FindBy(css = "span.search-highlight")
    private SelenideElement searchHighlight;

    @FindBy(css = "a")
    private SelenideElement resultLink;
}
