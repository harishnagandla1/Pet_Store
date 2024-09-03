import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.path.json.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import java.util.Map;

public class PetSteps {

    private SharedData sharedData;
    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    public PetSteps(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Before
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://api.petstore.com")
            .setContentType("application/json")
            .build();

        responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
    }

    @Given("^the API endpoint '(\/pet)' is ready to accept POST requests$")
    public void the_API_endpoint_is_ready_to_accept_POST_requests(String uri) {
        requestSpec.basePath(uri);
    }

    @When("^a POST request is sent to '(\/pet)' with the payload body:$")
    public void a_POST_request_is_sent_to_with_the_payload_body(String uri, DataTable petDetails) {
        Map<String, String> petData = petDetails.asMap(String.class, String.class);

        Pet pet = new Pet.PetBuilder()
            .setCategory(new Category(Integer.parseInt(petData.get("categoryId")), petData.get("categoryName")))
            .setName(petData.get("petName"))
            .setPhotoUrls(new String[]{petData.get("photoUrl")})
            .setTags(new Tag[]{new Tag(Integer.parseInt(petData.get("tagId")), petData.get("tagName"))})
            .setStatus(petData.get("status"))
            .build();
        
        sharedData.response = given().spec(requestSpec)
            .body(pet)
            .when().post(uri);
    }

    @Then("^the response should have a status code of (200)$")
    public void the_response_should_have_a_status_code_of(int statusCode) {
        responseSpec = responseSpec.expectStatusCode(statusCode);
        sharedData.response.then().spec(responseSpec);
    }

    @Then("^the response should confirm pet creation in JSON format$")
    public void the_response_should_confirm_pet_creation_in_JSON_format() {
        String actualStatus = JsonPath.from(sharedData.response.asString()).get("status");
        Assert.assertEquals("available", actualStatus);
    }
}

public class SharedData {
    public Response response;
}