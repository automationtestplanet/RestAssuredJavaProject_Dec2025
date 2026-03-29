package com.reqres;

import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reqres.models.response.CreatedUserDetails;
import com.reqres.models.request.CreateUserDetails;
import com.reqres.utils.JsonUtils;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;


public class ReqResApiTests {

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

    @Test
    public void getAllUsers() {
        String response = given().header("x-api-key", "reqres_c4dd8dedafed4c89ba31edf4eb997630")
                .when().get("https://reqres.in/api/users")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void getAllUsersByPageNum() {
        String response = given().header("x-api-key", "reqres_c4dd8dedafed4c89ba31edf4eb997630").queryParam("page", "2")
                .when().get("https://reqres.in/api/users")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void getAllUsersWithoutBaseUrl() {
        String response = given(requestSpecification)
                .when().get("/api/users")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void getAllUsersByPageNumWithoutBaseUrl() {
        String response = given(requestSpecification).queryParam("page", "2")
                .when().get("/api/users")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void getUserByIdWithoutBaseUrl() {
        String response = given(requestSpecification).pathParam("id", "2")
                .when().get("/api/users/{id}")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void createUser() {
        String requestBody = "{\n" +
                "    \"name\": \"AutomationTestPlanet\",\n" +
                "    \"job\": \"Trainings\"\n" +
                "}";

        String response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void createUserWithJsonFile() {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser.json";
        String requestBody = jsonUtils.readDataFromJsonAsString(requestBodyPath);
        String response = given(requestSpecification)
                .header("Accept", "*/*")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void createUserWithJsonFileAndJsonObject() {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser.json";
        JSONObject requestBody = jsonUtils.readDataFromJsonAsJsonObject(requestBodyPath);
        String response = given(requestSpecification)
                .header("Accept", "*/*")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void createUserWithJsonFileAndUserModelClass() {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser.json";
        CreateUserDetails requestBody = jsonUtils.readDataFromJsonAsUserDetails(requestBodyPath);

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "*/*");

        String response = given(requestSpecification)
                .headers(headers)
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void createUserWithUserModelClassSetterMethods() {
        CreateUserDetails createUserDetails = new CreateUserDetails();
        createUserDetails.setName("User1");
        createUserDetails.setJob("Test Engineer1");

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "*/*");

        String response = given(requestSpecification)
                .headers(headers)
                .body(createUserDetails)
                .when().post("/api/users")
                .then().statusCode(201).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void createUserWithJsonFileWirthParameters() {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser2.json";
        String requestBody = jsonUtils.readDataFromJsonAsString(requestBodyPath);
        requestBody = String.format(requestBody, "Krish", "Quality Engineer");
        System.out.println(requestBody);
        String response = given(requestSpecification)
                .header("Accept", "*/*")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void updateUser() {
        CreateUserDetails createUserDetails = new CreateUserDetails();
        createUserDetails.setName("JanetTest1");
        createUserDetails.setJob("QA Engineer");

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "*/*");

        Map<String, String> pathParams = new LinkedHashMap<>();
        pathParams.put("id", "2");

        String response = given(requestSpecification)
                .pathParams(pathParams)
                .headers(headers)
                .body(createUserDetails)
                .when().put("/api/users/{id}")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void partialUpdateUser() {
        CreateUserDetails createUserDetails = new CreateUserDetails();
        createUserDetails.setName("JanetTest2");

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "*/*");

        Map<String, String> pathParams = new LinkedHashMap<>();
        pathParams.put("id", "2");

        String response = given(requestSpecification)
                .pathParams(pathParams)
                .headers(headers)
                .body(createUserDetails)
                .when().patch("/api/users/{id}")
                .then().statusCode(200).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void deleteUser() {
        Map<String, String> pathParams = new LinkedHashMap<>();
        pathParams.put("id", "2");

        String response = given(requestSpecification)
                .pathParams(pathParams)
                .when().delete("/api/users/{id}")
                .then().statusCode(204).extract().body().asString();

        System.out.println(response);
    }

    @Test
    public void createUserAndValidateResponse1() throws JsonProcessingException {
        String requestBody = "{\n" +
                "    \"name\": \"AutomationTestPlanet\",\n" +
                "    \"job\": \"Trainings\"\n" +
                "}";

        Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201).extract().response().thenReturn();

        ObjectMapper objMapper = new ObjectMapper();
        CreatedUserDetails createdUserDetails = objMapper.readValue(response.body().asString(), CreatedUserDetails.class);

        Assert.assertEquals(createdUserDetails.getName(), "AutomationTestPlanet", "Name is not Matching");
        Assert.assertEquals(createdUserDetails.getJob(), "Trainings", "Name is not Matching");
        Assert.assertNotNull(createdUserDetails.getId());
    }

    @Test
    public void createUserAndValidateResponse2() throws JsonProcessingException {
        String requestBody = "{\n" +
                "    \"name\": \"AutomationTestPlanet\",\n" +
                "    \"job\": \"Trainings\"\n" +
                "}";

        CreatedUserDetails createdUserDetails = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201).extract().response().as(CreatedUserDetails.class);

        Assert.assertEquals(createdUserDetails.getName(), "AutomationTestPlanet", "Name is not Matching");
        Assert.assertEquals(createdUserDetails.getJob(), "Trainings", "Name is not Matching");
        Assert.assertNotNull(createdUserDetails.getId());
    }

    @Test
    public void createUserAndValidateResponse3() throws JsonProcessingException {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser.json";
        CreateUserDetails requestBody = jsonUtils.readDataFromJsonAsUserDetails(requestBodyPath);

        CreatedUserDetails createdUserDetails = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body(requestBody)
                .when().post("/api/users")
                .then().statusCode(201).extract().response().as(CreatedUserDetails.class);

        Assert.assertEquals(createdUserDetails.getName(), requestBody.getName(), "Name is not Matching");
        Assert.assertEquals(createdUserDetails.getJob(), requestBody.getJob(), "Job is not Matching");
        Assert.assertNotNull(createdUserDetails.getId());
    }
}
