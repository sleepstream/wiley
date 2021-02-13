package api.requests.specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utils.TestProperties.getIntProperty;
import static utils.TestProperties.getTestProperty;

public class RequestSpecWiley {

    public RequestSpecification defaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(getTestProperty("baseUrl"))
                .setPort(getIntProperty("connectionPort"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.ANY)
                .addFilter(new AllureRestAssured())
                //to show logs in console
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public RequestSpecification defaultRequestSpecHttpBin() {
        return new RequestSpecBuilder()
                .setBaseUri(getTestProperty("baseUrlHttpBin"))
                .setPort(getIntProperty("connectionPort"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.ANY)
                .addFilter(new AllureRestAssured())
                //to show logs in console
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public RequestSpecification requestSpecHttpBinPng() {
        return new RequestSpecBuilder()
                .setBaseUri(getTestProperty("baseUrlHttpBin"))
                .setPort(getIntProperty("connectionPort"))
                .setAccept("image/png")
                .addFilter(new AllureRestAssured())
                //to show logs in console
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
