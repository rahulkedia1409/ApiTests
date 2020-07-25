package com.rahul.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rahul.main.helper.LoggerHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.json.JSONObject;
import org.testng.Assert;

import java.lang.reflect.Type;


public class GetAccessToken {
    public static Logger logger = LoggerHelper.getLogger(GetAccessToken.class);

    public  OAuthToken getAuthTOken(String userName, String password,String url
                                          ) {

        RestAssured.baseURI = "http://13.126.80.194:8080";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();

        if (userName == null || password == null) {

            requestParams.put("username", "rupeek");
            requestParams.put("password", "password");
        } else {
            RestAssured.baseURI = url;
            requestParams.put("username", userName);
            requestParams.put("password", password);
        }
        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post("/authenticate");
        Assert.assertEquals(response.getStatusCode(), 200, "AUTH Failed:::");
        Gson gson = new Gson();
        OAuthToken oAuthToken = new OAuthToken();
        Type fooType = new TypeToken<OAuthToken>() {
        }.getType();
        oAuthToken = gson.fromJson(response.getBody().asString(), fooType);
        Assert.assertNotNull(oAuthToken, "Auth Token Not Received:::");
        logger.log(Priority.INFO, "AUTH Success:::" + oAuthToken.getToken());
        return oAuthToken;
    }
}
