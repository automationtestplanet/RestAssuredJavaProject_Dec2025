package com.reqres;

import com.reqres.utils.JsonUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    JsonUtils jsonUtils = new JsonUtils();


    @BeforeTest
    public void beforeTest() {
        baseURI = "https://reqres.in";
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseURI).build()
                .header("x-api-key", "reqres_c4dd8dedafed4c89ba31edf4eb997630");

    }
}
