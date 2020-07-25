package com.rahul.main;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rahul.main.helper.LoggerHelper;
import com.rahul.main.pojo.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.testng.Assert;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiClient {

    public static String userDetailsPathh = "/api/v1/users";
    public static Logger logger = LoggerHelper.getLogger(GetAccessToken.class);

    public List<User> getUserList(String url, String userName, String password) {
        Map<String, String> headerMap = new HashMap<>();
        RequestSpecification requestSpecification = RestAssured.given();
        GetAccessToken getAccessToken = new GetAccessToken();
        OAuthToken token = getAccessToken.getAuthTOken(userName, password, url);
        String userDetailsPath = url + userDetailsPathh;
        requestSpecification.accept("application/json");
        requestSpecification.contentType("application/json");
        String tokenn = "Bearer " + token.getToken();
        headerMap.put("authorization", tokenn);
        requestSpecification.headers(headerMap);

        Response response = requestSpecification.get(userDetailsPath);
        int responseCode = response.getStatusCode();

        Assert.assertEquals(response.getStatusCode(), 200, "The Api is not responding");
        System.out.println("BODY" + response.getBody().asString());
        logger.log(Priority.INFO, "GET is successfull");
        Gson gson = new Gson();


        Type fooType = new TypeToken<List<User>>() {
        }.getType();


        List<User> userList = gson.fromJson(response.getBody().asString(), fooType);

        return userList;

    }

    public User getUser(String url, String userName, String password, String phone_number) {

        Map<String, String> headerMap = new HashMap<>();
        RequestSpecification requestSpecification = RestAssured.given();
        GetAccessToken getAccessToken = new GetAccessToken();
        OAuthToken token = getAccessToken.getAuthTOken(userName, password, url);
        String userDetailsPath = url + userDetailsPathh + "/" + phone_number;
        requestSpecification.accept("application/json");
        requestSpecification.contentType("application/json");
        String tokenn = "Bearer " + token.getToken();
        headerMap.put("authorization", tokenn);
        requestSpecification.headers(headerMap);
        Response response = requestSpecification.get(userDetailsPath);
        int responseCode = response.getStatusCode();

        Assert.assertEquals(response.getStatusCode(), 200, "The Api is not responding");
        System.out.println("BODY" + response.getBody().asString());
        logger.log(Priority.INFO, "GET is successfull");
        Gson gson = new Gson();


        Type fooType = new TypeToken<User>() {
        }.getType();


        User User = gson.fromJson(response.getBody().asString(), fooType);

        return User;

    }

}
