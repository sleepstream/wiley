package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

public class TabPanel extends ElementsContainer {

    @FindBy(css = ".product-button")
    @Getter
    private SelenideElement actionItemContainer;

    @FindBy(css = ".product-button")
    @Getter
    private ActionItemButton actionItemButton;
}
