package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ProductItem extends ElementsContainer {

    @FindBy(css = "#eBundlePlpTabMainTabPanel > .eBundlePlpTab")
    private PanelTypeSwitcher panelTypeSwitcher;

    @FindBy(css = ".product-content > h3.product-title")
    private SelenideElement productTitle;

    @FindBy(css = ".product-content > h3.product-title > a > span.search-highlight")
    private SelenideElement highLightedTitle;

    @FindBy(css = ".tab-content")
    private TabContent tabContent;

    @Step("Check item title contains highlighted search string")
    public void checkItemTitle(String search) {
        productTitle.scrollIntoView(false);
        assertEquals("Title should has highlighted search string", search,
                highLightedTitle.getText().trim().toLowerCase());
    }

    @Step("Check action button for each type")
    public void checkActionButtons() {
        int i = 0;
        for(Presentation presentation : panelTypeSwitcher.getPresentationList()) {
            tabContent.getTabPanelList().get(i).getActionItemContainer().scrollIntoView(false);
            presentation.switchTab();
            switch (presentation.getTitleOfCurrentTab()) {
                case "E-BOOK":
                    assertEquals("E-BOOK should has button \"Add to Cart\", product:" + productTitle.getText(),
                            "ADD TO CART",
                            tabContent.getTabPanelList().get(i).getActionItemButton().getButtonTitle().trim());
                    break;
                case "PRINT":
                    assertEquals("PRINT should has button \"Add to Cart\", product:" + productTitle.getText(),
                         "ADD TO CART",
                            tabContent.getTabPanelList().get(i).getActionItemButton().getButtonTitle().trim());
                    break;
                case "O-BOOK":
                    assertEquals("O-BOOK should has button \"VIEW ON WILEY ONLINE LIBRARY\", product:" + productTitle.getText(),
                            "VIEW ON WILEY ONLINE LIBRARY",
                            tabContent.getTabPanelList().get(i).getActionItemButton().getButtonTitle().trim());
                    break;
            }
            i+=1;
        }
    }
}
