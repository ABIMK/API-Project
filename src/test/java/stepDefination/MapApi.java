package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;

public class MapApi extends Utility{
	 
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
	  reqSpec=given().spec(baseRequestSpec()).queryParam("key", "qaclick123").contentType(ContentType.JSON).body(data.addPlacePayLoad(name, address, language));
	}
	
	@When("user calls {string} using {string} request")
	public void user_calls_using_request(String resource, String method) {
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		if(method.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("Put")) {
			response = reqSpec.when().put(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("Get")) {
			response =  reqSpec.when().get(resourceAPI.getResource());
		}
		
	}
	
	@Then("Api call success with status code {int}")
	public void api_call_success_with_status_code(Integer int1) {
	   assertEquals(200,response.getStatusCode()); 
	   response = response.then().spec(baseResSpec(200)).extract().response();
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String ExpectedValue) {
	  assertEquals(getValueFromJson(response, key),ExpectedValue);  
	}
	
	@Then("verify place_Id created maps to {string} using {string} and {string}")
	public void verify_place_id_created_maps_to_using_and(String keyValue, String resource,String key) throws IOException {
		if(place_id==null) {
			place_id = getValueFromJson(response, "place_id");
		}
	   reqSpec= given().spec(baseRequestSpec()).queryParam("key", "qaclick123").queryParam("place_id", place_id);
	   user_calls_using_request(resource,"GET");
	   System.out.println(response.asPrettyString());
	   String actualValue = getValueFromJson(response,key);
	   assertEquals(actualValue, keyValue);
	}

	@Given("Update Place Payload with {string}")
	public void update_place_payload_with(String newAddress) throws IOException {
		reqSpec = given().spec(baseRequestSpec()).queryParam("key", "qaclick123").contentType(ContentType.JSON)
		.body(data.updatePlace(place_id, newAddress));
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		reqSpec = given().spec(baseRequestSpec()).queryParam("key", "qaclick123").contentType(ContentType.JSON).body(data.deletePlacePayload(place_id));
	}
}
