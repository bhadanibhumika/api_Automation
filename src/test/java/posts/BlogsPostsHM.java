package posts;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BlogsPostsHM {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

    }
    @Test
    public static void it_should_get_post_by_id_2(){

        Response response = given()
                .basePath("todos")
                .when()
                .get("2")
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getString("id"),"2");
        Assert.assertEquals(response.jsonPath().getString("completed"),"false");

    }
    @Test
    public static void  it_should_get_post_by_id_4(){
        Response response = given()
                .basePath("todos")
                .when()
                .get("4")
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getString("id"),"4");
        Assert.assertEquals(response.jsonPath().getString("completed"),"true");
    }

}
