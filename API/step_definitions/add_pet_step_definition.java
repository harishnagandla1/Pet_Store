public class AddPetStepDefinition {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static Response response;

    @Given("the API endpoint '/api/pet' is ready to accept POST requests")
    public void the_api_endpoint_api_pet_is_ready_to_accept_post_requests() {
        RestAssured.baseURI = BASE_URL;
    }

    @When("a POST request is sent to '/api/pet' with the payload body")
    public void a_post_request_is_sent_to_api_pet_with_the_payload_body(String body) {
        response = given().contentType(ContentType.JSON).body(body).when().post("/pet");
    }

    @Then("the response should have a status code of {int}")
    public void the_response_should_have_a_status_code_of(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response should confirm pet creation in JSON format")
    public void the_response_should_confirm_pet_creation_in_json_format() {
        response.then().body("name", equalTo("Rover"));
    }
}