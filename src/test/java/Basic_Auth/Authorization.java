package Basic_Auth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

public class Authorization {
    RequestSpecification requestSpecification;
    Response response;
    @Test
    public void basicAuth(){

        requestSpecification= RestAssured.given()
                .baseUri("http://postman-echo.com")
                .basePath("/basic-auth")
                .header("accept", ContentType.JSON);

        response=requestSpecification.auth().preemptive().basic("postman","password").get();
        response.prettyPrint();
        System.out.println(response.getStatusLine());
    }

   @Test
   public void digestAuth(){
      String random =  RandomStringUtils.randomAlphabetic(1);
       System.out.println(random);
        requestSpecification= RestAssured.given()
                .baseUri("http://httpbin.org")
                .basePath("/digest-auth/undefined/prachi/prachi")
                .header("accept", ContentType.JSON);

//        response=requestSpecification.auth().digest("prachi","prachi").get();
        response.prettyPrint();
        System.out.println(response.getStatusLine());

    }

}

