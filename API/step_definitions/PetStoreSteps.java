public class PetStoreSteps {

private Response response;
private RequestSpecification requestSpecification;

@Given("the API endpoint {string} is ready to accept {string} requests")
public void the_api_endpoint_is_ready_to_accept_requests(String endpoint, String method) {
requestSpecification = given().baseUri("http://api.example.com" + endpoint)
.header("Content-Type", "application/json");
}

@When("a {string} request is sent to {string} with the payload body:")
public void a_request_is_sent_with_payload(String method, String endpoint, String payload) {
switch (method.toUpperCase()) {
case "POST":
response = requestSpecification.body(payload).post(endpoint);
break;
case "PUT":
response = requestSpecification.body(payload).put(endpoint);
break;
case "DELETE":
response = requestSpecification.delete(endpoint);
break;
case "GET":
response = requestSpecification.get(endpoint);
break;
}
}

@Then("the response should have a status code of {int}")
public void the_response_should_have_a_status_code_of(int statusCode) {
response.then().statusCode(statusCode);
}

@Then("the response should confirm pet {string} in JSON format")
public void the_response_should_confirm_pet_action_in_json_format(String action) {
response.then().body("action", equalTo(action));
}

@Then("the response should return the pet information in JSON format")
public void the_response_should_return_pet_information_in_json_format() {
response.then().body(matchesJsonSchemaInClasspath("pet-schema.json"));
}

@Then("the response should return the pets with the specified status in JSON format")
public void the_response_should_return_pets_with_specified_status_in_json_format() {
response.then().body(matchesJsonSchemaInClasspath("pets-schema.json"));
}
}