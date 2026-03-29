package com.reqres;

import com.reqres.models.request.CreateUserDetails;
import com.reqres.models.response.AllUsersDetails;
import com.reqres.models.response.UserData;
import com.reqres.services.UserService;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class UserServiceTests extends BaseTest {

    UserService userService = new UserService();

    @Test
    public void getAllUsersTest() {
        AllUsersDetails allUsersDetails = userService.getAllUsers(requestSpecification, 200);
        Assert.assertTrue(allUsersDetails.getPage() > 0, "Page value is showing 0");
        Assert.assertTrue(allUsersDetails.getPer_page() > 1, "Par_Page value is showing 0");
        Assert.assertTrue(allUsersDetails.getTotal() > 1, "Total value is showing 0");
        Assert.assertTrue(allUsersDetails.getTotal_pages() > 1, "Total_Pages value is showing 0");
        List<UserData> listOfData = allUsersDetails.getData();
        for (UserData eachUserData : listOfData) {
            Assert.assertNotNull(eachUserData.getId(), "Id is null");
        }
    }

    @Test
    public void getAllUsersByPageNumTest() {
        Map<String, String> queryParams = Map.of("page", "2");
        AllUsersDetails allUsersDetails = userService.getUsersByPageName(requestSpecification, queryParams, 200);
        Assert.assertEquals(allUsersDetails.getPage(), Integer.parseInt(queryParams.get("page")), "Page value is showing incorrect");
        List<UserData> listOfData = allUsersDetails.getData();
        for (UserData eachUserData : listOfData) {
            Assert.assertNotNull(eachUserData.getId(), "Id is null");
        }
    }

    @Test
    public void getUserByIdTest() {
        Map<String, String> pathParams = Map.of("id", "2");
        var singleUserDetails = userService.getUsersById(requestSpecification, pathParams, 200);
        Assert.assertEquals(singleUserDetails.getData().getId(), Integer.parseInt(pathParams.get("id")), "Id value is showing incorrect");
    }

    @Test
    public void createUserTest() {
        String requestBody = "{\n" +
                "    \"name\": \"AutomationTestPlanet\",\n" +
                "    \"job\": \"Trainings\"\n" +
                "}";
        var createdUserDetails = userService.createUser(requestSpecification, requestBody, 201);
        Assert.assertNotNull(createdUserDetails.getId(), "Id is null");
        Assert.assertTrue(requestBody.contains(createdUserDetails.getName()), "Name is showing incorrect");
        Assert.assertTrue(requestBody.contains(createdUserDetails.getJob()), "Job is showing incorrect");
    }

    @Test
    public void createUserWithJsonFileTest() {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser.json";
        String requestBody = jsonUtils.readDataFromJsonAsString(requestBodyPath);
        var createdUserDetails = userService.createUser(requestSpecification, requestBody, 201);
        Assert.assertNotNull(createdUserDetails.getId(), "Id is null");
        Assert.assertTrue(requestBody.contains(createdUserDetails.getName()), "Name is showing incorrect");
        Assert.assertTrue(requestBody.contains(createdUserDetails.getJob()), "Job is showing incorrect");
    }

    @Test
    public void createUserWithJsonFileAndJsonObjectTest() {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser.json";
        JSONObject requestBody = jsonUtils.readDataFromJsonAsJsonObject(requestBodyPath);
        var createdUserDetails = userService.createUser(requestSpecification, requestBody, 201);
        Assert.assertNotNull(createdUserDetails.getId(), "Id is null");
        Assert.assertEquals(createdUserDetails.getName(), requestBody.get("name").toString(), "Name is showing incorrect");
        Assert.assertEquals(createdUserDetails.getJob(), requestBody.get("job").toString(), "Job is showing incorrect");
    }

    @Test
    public void createUserWithJsonFileAndUserModelClassTest() {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser.json";
        CreateUserDetails requestBody = jsonUtils.readDataFromJsonAsUserDetails(requestBodyPath);

        var createdUserDetails = userService.createUser(requestSpecification, requestBody, 201);
        Assert.assertNotNull(createdUserDetails.getId(), "Id is null");
        Assert.assertEquals(createdUserDetails.getName(), requestBody.getName(), "Name is showing incorrect");
        Assert.assertEquals(createdUserDetails.getJob(), requestBody.getJob(), "Job is showing incorrect");
    }

    @Test
    public void createUserWithUserModelClassSetterMethodsTest() {
        CreateUserDetails createUserDetails = new CreateUserDetails();
        createUserDetails.setName("User1");
        createUserDetails.setJob("Test Engineer1");
        var createdUserDetails = userService.createUser(requestSpecification, createUserDetails, 201);
        Assert.assertNotNull(createdUserDetails.getId(), "Id is null");
        Assert.assertEquals(createdUserDetails.getName(), createUserDetails.getName(), "Name is showing incorrect");
        Assert.assertEquals(createdUserDetails.getJob(), createUserDetails.getJob(), "Job is showing incorrect");
    }

    @Test
    public void createUserWithJsonFileWithParametersTest() {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser2.json";
        String requestBody = jsonUtils.readDataFromJsonAsString(requestBodyPath);
        requestBody = String.format(requestBody, "Krish", "Quality Engineer");

        var createdUserDetails = userService.createUser(requestSpecification, requestBody, 201);
        Assert.assertNotNull(createdUserDetails.getId(), "Id is null");
        Assert.assertTrue(requestBody.contains(createdUserDetails.getName()), "Name is showing incorrect");
        Assert.assertTrue(requestBody.contains(createdUserDetails.getJob()), "Job is showing incorrect");
    }

    @Test
    public void updateUserTest() {
        CreateUserDetails createUserDetails = new CreateUserDetails();
        createUserDetails.setName("JanetTest1");
        createUserDetails.setJob("QA Engineer");
        Map<String, String> pathParams = Map.of("id", "2");
        var updatedUserDetails = userService.updateUserDetails(requestSpecification, pathParams, createUserDetails, 200);
        Assert.assertEquals(updatedUserDetails.getName(), createUserDetails.getName(), "Name is showing incorrect");
        Assert.assertEquals(updatedUserDetails.getJob(), createUserDetails.getJob(), "Job is showing incorrect");
    }

    @Test
    public void partialUpdateUserTest() {
        CreateUserDetails createUserDetails = new CreateUserDetails();
        createUserDetails.setName("JanetTest2");
        Map<String, String> pathParams = Map.of("id", "2");
        var updatedUserDetails = userService.updatePartialUserDetails(requestSpecification, pathParams, createUserDetails, 200);
        Assert.assertEquals(updatedUserDetails.getName(), createUserDetails.getName(), "Name is showing incorrect");
    }

    @Test
    public void deleteUserTest() {
        Map<String, String> pathParams = Map.of("id", "2");
        String response = userService.deleteUser(requestSpecification, pathParams, 204);
        Assert.assertTrue(response.isEmpty(), "Delete response is not empty");

    }
}
