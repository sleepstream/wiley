package ui.elements.pageFragments;

import com.codeborne.selenide.ElementsContainer;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ProductsList extends ElementsContainer {

    @FindBy(css = ".product-item")
    private List<ProductItem> productItemList;

    @Step("Check product items data")
    public void checkProductItemsData() {
        assertTrue("Count of product on page not equal to 10", productItemList.size() == 10);
        for(ProductItem productItem : productItemList) {
            productItem.checkItemTitle("java");
        }

        for(ProductItem productItem : productItemList) {
            productItem.checkActionButtons();
        }
    }
}
