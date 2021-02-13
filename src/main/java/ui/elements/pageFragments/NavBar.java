package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import ui.base.NavBarMenu;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.AssertJUnit.assertEquals;

public class NavBar extends ElementsContainer {

    @FindBy(css = ".navigation-menu-items > li.dropdown-submenu")
    private List<DropDownRootMenuItem> dropdownMenuList;

    @Step("Open menu by root name = {name}")
    public void openMenuByName(String rootMenuName) {
        List<DropDownRootMenuItem> tmp = dropdownMenuList.stream().filter(item -> item.getMenuTitle().equals(rootMenuName)).collect(Collectors.toList());
        assertThat(rootMenuName + " Not found in menu list", tmp.size() == 1);
        tmp.get(0).openMenu();
    }

    @Step("Open submenu by name = {name} in root menu list {rootMenuName}")
    public void openSubMenuByName(String rootMenuName, String name) {
        List<DropDownRootMenuItem> tmp = dropdownMenuList.stream().filter(item -> item.getMenuTitle().equals(rootMenuName)).collect(Collectors.toList());
        assertThat(rootMenuName + " Not found in menu list", tmp.size() == 1);
        tmp.get(0).openMenu();

        List<DropDownMenu> list = tmp.get(0).getDropDownRootMenu().stream().filter(item -> item.getItemTitle().equals(name)).collect(Collectors.toList());
        assertEquals("Expected menu item " + name +" not found, actual list " +
                tmp.get(0).getDropDownRootMenu().stream().map(DropDownMenu::getItemTitle).collect(Collectors.toList()), 1, list.size());
        list.get(0).openMenu();
    }

    @Step("Click submenu by name = {name} in root menu list {rootMenuName}")
    public void clickSubMenuByName(String rootMenuName, String name) {
        List<DropDownRootMenuItem> tmp = dropdownMenuList.stream().filter(item -> item.getMenuTitle().equals(rootMenuName)).collect(Collectors.toList());
        assertThat(rootMenuName + " Not found in menu list", tmp.size() == 1);
        tmp.get(0).openMenu();

        List<DropDownMenu> list = tmp.get(0).getDropDownRootMenu().stream().filter(item -> item.getItemTitle().equals(name)).collect(Collectors.toList());
        assertEquals("Expected menu item " + name +" not found, actual list " +
                tmp.get(0).getDropDownRootMenu().stream().map(DropDownMenu::getItemTitle).collect(Collectors.toList()), 1, list.size());
        list.get(0).clickMenu();
    }

    @Step("Check count of sub menu items under {rootMenuName}")
    public void checkCountItemsUnderRootMenu(NavBarMenu navBarMenu) {
        List<DropDownRootMenuItem> tmp = dropdownMenuList.stream().filter(item -> item.getMenuTitle().equals(navBarMenu.getRootItemTitle())).collect(Collectors.toList());
        assertThat(navBarMenu.getRootItemTitle() + " Not found in menu list", tmp.size() == 1);
        assertThat("Cont of drop down menu items not equal " + navBarMenu.getSubMenuCount(),
                dropdownMenuList.get(0).getDropDownRootMenu().size() != navBarMenu.getSubMenuCount());
    }

    @Step("Check title of sub menu items under {rootMenuName}")
    public void checkTitleItemsUnderRootMenu(NavBarMenu navBarMenu) {
        List<DropDownRootMenuItem> tmp = dropdownMenuList.stream().filter(item -> item.getMenuTitle().equals(navBarMenu.getRootItemTitle())).collect(Collectors.toList());
        assertThat(navBarMenu.getRootItemTitle() + " Not found in menu list", tmp.size() == 1);
        assertThat(dropdownMenuList.get(0).getDropDownRootMenu().stream().map(DropDownMenu::getItemTitle).collect(Collectors.toList()),
                hasItems(navBarMenu.getSubMenuItems().toArray()));
    }
}
