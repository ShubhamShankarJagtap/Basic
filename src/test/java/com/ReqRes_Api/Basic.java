package com.ReqRes_Api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Basic {
    @Test
    public void testCase1(){
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        System.out.println(response.toString());
        System.out.println("Response status code is  : " + response.getStatusCode());
    }
}
