package ui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ui.base.NavBarMenu;
import ui.base.SearchFilter;
import ui.base.SelenideBase;
import ui.elements.pages.EducationPage;
import ui.elements.pages.MainPage;
import ui.elements.pages.ModalDialog;
import ui.elements.pages.SearchPage;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static utils.TestProperties.getTestProperty;

public class CheckUiCases extends SelenideBase {

    private MainPage mainPage;
    private SearchPage searchPage;
    private ModalDialog modalDialog;
    private EducationPage educationPage;

    @BeforeMethod
    void beforeMethod() {
        open(getTestProperty("baseUrl"));
        mainPage = page(MainPage.class);
    }

    @AfterMethod
    void afterMethod() {
        closeWindow();
    }

    @DataProvider
    public Object[][] getMenuFromJson() throws Exception {
        JsonElement jsonData = new JsonParser()
                .parse(new FileReader("src/test/resources/dataProvider/navBarMenu.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("menuItems");
        List<NavBarMenu> menuItems = new Gson().fromJson(dataSet, new TypeToken<List<NavBarMenu>>() {
        }.getType());
        return menuItems.stream()
                .map(item -> new Object[] { item })
                .toArray(Object[][]::new);
    }

    @DataProvider
    public Object[][] getFiltersFromJson() throws Exception {
        JsonElement jsonData = new JsonParser()
                .parse(new FileReader("src/test/resources/dataProvider/searchFilters.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("searchItems");
        List<SearchFilter> filterItems = new Gson().fromJson(dataSet, new TypeToken<List<SearchFilter>>() {
        }.getType());
        return filterItems.stream()
                .map(item -> new Object[] { item })
                .toArray(Object[][]::new);
    }

    @Test(description = "Check nav bar items",dataProvider = "getMenuFromJson")
    void mainPageNavBarTest(NavBarMenu menuItem) {
        mainPage.mainHeaderNavbar.openMenuByName(menuItem.getRootItemTitle());

        //check data on main nav menu bar
        mainPage.mainHeaderNavbar.checkCountItemsUnderRootMenu(menuItem);

        //check sub menu content
        mainPage.mainHeaderNavbar.checkTitleItemsUnderRootMenu(menuItem);
    }

    @Test(description = "Check search field and results")
    void enterDataInSearchFieldTest() {
        mainPage.inputGroup.enterDataIntoInputField("java");
        mainPage.checkPositionOfRelatedContent();
    }

    @Test(description = "Check search results page and result items")
    void checkSearchResultsTest() {
        mainPage.inputGroup.enterDataIntoInputField("java");
        mainPage.inputGroup.clickSearchButton();

        modalDialog = page(ModalDialog.class);
        searchPage = page(SearchPage.class);

        if(modalDialog.getModalDialogWindow().isDisplayed()) {
            modalDialog.pressYesButton();
        }
        searchPage.checkSearchPageData();
    }

    @Test(description = "Check search filters items",
          dataProvider = "getFiltersFromJson")
    void checkFilterItemsTest(SearchFilter searchFilter) {
        mainPage.inputGroup.enterDataIntoInputField("java");
        mainPage.inputGroup.clickSearchButton();

        modalDialog = page(ModalDialog.class);
        searchPage = page(SearchPage.class);

        if(modalDialog.getModalDialogWindow().isDisplayed()) {
            modalDialog.pressYesButton();
        }

        searchPage.checkFilterItemsData(searchFilter);
    }

    @Test(description = "Check education page data")
    void checkEducationPageTest() {
        mainPage.mainHeaderNavbar.clickSubMenuByName("SUBJECTS", "Education");
        educationPage = page(EducationPage.class);
        List<String> expectedLinks = Arrays.asList("Information & Library Science",
                "Education & Public Policy",
                "K-12 General",
                "Higher Education General",
                "Vocational Technology",
                "Conflict Resolution & Mediation (School settings)",
                "Curriculum Tools- General",
                "Special Educational Needs",
                "Theory of Education",
                "Education Special Topics",
                "Educational Research & Statistics",
                "Literacy & Reading",
                "Classroom Management");

        educationPage.checkPanelLinks(expectedLinks);
    }
}
