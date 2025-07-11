package stepdefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.cucumber.java.en.*;

public class GitHubStepDefs {

    Response response;
    String bearerToken = "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";

    @Given("GitHub API is available")
    public void github_api_is_available() {
        RestAssured.baseURI = "https://api.github.com";
    }
//just for testing
    @When("I send a POST request to create a new repository")
    public void i_send_post_request_to_create_repo() {
        String requestBody = "\"{\\r\\n\"\n"
        		+ "				+ \"    \\r\\n\"\n"
        		+ "				+ \"    \\\"name\\\":\\\"API-testing-repositorycall123\\\",\\r\\n\"\n"
        		+ "				+ \"    \\\"description\\\":\\\"Repository created via Postman call\\\"\\r\\n\"\n"
        		+ "				+ \"    \\r\\n\"\n"
        		+ "				+ \"    }\"";

        response = given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(requestBody)
                   .when()
                        .post("/user/repos");
    }

    @Then("the repository should be created successfully with name {string}")
    public void verify_repo_created(String expectedName) {
        response.then()
                .statusCode(201)
                .body("name", equalTo(expectedName))
                .body("description", equalTo("Repository created via Postman call"));
    }
}
