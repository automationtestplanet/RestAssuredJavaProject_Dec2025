package reqres.bdd;

import com.reqres.models.request.CreateUserDetails;
import com.reqres.models.response.*;
import com.reqres.services.UserService;
import com.reqres.utils.JsonUtils;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class UserServiceStepDef {

    RequestSpecification requestSpecification;
    JsonUtils jsonUtils = new JsonUtils();
    UserService userService = new UserService();
    AllUsersDetails allUsersDetails;
    SingleUserDetails singleUserDetails;
    CreatedUserDetails createdUserDetails;
    UpdatedUserDetails updatedUserDetails;
    String deleteResponse;


    @Given("the user service endpoint")
    public void theUserServiceEndpoint() {
        baseURI = "https://reqres.in";
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseURI).build()
                .header("x-api-key", "reqres_c4dd8dedafed4c89ba31edf4eb997630");
    }

    @When("the user sends a GET request to the user service endpoint")
    public void theUserSendsAGETRequestToTheUserServiceEndpoint() {
        allUsersDetails = userService.getAllUsers(requestSpecification, 200);
    }

    @Then("all the users should be returned")
    public void allTheUsersShouldBeReturned() {
        Assert.assertNotNull(allUsersDetails, "Get All Users response is null");
    }

    @Then("the response should have page number greater than {int}")
    public void theResponseShouldHavePageNumberGreaterThan(Integer pageNum) {
        Assert.assertTrue(allUsersDetails.getPage() > pageNum, "Page value is showing 0");

    }

    @Then("the response should have per page value greater than {int}")
    public void theResponseShouldHavePerPageValueGreaterThan(Integer perPageNum) {
        Assert.assertTrue(allUsersDetails.getPer_page() > perPageNum, "Par_Page value is showing 0");
    }

    @Then("the response should have total value greater than {int}")
    public void theResponseShouldHaveTotalValueGreaterThan(Integer totalNum) {
        Assert.assertTrue(allUsersDetails.getTotal() > totalNum, "Total value is showing 0");
    }

    @Then("the response should have total pages value greater than {int}")
    public void theResponseShouldHaveTotalPagesValueGreaterThan(Integer totalPagesNum) {
        Assert.assertTrue(allUsersDetails.getTotal_pages() > totalPagesNum, "Total_Pages value is showing 0");
    }

    @Then("the each data id should not be null")
    public void theEachDataIdShouldNotBeNull() {
        List<UserData> listOfData = allUsersDetails.getData();
        for (UserData eachUserData : listOfData) {
            Assert.assertNotNull(eachUserData.getId(), "Id is null");
        }
    }

    @When("the user sends a GET request to the user service endpoint with query parameter {string} and value {string}")
    public void theUserSendsAGETRequestToTheUserServiceEndpointWithQueryParameterAndValue(String queryParamName, String queryParamValue) {
        Map<String, String> queryParams = Map.of(queryParamName, queryParamValue);
        allUsersDetails = userService.getUsersByPageName(requestSpecification, queryParams, 200);
    }

    @Then("the response should have page number {string}")
    public void theResponseShouldHavePageNumber(String pageNum) {
        Assert.assertEquals(allUsersDetails.getPage(), Integer.parseInt(pageNum), "Page value is showing incorrect");
    }

    @When("the user sends a GET request to the user service endpoint with path parameter {string} and value {string}")
    public void theUserSendsAGETRequestToTheUserServiceEndpointWithPathParameterAndValue(String pathParamName, String pathParamValue) {
        Map<String, String> pathParams = Map.of(pathParamName, pathParamValue);
        singleUserDetails = userService.getUsersById(requestSpecification, pathParams, 200);

    }

    @Then("the user with id {string} should be returned")
    public void theUserWithIdShouldBeReturned(String expId) {
        Assert.assertEquals(singleUserDetails.getData().getId(), Integer.parseInt(expId), "Id value is showing incorrect");
    }

    @When("the user sends a POST request to the user service endpoint with name {string} and job {string}")
    public void theUserSendsAPOSTRequestToTheUserServiceEndpointWithNameAndJob(String userName, String job) {
        String requestBodyPath = System.getProperty("user.dir") + "//src//test//resources//TestData//CreateUser2.json";
        String requestBody = jsonUtils.readDataFromJsonAsString(requestBodyPath);
        requestBody = String.format(requestBody, userName, job);
        createdUserDetails = userService.createUser(requestSpecification, requestBody, 201);


    }

    @Then("the new user should be created")
    public void theNewUserShouldBeCreated() {
        Assert.assertNotNull(createdUserDetails, "Create User response is null");
    }

    @Then("the response have the id should not be null")
    public void theResponseHaveTheIdShouldNotBeNull() {
        Assert.assertNotNull(createdUserDetails.getId(), "Id is null");
    }

    @Then("the response should have name {string}")
    public void theResponseShouldHaveName(String name) {
        Assert.assertEquals(createdUserDetails.getName(), name, "Name is showing incorrect");
    }

    @Then("the response should have job {string}")
    public void theResponseShouldHaveJob(String job) {
        Assert.assertEquals(createdUserDetails.getJob(), job, "Job is showing incorrect");
    }

    @When("the user sends a POST request to the user service endpoint with {string} and {string}")
    public void theUserSendsAPOSTRequestToTheUserServiceEndpointWithAnd(String name, String job) {
        CreateUserDetails createUserDetails = new CreateUserDetails();
        createUserDetails.setName(name);
        createUserDetails.setJob(job);
        createdUserDetails = userService.createUser(requestSpecification, createUserDetails, 201);
    }

    @When("the user sends a PUT request to the user service endpoint with path parameter {string} and value {string} and name {string} and job {string}")
    public void theUserSendsAPUTRequestToTheUserServiceEndpointWithPathParameterAndValueAndNameAndJob(String pathParamName, String pathParamVal, String name, String job) {
        CreateUserDetails createUserDetails = new CreateUserDetails();
        createUserDetails.setName(name);
        createUserDetails.setJob(job);
        Map<String, String> pathParams = Map.of(pathParamName, pathParamVal);
        updatedUserDetails = userService.updateUserDetails(requestSpecification, pathParams, createUserDetails, 200);
    }

    @Then("the response should have name {string} and job {string}")
    public void theResponseShouldHaveNameAndJob(String name, String job) {
        Assert.assertEquals(updatedUserDetails.getName(), name, "Name is showing incorrect");
        Assert.assertEquals(updatedUserDetails.getJob(), job, "Job is showing incorrect");
    }

    @When("the user sends a DELETE request to the user service endpoint with path parameter {string} and value {string}")
    public void theUserSendsADELETERequestToTheUserServiceEndpointWithPathParameterAndValue(String pathParamName, String pathParamVal) {
        Map<String, String> pathParams = Map.of(pathParamName, pathParamVal);
        deleteResponse = userService.deleteUser(requestSpecification, pathParams, 204);
    }
    @Then("the response should be empty")
    public void theResponseShouldBeEmpty() {
        Assert.assertTrue(deleteResponse.isEmpty(), "Delete response is not empty");
    }


}
