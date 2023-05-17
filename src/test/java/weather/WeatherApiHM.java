package weather;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class WeatherApiHM {
 @Test
    public void testLatitudeLongitude(){
     ValidatableResponse validatableResponse = given()
             .baseUri("https://jsonplaceholder.typicode.com/")
             .basePath("users")
             .queryParam("appid","d989d11aff18746366959a80701136c1")
             .when()
             .get("2")
             .then();
     Response response = validatableResponse
             .extract()
             .response();
     Assert.assertEquals(response.getStatusCode(),200);
     response.prettyPrint();
     Assert.assertEquals(response.jsonPath().getDouble("lat"),"-43.9509");


 }
}
