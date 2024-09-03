import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.path.json.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import org.junit.Assert;

public class JobUserSteps {

private SharedData sharedData;
private RequestSpecification requestSpec;
private ResponseSpecification responseSpec;

public JobUserSteps(SharedData sharedData) {
this.sharedData = sharedData;
}

@Before
public void setUp() {
requestSpec = new RequestSpecBuilder()
.setBaseUri("http://api.example.com")
.setContentType("application/json")
.build();

responseSpec = new ResponseSpecBuilder()
.expectStatusCode(201)
.build();
}

@Given("^the API endpoint '([^"]*)' is ready to accept POST requests$")
public void api_endpoint_is_ready(String uri) {
requestSpec.basePath(uri);
}

@When("^a POST request is sent to '([^"]*)' with the payload body:$")
public void i_send_a_post_request_to_with_the_following_user_details(String uri, String payload) {
sharedData.response = given().spec(requestSpec)
.body(payload)
.when().post(uri);
}

@Then("^the response should have a status code of (\\d+)$")
public void i_receive_a_response(int statusCode) {
responseSpec = responseSpec.expectStatusCode(statusCode);
sharedData.response.then().spec(responseSpec);
}

@Then("^the response should confirm user creation in JSON format$")
public void the_response_should_confirm_user_creation() {
String actualName = JsonPath.from(sharedData.response.asString()).get("name");
Assert.assertNotNull(actualName);
}
}

public class SharedData {
public Response response;
}
