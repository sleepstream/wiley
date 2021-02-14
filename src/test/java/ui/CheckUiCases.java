package ui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Description;
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
        //dataProvider for test data for nav bar menu - add in json file data for another menu items to check them
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
        //additional test, not from specification, check filters field on search result page
        //possible to add in data provider information from DB about filters pre result etc
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
    @Description("Test check title of root menu in nav bar, check count of sub-menu items under root, " +
            " check titles of sub-menu items")
    void mainPageNavBarTest(NavBarMenu menuItem) {
        //open menu in nav bar by it's name
        mainPage.mainHeaderNavbar.openMenuByName(menuItem.getRootItemTitle());

        //check data on main nav menu bar
        mainPage.mainHeaderNavbar.checkCountItemsUnderRootMenu(menuItem);

        //check sub menu content
        mainPage.mainHeaderNavbar.checkTitleItemsUnderRootMenu(menuItem);
    }

    @Test(description = "Check search field and results")
    @Description("Check area with related content is displayed right under the search header")
    void enterDataInSearchFieldTest() {
        //enter data in search field without pressing search button
        mainPage.inputGroup.enterDataIntoInputField("java");

        //check related content is under search field, if i understand specification correct task 2
        mainPage.checkPositionOfRelatedContent();
    }

    @Test(description = "Check search results page and result items")
    @Description("Check search result page: • only titles containing “Java” are displayed\n" +
            "• there are 10 titles on the page \n" +
            "• each title has at least one “Add to Cart” button for E-Book/Print version and " +
            "“VIEW ON WILEY ONLINE LIBRARY” for O-BOOK version")
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
    @Description("Additional test not from specification, check filter panel on search results page")
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
    @Description("• Check “Education” header is displayed \n" +
            "• 13 items are displayed under “Subjects” on the left side of the screen and the texts are: \"Information & Library Science\"," +
            "\"Education & Public Policy\", \"K-12 General\", \"Higher Education General\", \"Vocational Technology\", " +
            "\"Conflict Resolution & Mediation (School settings)\", \"Curriculum Tools- General\", \"Special Educational Needs\", " +
            "\"Theory of Education\", \"Education Special Topics\", \"Educational Research & Statistics\", \"Literacy & Reading\", \"Classroom Management\"\n")
    void checkEducationPageTest() {
        //it's possible to add dataProvider to the test for check several pages, not only Education
        mainPage.mainHeaderNavbar.clickSubMenuByName("SUBJECTS", "Education");
        educationPage = page(EducationPage.class);

        educationPage.checkPageHeader();
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
