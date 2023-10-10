package utilities;

;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClass {

    static public RequestSpecification requestSpecification= given();
    static public Response response;
static public int custemerId;
static public int projectId;
static public int userId;

}
