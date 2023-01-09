package test;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;
import org.apache.commons.codec.binary.Base64;

import static io.restassured.RestAssured.given;

public class JiraIssueType implements RequestCapability {

    public static void main(String[] args) {
        String baseUri = "https://phongdz318.atlassian.net";
        String path = "/rest/api/3/project/PHON";

        // Generate Token for API
        String email = "nguyenhaphong.3108@gmail.com";
        String apiToken = "rOTI57o4Fwj3B2c53tqj0F89";
        String cred = email.concat(":").concat(apiToken);
        byte[] encodedCred = Base64.encodeBase64(cred.getBytes());
        String encodedCredString = new String(encodedCred);

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header("Authorization", "Basic " + encodedCredString);

        Response response = request.get(path);
        response.prettyPrint();
    }
}
