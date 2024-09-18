import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class PetApiSteps {
    private Response response;
    private RequestSpecification requestSpecification;
    private Pet pet;

    @Given("the base URL is set to {string}")
    public void the_base_URL_is_set_to(String url) {
        requestSpecification = given().baseUri(url)
                                      .header("Content-Type", "application/json")
                                      .header("Authorization", "Bearer " + generateToken());
    }

    @When("a {string} request is sent to {string} with the JSON body")
    public void a_request_is_sent_to_with_the_JSON_body(String method, String endpoint, Map<String, String> data) {
        Category category = new Category.Builder()
                .setId(Integer.parseInt(data.get("category_id")))
                .setName(data.get("category_name"))
                .build();

        List<Tag> tags = Arrays.asList(data.get("tags").split(",")).stream()
                .map(tag -> new Tag.Builder().setId(Integer.parseInt(tag.split(":")[0])).setName(tag.split(":")[1]).build())
                .collect(Collectors.toList());

        pet = new Pet.Builder()
                .setId(Integer.parseInt(data.get("id")))
                .setName(data.get("name"))
                .setCategory(category)
                .setTags(tags)
                .setStatus(data.get("status"))
                .build();

        response = requestSpecification.body(pet).request(method.toUpperCase(), endpoint);
    }

    @Then("the response code should be {int}")
    public void the_response_code_should_be(int responseCode) {
        response.then().statusCode(responseCode);
    }

    @And("the response body should exactly match the JSON")
    public void the_response_body_should_exactly_match_the_JSON(String expectedJson) {
        response.then().body(equalTo(expectedJson));
    }

    @And("the response body should contain the message {string}")
    public void the_response_body_should_contain_the_message(String expectedMessage) {
        response.then().body("message", equalTo(expectedMessage));
    }

    @Given("a pet with ID {int} exists")
    public void a_pet_with_ID_exists(int petId) {
        // Add code to create a pet with the given ID if it doesn't exist
    }

    @When("a {string} request is sent to {string} with query parameter {string}")
    public void a_request_is_sent_to_with_query_parameter(String method, String endpoint, String queryParam) {
        response = requestSpecification.queryParam(queryParam.split("=")[0], queryParam.split("=")[1]).request(method.toUpperCase(), endpoint);
    }
}