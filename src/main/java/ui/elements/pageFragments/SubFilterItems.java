package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

public class SubFilterItems extends ElementsContainer {

    @FindBy(css = "a")
    private SelenideElement filterLink;

    @FindBy(css = "i")
    @Getter
    private SelenideElement preResultFilterCount;

    public String getFilterLink() {
        return filterLink.getText().replace(preResultFilterCount.getText(), "").trim();
    }
}
