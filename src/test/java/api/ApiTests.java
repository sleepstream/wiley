package api;

import api.body.Pages;
import api.body.SearchBody;
import api.body.SuggestionsBody;
import api.requests.QueryParamBuilder;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.LogListener;
import org.apache.tika.Tika;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Date;

import static api.enums.Entities.*;
import static api.requests.Requests.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners(LogListener.class)
public class ApiTests {

    @Test(description = "Check search results")
    @Severity(SeverityLevel.CRITICAL)
    void checkSearchResults() {
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

    @Test(description = "Check delay endpoint results")
    @Severity(SeverityLevel.NORMAL)
    void checkDelayEndpoint() {
        //boundary values
        //first request always incorrect
        postHttpBin(DELAY, "0");

        int time = (int) (postHttpBin(DELAY, "-1").time()/1000);
        System.out.println("Delay is " + time);
        assertThat("delay is " + time + " expected 0", time == 0);

        time = (int) (postHttpBin(DELAY, "0").time()/1000);
        System.out.println("Delay is " + time);
        assertThat("delay is " + time + " expected 0", time == 0);

        time = (int) (postHttpBin(DELAY, "1").time()/1000);
        System.out.println("Delay is " + time);
        assertThat("delay is " + time + " expected 1", time == 1);


        time = (int) (postHttpBin(DELAY, "5").time()/1000);
        System.out.println("Delay is " + time);
        assertThat("delay is " + time + " expected 5", time == 5);

        time = (int) (postHttpBin(DELAY, "10").time()/1000);
        System.out.println("Delay is " + time);
        assertThat("delay is " + time + " expected 10", time == 10);

        time = (int) (postHttpBin(DELAY, "11").time()/1000);
        System.out.println("Delay is " + time);
        assertThat("delay is " + time + " expected 11", time == 10);
    }

    @Test(description = "Check png endpoint results")
    @Severity(SeverityLevel.NORMAL)
    void checkPngEndpoint() {
        QueryParamBuilder queryParamBuilder = new QueryParamBuilder();
        byte[] image = getHttpBinPng(PNG, queryParamBuilder).asByteArray();
        //check file isn't empty
        assertTrue("Image is empty! ", image.length > 0);

        String contentType = new Tika().detect(image);

        //check file is png image
        assertThat("Downloaded file isn't png image", contentType.equals("image/png"));

    }
}