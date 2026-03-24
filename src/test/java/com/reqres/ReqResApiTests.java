package com.reqres;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;

import io.restassured.builder.ResponseBuilder;
import io.restassured.specification.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ReqResApiTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeTest
    public void beforeTest(){
        baseURI = "https://reqres.in";
        requestSpecification = new RequestSpecBuilder().setBaseUri(baseURI).build().header("x-api-key","reqres_c4dd8dedafed4c89ba31edf4eb997630");

    }

    @Test
    public void getAllUsers(){
        String response = given().header("x-api-key","reqres_c4dd8dedafed4c89ba31edf4eb997630")
                .when().get("https://reqres.in/api/users")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void getAllUsersByPageNum(){
        String response = given().header("x-api-key","reqres_c4dd8dedafed4c89ba31edf4eb997630").queryParam("page","2")
                .when().get("https://reqres.in/api/users")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void getAllUsersWithoutBaseUrl(){
        String response = given(requestSpecification)
                .when().get("/api/users")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void getAllUsersByPageNumWithoutBaseUrl(){
        String response = given(requestSpecification).queryParam("page","2")
                .when().get("/api/users")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void getUserByIdWithoutBaseUrl(){
        String response = given(requestSpecification).pathParam("id","2")
                .when().get("/api/users/{id}")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void createUser(){

    }

    @Test
    public void updateUser(){

    }

    @Test
    public void partialUpdateUser(){

    }

    @Test
    public void deleteUser(){

    }
}
