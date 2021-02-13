package api.requests.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class ResponseSpecWiley {

    public static ResponseSpecification successResponseSpec = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public static ResponseSpecification successResponseSpecPng = new ResponseSpecBuilder()
            .expectContentType("image/png")
            .expectStatusCode(HttpStatus.SC_OK)
            .build();
}
