package com.Actitime.stepDefinition;

import com.github.javafaker.Faker;
import com.pojo.CustomerPojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;
import utilities.BaseClass;

import java.util.*;

public class CustomerStepDefinition extends BaseClass {

    CustomerPojo customerPayload;

    @Given("I set up the request structure to get all customers")
    public void getAllCustomers() {
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("accept", ContentType.JSON)
                .header("ContentType", ContentType.JSON)
                .log()
                .all();
    }

    @When("I hit an api")
    public void iHitAnApi(DataTable table) {

        Map<String, String> data = table.asMaps().get(0);

        if (data.get("method").equals("GET")) {
            String pathParam = "";
            if (data.get("pathparam") != null) {
                pathParam = data.get("pathParam");
                requestSpecification.pathParam("projectId", pathParam);
                response = requestSpecification.get("/" + data.get("endPoint") + "{projectId}");
                response.prettyPrint();
            } else if (data.get("pathParam") == null) {
                response = requestSpecification.get("/" + data.get("endPoint"));
                response.prettyPrint();
            }
        } else if (data.get("method").equals("POST")) {
            response = requestSpecification.post("/" + data.get("endPoint"));
        } else if (data.get("method").equals("PUT    ")) {
            String pathParam = "";
            if (data.get("pathparam") != null) {
                pathParam = data.get("pathParam");
                requestSpecification.pathParam("CustomerId", pathParam);
                requestSpecification.put("/" + data.get("endPoint") + "{CustomerId}");
            } else if (data.get("pathparam") == null) {
                requestSpecification.put("/" + data.get("endPoint"));
            }
        } else if (data.get("method").equals("PATCH")) {
            String pathParam = "";
            if (data.get("pathparam") != null) {
                pathParam = data.get("pathParam");
                requestSpecification.pathParam("CustomerId", pathParam);
                requestSpecification.patch("/" + data.get("endPoint") + "{CustomerId}");
            } else if (data.get("pathparam") == null) {
                requestSpecification.patch("/" + data.get("endPoint"));
            }
        } else if (data.get("method").equals("DELETE")) {
            String pathParam = data.get("pathParam");
            requestSpecification.pathParam("CustomerId", pathParam);
            requestSpecification.delete("/" + data.get("endPoint") + "{CustomerId}");
        }

        response.prettyPrint();
    }

    @Then("I verify all customers details in the response")
    public void iVerifyAllCustomerDetailsInTheResponse(String expectedCustomerName) {

        int statusCode = response.getStatusCode();

        Assert.assertEquals(200, statusCode);

        List<Map<String, Object>> itemsList = response.jsonPath().getList("items");

        for (Map<String, Object> customerDetails : itemsList) {

            String actualCustomerName = customerDetails.get("name").toString();

            if (expectedCustomerName.equals(actualCustomerName)) {
                System.out.println("The verification is passed");
                break;
            }
        }
    }

    @Given("I setup the request structure to create new customer")
    public void iSetupTheRequestStructureToCreateNewCustomer(DataTable table) {

// Get the name of the customer from customer.feature file using datatable.

        String customerName = table.asMaps().get(0).get("name");
//        String archived = table.asMaps().get(0).get("archived");

        String requestBody;
        if (Objects.isNull(customerName)) {
            requestBody = """
                    {
                        "name": null,
                        "archived": false
                    }""";
        } else if (customerName.equals("empty")) {
            requestBody = """
                    {
                        "name": "",
                        "archived": false
                    }""";
        } else {
            requestBody = "{\n" +
                    "    \"name\": \"" + customerName + "\",\n" +
                    "    \"archived\": false\n" +
                    "}";
        }

        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", ContentType.JSON)
                .header("Content-type", ContentType.JSON)
                .body(requestBody)
                .log()
                .all();

    }

    @Then("I verify the customer is getting created successfully with the name {string}")
    public void iVerifyTheCustomerIsGettingCreatedSuccessfullyWithTheName(String expectedCustomerName) {

        Assert.assertEquals(200, response.getStatusCode());

        custemerId = response.jsonPath().getInt("id");

        String actualCustomerName = response.jsonPath().getString("name");

        Assert.assertEquals(expectedCustomerName, actualCustomerName);

        // verify the archived value and it should be false.

        Assert.assertFalse(response.jsonPath().get("archived"));

//        Verify the description, it should be null.

        Assert.assertTrue(Objects.isNull(response.jsonPath().get("description")));

        System.out.println("Verification is successful as : " + actualCustomerName + "Matches" + expectedCustomerName);
    }

    @Given("I set up the request structure.")
    public void iSetUpTheRequestStructure(Map<String, Object> queryParams) {

        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", ContentType.JSON)
                .log().all();

//       check if queryParams is not null if it is not null, then add it in the requestSpecification

        if (Objects.nonNull(queryParams)) {

            requestSpecification.queryParams(queryParams);
        }
    }

    @Then("I verify the response is getting sorted in {string}")
    public void iVerifyTheResponseIsGettingSortedIn(String order) {

// write a logic to verify the response if it is in a provided order.

        Assert.assertEquals(200, response.getStatusCode());

//        To print the all information of the customer.

        System.out.println(response.jsonPath().getList("items.description"));

//        Print all the value's of allowed acton fiels.

        List<Map<String, Boolean>> allowedAction = response.jsonPath().getList("items.allowedActions");
        System.out.println(allowedAction);

//      get all the names from the response.

        List<String> names = response.jsonPath().getList("items.names");

//        Create a new list & add a name list to it.

        ArrayList<String> actualSortedList = new ArrayList<>(names);

        Comparator<String> comparator = (s1, s2) -> {

            return s1.compareToIgnoreCase(s2);
        };

//       Comparator<String> comparator1 = String::compareToIgnoreCase;

        if (order.equals("desc")) {

//    sort the lst in reverse order
//     Collections.reverse(names);

            names.sort(Collections.reverseOrder(comparator));

            System.out.println("Names in the descending order : " + names);
        } else if (order.equals("asc")) {

// Sort the list is ascending order

            Collections.sort(names, comparator);
            names.sort(comparator);

            System.out.println("Names in the ascending order : " + names);
        }
        Assert.assertEquals(names, actualSortedList);
    }

    @Then("I verify the status-code as {int} and error message")
    public void iVerifyTheStatusCodeAsAndErrorMessage(int expectedStatusCode, Map<String, String> data) {

        Assert.assertEquals(expectedStatusCode, response.getStatusCode());

//       Verify an error message

        Assert.assertEquals(data.get("message"), response.jsonPath().getString("message"));
    }

    @Given("I setup the request structure to create customer payload")
    public void iSetupTheRequestStructureToCreateCustomerPayload(DataTable table) {

        customerPayload = new CustomerPojo();
        Map<String, String> payload = table.asMaps().get(0);
        String name = (payload.get("name").equals("random") ? new Faker().name().firstName() : payload.get("name"));

        customerPayload.setName(name);

        Optional.ofNullable(payload.get("archived"))
                .ifPresentOrElse(value -> {
                    boolean archived = Boolean.parseBoolean(value);
                    customerPayload.setArchived(archived);
                }, () -> System.out.println("Empty value in archived."));

        requestSpecification.basePath("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", ContentType.JSON)
                .header("Content-Type", ContentType.JSON)
                .body(customerPayload)
                .log()
                .all();
    }

    @Then("I verify status code {int} and archived in the response.")
    public void iVerifyStatusCodeAndArchivedInTheResponse(int expectedStatusCode, boolean expectedArchiveValue) {

        Assert.assertEquals(expectedStatusCode, response.getStatusCode());

        Assert.assertEquals(expectedArchiveValue, response.jsonPath().getBoolean("archived"));

        custemerId = response.jsonPath().getInt("id");
    }
}
