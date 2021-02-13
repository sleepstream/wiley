package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PanelTypeSwitcher extends ElementsContainer {

    @FindBy(css = "li[role='presentation']")
    @Getter
    private List<Presentation> presentationList;

    @FindBy(css = "li.active[role='presentation']")
    @Getter
    private Presentation presentationActive;

}
