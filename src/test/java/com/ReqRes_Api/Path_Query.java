package com.ReqRes_Api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

public class Path_Query {
    RequestSpecification requestSpecification;
    Response response;
    @Test
    public void learn(){
        requestSpecification= RestAssured.given()
                .baseUri("https://reqres.in")
                .basePath("/api")
                .pathParam("My first pathParameter","users")
                .queryParam("page",2)
                .queryParam("id",5)
                .header("accept", ContentType.JSON)
                .log()
                .all();

        response=requestSpecification.when().get("/"+"{My first pathParameter}");
       Map<String,String>cook= response.getCookies();
      Set<Map.Entry<String,String>> set  = cook.entrySet();

        System.out.println(set);
        System.out.println(cook.keySet());
        System.out.println(cook);
        response.prettyPrint();
          Assert.assertEquals(200,response.getStatusCode());
        String resBody=response.body().asString();

        String pathParameter = study();
        System.out.println(pathParameter);
    }

    String study(){
        int customerId = 24;
        return String.valueOf(customerId);
    }
}
