package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.testng.AssertJUnit.assertEquals;

public class DropDownRootMenuItem extends ElementsContainer {

    @FindBy(css = "a.collapsed")
    private SelenideElement menuLink;

    @FindBy(css = "div.dropdown-menu")
    private SelenideElement dropdownMenu;

    @FindBy(css = "span.dropdown-item-title")
    private SelenideElement menuTitle;

    @FindBy(css = "ul.dropdown-items > li.dropdown-item")
    @Getter
    private List<DropDownMenu> dropDownRootMenu;

    @Step("Get menu title")
    public String getMenuTitle() {
        return menuLink.getText().trim();
    }

    @Step("Check menu title")
    public void checkMenuTitle(String menuTitle) {
        this.menuTitle.shouldHave(text(menuTitle));
    }

    @Step("Open menu")
    public void openMenu() {
        menuLink.hover();
        dropdownMenu.shouldBe(visible);
    }

    @Step("Open submenu")
    public void openSubMenuByName(String name) {
        List<DropDownMenu> list = dropDownRootMenu.stream().filter(item -> item.getItemTitle().equals(name)).collect(Collectors.toList());
        assertEquals("Expected menu item " + name +" not found, actual list " +
                dropDownRootMenu.stream().map(DropDownMenu::getItemTitle).collect(Collectors.toList()), 1, list.size());
        list.get(0).openMenu();
    }
}
