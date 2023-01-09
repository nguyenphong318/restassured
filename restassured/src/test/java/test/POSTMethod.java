package test;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;

public class POSTMethod {

    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseUri);

        // Content-Type header
        request.header(new Header("Content-Type", "application/json; charset=UTF-8"));

//        // Create request body
//        String postBody = "{\n" +
//                "  \"userId\": 1,\n" +
//                "  \"id\": 1,\n" +
//                "  \"title\": \"The request tittle\",\n" +
//                "  \"body\": \"The request body\"\n" +
//                "}";

        // Create body By Gson
        Gson gson = new Gson();
        PostBody postBody = new PostBody();
        postBody.setUserId(1);
        postBody.setId(1);
        postBody.setTitle("The request tittle");
        postBody.setBody("The request body");

        // Send POST request Endpoint
//        Response response = request.body(postBody).post("/posts");
        Response response = request.body(gson.toJson(postBody)).post("/posts");
        response.prettyPrint();

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        // Verification response
        response.then().statusCode(equalTo(201));
        response.then().statusLine(containsStringIgnoringCase("201 Created"));
        response.then().body("userId", equalTo(1));
        response.then().body("title", equalTo("The request tittle"));
        response.then().body("body", equalTo("The request body"));
    }
}
