package api;

import api.body.DelayTestsObject;
import api.body.Pages;
import api.body.SearchBody;
import api.body.SuggestionsBody;
import api.requests.QueryParamBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.LogListener;
import org.apache.tika.Tika;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ui.base.NavBarMenu;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static api.enums.Entities.*;
import static api.requests.Requests.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners(LogListener.class)
public class ApiTests {

    @DataProvider
    public Object[][] getDelayData() {
        //boundary values
        //in dataProvider you can add additional test request
        List<DelayTestsObject> delayTestsObjectList = new ArrayList<>();
        delayTestsObjectList.add(new DelayTestsObject(-1, 0));
        delayTestsObjectList.add(new DelayTestsObject(0, 0));
        delayTestsObjectList.add(new DelayTestsObject(1, 1));
        delayTestsObjectList.add(new DelayTestsObject(5, 5));
        delayTestsObjectList.add(new DelayTestsObject(10, 10));
        delayTestsObjectList.add(new DelayTestsObject(11, 10));


        return delayTestsObjectList.stream()
                .map(item -> new Object[] { item })
                .toArray(Object[][]::new);
    }

    @Test(description = "Check search results")
    @Severity(SeverityLevel.CRITICAL)
    void checkSearchResults() {
        //check search request as asked in specification
        QueryParamBuilder queryParamBuilder = new QueryParamBuilder();
        queryParamBuilder.search("java");
        SearchBody searchBody = get(SEARCH, queryParamBuilder).as(SearchBody.class);

        assertThat("Actual count of pages not equals to expected", searchBody.getPages().size() == 4);
        assertThat("Actual count of suggestions not equals to expected", searchBody.getSuggestions().size() == 4);

        for(Pages page  : searchBody.getPages()) {
            assertTrue(page.getTitle().contains("Wiley"));
        }

        for(SuggestionsBody suggestionsBody : searchBody.getSuggestions()) {
            assertTrue(suggestionsBody.getTerm().contains("<span class=\"search-highlight\">java</span>"));
        }
    }

    @Test(description = "Check delay endpoint results",
            dataProvider = "getDelayData")
    @Severity(SeverityLevel.NORMAL)
    void checkDelayEndpoint(DelayTestsObject delayTestsObject) {
        //first request always return incorrect delay, no matter what delay you request - bug, i think
        int time = (int) (postHttpBin(DELAY, delayTestsObject.getDelayRequest().toString()).time()/1000);
        System.out.println("Delay is " + time);
        assertThat("delay is " + time + " expected 0", time == delayTestsObject.getDelayExpected());
    }

    @Test(description = "Check png endpoint results")
    @Severity(SeverityLevel.NORMAL)
    void checkPngEndpoint() {
        //check request response status, check content type,  check downloaded data - is it mime type image/png
        QueryParamBuilder queryParamBuilder = new QueryParamBuilder();
        byte[] image = getHttpBinPng(PNG, queryParamBuilder).asByteArray();
        //check file isn't empty
        assertTrue("Image is empty! ", image.length > 0);

        String contentType = new Tika().detect(image);

        //check file is png image
        assertThat("Downloaded file isn't png image", contentType.equals("image/png"));

    }
}