package posts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BlogPosts {
    @BeforeClass
    public static void setUP(){
        RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
    }
    @Test
    public void it_should_get_post_by_id(){

        Response response = given()
                .basePath("posts")
                .when()
                .get("5")
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getString("title"),"nesciunt quas odio");
        Assert.assertEquals(response.jsonPath().getString("body"),"repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque");
    }
    @Test
    public void it_should_get_comments_by_postID(){
        //https://jsonplaceholder.typicode.com/comments?postId=1

        Response response = given()
                .basePath("comments")
                .queryParam("postId","1")
                .when()
                .get()
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getString("[0].email"),"Eliseo@gardner.biz");

    }
    @Test
    public  void it_should_create_new_post(){

        Response response = given()
                .basePath("posts")
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"userID\":12,\n" +
                        "    \"title\":\"dummy title\",\n" +
                        "    \"body\":\"simple body\"\n" +
                        "}")
                .post()
                .then()
                .extract().response();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(response.jsonPath().getInt("id"),101);
        Assert.assertEquals(response.jsonPath().getInt("userID"),12);
        Assert.assertEquals(response.jsonPath().getString("title"),"dummy title");
        Assert.assertEquals(response.jsonPath().getString("body"),"simple body");

    }

}
