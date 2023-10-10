package com.ReqRes_Api;

import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PostExternalJson {
    RequestSpecification requestSpecification;
    Response response;
File file;
FileReader fileReader;
JsonParser jsonParser;
    @Test
    public void bodyExternaljson() throws FileNotFoundException {
        file=new File(".//Post.json");
        fileReader=new FileReader(file);
jsonParser=new JsonParser();
        requestSpecification = RestAssured.given()
                .baseUri("https://reqres.in")
                .basePath("/api/")
                .header("accept", ContentType.JSON)
                .body(jsonParser.toString())
                .log()
                .all();

        response=requestSpecification.post("users");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),201);

        String bookPrice="2000";

        Integer price=Integer.parseInt(bookPrice);
    }
}
