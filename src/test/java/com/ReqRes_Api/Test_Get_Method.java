package com.ReqRes_Api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;

public class Test_Get_Method {
    RequestSpecification requestSpecification;
Response response;
ValidatableResponse validatableResponse;
    @Test
    public void verifyGetRequestStatus(){

        requestSpecification   =RestAssured.given().
                baseUri("https://reqres.in")
                .basePath("/api/users/2")
                .header("accept", ContentType.JSON)
                .header("ContentType", ContentType.JSON)
                .log()
                .all();

          response = requestSpecification.when().get();
          response.prettyPrint();

//          For validating the header.
        System.out.println(response.header("accept"));   // get the value of the associate value.
        System.out.println(response.getHeader("content-Type"));
        System.out.println(response.getHeader("Connection"));

//        Printing all the header value's.
         Headers headers=response.headers();
         for (Header header:headers){
             System.out.println("The key is : " + header.getName() +" " + "The value is : " + header.getValue());
         }

//         Validating the response.

          validatableResponse = response.then().statusCode(200);

          Assert.assertEquals(response.getStatusCode(),200);

        Assert.assertEquals("THIS IS STATUS LINE VALIDATION",response.getStatusLine(),"HTTP/1.1 200 OK");
        System.out.println("The get request is successfully accepted and valid response has been received ");
    }
}
