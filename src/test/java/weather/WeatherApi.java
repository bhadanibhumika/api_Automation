package weather;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class WeatherApi {
    @BeforeClass
    public void setup(){

    }
    @Test
    public void it_gets_weather(){
     ValidatableResponse validatableResponse = given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .basePath("weather")
                .queryParam("lat","44.34")
                .queryParam("lon","10.99")
                .queryParam("appid","d989d11aff18746366959a80701136c1")
                .when()
                .get()
                .then();
     Response response = validatableResponse
             .extract()
             .response();

        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
       // Assert.assertEquals(response.jsonPath().getDouble("main.feels_like"),282.93);
        validatableResponse.assertThat().body("main",hasKey("feels_like"));

    }




    }