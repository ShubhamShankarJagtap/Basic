package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;
import utilities.BaseClass;

import java.util.List;
import java.util.Map;

public class CustomerStepDefinition extends BaseClass {

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
            } else if(data.get("pathParam")==null){
                response = requestSpecification.get("/" + data.get("endPoint"));
                response.prettyPrint();
            }
        }
    }

    @Then("I verify all customers details in the response")
    public void iVerifyAllCustomersDetailsInTheResponse(String expectedCustomerName) {

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
}