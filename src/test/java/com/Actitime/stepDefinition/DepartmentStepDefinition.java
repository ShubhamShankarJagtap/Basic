package com.Actitime.stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;
import utilities.BaseClass;

import java.util.HashMap;
import java.util.Map;

public class DepartmentStepDefinition extends BaseClass {
    @Given("I set up the request structure to get all department")
    public void setup(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1/")
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .log()
                .all();
    }

    @When("I will hit get all department api")
    public void iWillHitGetAllDepartmentApi(DataTable table) {
        Map<String,String> data=table.asMaps().get(0);
        response = requestSpecification.get(data.get("endpoint"));
        response.prettyPrint();
    }
    @Then("I verify all department details")
    public void iVerifyAllDepartmentDetails() {
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(200,actualStatusCode);
        System.out.println("Inside verification.");
    }



}
