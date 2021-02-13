package ui.elements.pageFragments;


import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;

public class DropDownMenu extends ElementsContainer {

    @FindBy(css = "a")
    private SelenideElement itemTitle;

    @FindBy(css = "ul.sub-list")
    private SelenideElement dropdownMenu;

    @FindBy(css = "ul.sub-list > li.dropdown-item")
    @Getter
    private List<DropDownMenu> dropDownSubMenu;

    public String getItemTitle() {
        return itemTitle.getText();
    }

    @Step("Open submenu")
    public void openMenu() {
        itemTitle.hover();
        dropdownMenu.shouldBe(visible);
    }

    public void clickMenu() {
        itemTitle.click();
    }
}
