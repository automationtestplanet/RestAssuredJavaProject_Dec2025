package com.reqres.services;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class RestAssuredService {

    public ValidatableResponse get(RequestSpecification rs, String endPoint, int expectedStatusCode) {
        return given(rs)
                .when().get(endPoint)
                .then().statusCode(expectedStatusCode).log().all();
    }

    public ValidatableResponse getWithQueryParams(RequestSpecification rs, String endPoint, Map<String, String> queryParams, int expectedStatusCode) {
        return given(rs)
                .queryParams(queryParams)
                .when().get(endPoint)
                .then().statusCode(expectedStatusCode).log().all();
    }

    public ValidatableResponse getWithPathParams(RequestSpecification rs, String endPoint, Map<String, String> pathParams, int expectedStatusCode) {
        return given(rs)
                .pathParams(pathParams)
                .when().get(endPoint)
                .then().statusCode(expectedStatusCode).log().all();
    }

    public ValidatableResponse post(RequestSpecification rs, String endPoint, Map<String,String> headers,Object requestBody, int expectedStatusCode) {
        return given(rs)
                .headers(headers)
                .body(requestBody)
                .when().post(endPoint)
                .then().statusCode(expectedStatusCode).log().all();
    }

    public ValidatableResponse put(RequestSpecification rs, String endPoint, Map<String,String> headers, Object requestBody, int expectedStatusCode) {
        return given(rs)
                .headers(headers)
                .body(requestBody)
                .when().put(endPoint)
                .then().statusCode(expectedStatusCode).log().all();
    }

    public ValidatableResponse putWithPathParam(RequestSpecification rs, String endPoint, Map<String,String> headers,Map<String, String> pathParams, Object requestBody, int expectedStatusCode) {
        return given(rs)
                .headers(headers)
                .pathParams(pathParams)
                .body(requestBody)
                .when().put(endPoint)
                .then().statusCode(expectedStatusCode).log().all();
    }

    public ValidatableResponse patchWithPathParam(RequestSpecification rs, String endPoint, Map<String,String> headers, Map<String, String> pathParams, Object requestBody, int expectedStatusCode) {
        return given(rs)
                .headers(headers)
                .pathParams(pathParams)
                .body(requestBody)
                .when().patch(endPoint)
                .then().statusCode(expectedStatusCode).log().all();
    }

    public ValidatableResponse deleteWithPathParam(RequestSpecification rs, String endPoint, Map<String, String> pathParams, int expectedStatusCode) {
        return given(rs)
                .pathParams(pathParams)
                .when().delete(endPoint)
                .then().statusCode(expectedStatusCode).log().all();
    }
}
