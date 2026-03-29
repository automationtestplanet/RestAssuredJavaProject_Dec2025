package com.reqres.services;

import com.reqres.models.response.AllUsersDetails;
import com.reqres.models.response.CreatedUserDetails;
import com.reqres.models.response.SingleUserDetails;
import com.reqres.models.response.UpdatedUserDetails;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class UserService extends RestAssuredService {
    public static String USERS_SERVICE_ENDPOINT = "/api/users";

    public AllUsersDetails getAllUsers(RequestSpecification rs, int statusCode) {
        return get(rs, USERS_SERVICE_ENDPOINT, statusCode).extract().as(AllUsersDetails.class);
    }

    public AllUsersDetails getUsersByPageName(RequestSpecification rs, Map<String, String> queryParams, int statusCode) {
        return getWithQueryParams(rs, USERS_SERVICE_ENDPOINT, queryParams, statusCode).extract().as(AllUsersDetails.class);
    }

    public SingleUserDetails getUsersById(RequestSpecification rs, Map<String, String> pathParam, int statusCode) {
        String pathParamName = pathParam.keySet().stream().findFirst().orElse("id");
        return getWithPathParams(rs, USERS_SERVICE_ENDPOINT + "/{" + pathParamName + "}", pathParam, statusCode).extract().as(SingleUserDetails.class);
    }

    public CreatedUserDetails createUser(RequestSpecification rs, Object requestBody, int statusCode) {
        Map<String, String> headers =
                Map.of("Content-Type", "application/json", "Accept", "*/*");
        return post(rs, USERS_SERVICE_ENDPOINT,headers,requestBody, statusCode).extract().as(CreatedUserDetails.class);

    }

    public UpdatedUserDetails updateUserDetails(RequestSpecification rs, Map<String, String> pathParam ,Object requestBody, int statusCode) {
        Map<String, String> headers =
                Map.of("Content-Type", "application/json", "Accept", "*/*");
        String pathParamName = pathParam.keySet().stream().findFirst().orElse("id");
        return putWithPathParam(rs, USERS_SERVICE_ENDPOINT + "/{" + pathParamName + "}",headers,pathParam, requestBody, statusCode).extract().as(UpdatedUserDetails.class);

    }

    public UpdatedUserDetails updatePartialUserDetails(RequestSpecification rs, Map<String, String> pathParam ,Object requestBody, int statusCode) {
        Map<String, String> headers =
                Map.of("Content-Type", "application/json", "Accept", "*/*");
        String pathParamName = pathParam.keySet().stream().findFirst().orElse("id");
        return patchWithPathParam(rs, USERS_SERVICE_ENDPOINT + "/{" + pathParamName + "}",headers,pathParam, requestBody, statusCode).extract().as(UpdatedUserDetails.class);

    }

    public String deleteUser(RequestSpecification rs, Map<String, String> pathParam, int statusCode) {
        String pathParamName = pathParam.keySet().stream().findFirst().orElse("id");
        return deleteWithPathParam(rs, USERS_SERVICE_ENDPOINT + "/{" + pathParamName + "}",pathParam, statusCode).extract().body().asString();
    }
}
