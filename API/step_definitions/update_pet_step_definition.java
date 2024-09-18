public class UpdatePetStepDefinition {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static Response response;

    @Given("the API endpoint '/api/pet/{petId}' is ready to accept PUT requests")
    public void the_api_endpoint_api_pet_petId_is_ready_to_accept_put_requests() {
        RestAssured.baseURI = BASE_URL;
    }

    @When("a PUT request is sent to '/api/pet/{petId}' with the payload body")
    public void a_put_request_is_sent_to_api_pet_petId_with_the_payload_body(String body) {
        response = given().contentType(ContentType.JSON).body(body).when().put("/pet/{petId}");
    }

    @Then("the response should have a status code of {int}")
    public void the_response_should_have_a_status_code_of(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response should confirm pet status update in JSON format")
    public void the_response_should_confirm_pet_status_update_in_json_format() {
        response.then().body("status", equalTo("Sold"));
    }
}