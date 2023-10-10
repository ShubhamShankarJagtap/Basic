package com.ReqRes_Api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class Parsing_xml {
    RequestSpecification requestSpecification;
    Response response;
    @Test
    public void xmlParsing(){
        requestSpecification   = RestAssured.given().
                baseUri("https://reqres.in")
                .basePath("/api/")
                .pathParam("Users","users")
                .header("accept", ContentType.XML)
                .contentType(ContentType.XML)
                .log()
                .all();

        response = requestSpecification.when().get("{Users}");
        response.prettyPrint();

        String pageNo=response.xmlPath().get("data.id").toString();
        System.out.println(pageNo);

        XmlPath xmlPath = new XmlPath(response.asString());

    }
}
