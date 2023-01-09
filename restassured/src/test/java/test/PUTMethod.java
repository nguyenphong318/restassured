package test;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BuildModelJSON;
import model.PostBody;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PUTMethod {

    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com";

        // Create request object and header
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(new Header("Content-Type", "application/json; charset=UTF-8"));

        // Create body
        PostBody postBody1 = new PostBody(1, 1, "New Title 1", "New Body 1");
        PostBody postBody2 = new PostBody(1, 1, "New Title 2", "New Body 2");
        PostBody postBody3 = new PostBody(1, 1, "New Title 3", "New Body 3");

        List<PostBody> postBodies = Arrays.asList(postBody1, postBody2, postBody3);

        for (PostBody postBody : postBodies) {

            System.out.println(postBody);
            String postBodyString = BuildModelJSON.parseJSONString(postBody);
            // Send request
            final int TARGET_POST_NUMBER = 1;
            Response response = request.body(postBodyString).put("/posts/".concat(String.valueOf(TARGET_POST_NUMBER)));
            response.prettyPrint();

            // Verify body
            response.then().body("title", equalTo(postBody.getTitle()));
            response.then().body("body", equalTo(postBody.getBody()));
            response.then().body("id", equalTo(postBody.getId()));
            response.then().body("userId", equalTo(postBody.getUserId()));
        }

    }
}
