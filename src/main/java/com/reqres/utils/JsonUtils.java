package com.reqres.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reqres.models.request.CreateUserDetails;
import org.json.simple.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    public JSONObject readDataFromJsonAsJsonObject(String jsonFilePath) {

        JSONObject jObject = null;
        try {
            jObject = new ObjectMapper().readValue(Paths.get(jsonFilePath).toFile(), JSONObject.class);
        } catch (Exception e) {
            System.out.println("Exception Occurred while reading the data from JSON file: " + e.getMessage());
        }
        return jObject;
    }

    public CreateUserDetails readDataFromJsonAsUserDetails(String jsonFilePath) {
        CreateUserDetails createUserDetails = null;
        try {
            createUserDetails = new ObjectMapper().readValue(Paths.get(jsonFilePath).toFile(), CreateUserDetails.class);
        } catch (Exception e) {
            System.out.println("Exception Occurred while reading the data from JSON file: " + e.getMessage());
        }
        return createUserDetails;
    }

    public String readDataFromJsonAsString(String filePath) {
        String fileContent = null;
        try {
            fileContent = Files.readString(Paths.get(filePath));
        } catch (Exception e) {
            System.out.println("Exception Occurred while reading the file as string: " + e.getMessage());
        }
        return fileContent;
    }
}
