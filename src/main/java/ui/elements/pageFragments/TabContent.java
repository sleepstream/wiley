package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TabContent extends ElementsContainer {

    @FindBy(css = ".tab-pane")
    @Getter
    private List<TabPanel> tabPanelList;
}
