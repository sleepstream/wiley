package api.requests;

import api.enums.Entities;
import api.requests.specifications.RequestSpecWiley;
import api.requests.specifications.ResponseSpecWiley;
import io.restassured.response.Response;

import static api.requests.specifications.ResponseSpecWiley.successResponseSpec;
import static api.requests.specifications.ResponseSpecWiley.successResponseSpecPng;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class Requests {

    public static Response get(Entities entity, QueryParamBuilder query) {
        return given()
                        .spec(new RequestSpecWiley().defaultRequestSpec().basePath(entity.endpoint.endpoint))
                        .queryParams(query.params)
                .when()
                        .get()
                .then()
                        .spec(successResponseSpec)
                        .extract()
                        .response();
    }

    public static Response postHttpBin(Entities entity, String delay) {
        return given()
                    .spec(new RequestSpecWiley().defaultRequestSpecHttpBin().basePath(entity.endpoint.endpoint
                    + (delay != null ? "/" + delay : "")))
                .when()
                    .get()
                .then()
                    .spec(successResponseSpec)
                    .extract()
                    .response();
    }

    public static Response getHttpBinPng(Entities entity, QueryParamBuilder query) {
        return given()
                .spec(new RequestSpecWiley().requestSpecHttpBinPng().basePath(entity.endpoint.endpoint))
                .queryParams(query.params)
                .when()
                .get()
                .then()
                .spec(successResponseSpecPng)
                .extract()
                .response();
    }
}
