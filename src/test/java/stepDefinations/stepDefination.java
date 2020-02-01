package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleMapAddAPI;
import resources.APIResouces;
import resources.TestDataBuilder;
import resources.Utils;

public class stepDefination extends Utils {
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	APIResouces apr;
	static String place_ID;

	
	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void add_Place_Payload_with(String name,String language, String address) throws Throwable {
		TestDataBuilder tb=new TestDataBuilder();
		reqSpec=given().spec(getRequestSpecificationBuilder())
		.body(tb.addPlaceTestBuilder(name,language,address));
	}

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String apiResourceName, String reqType) throws Throwable {
		apr=APIResouces.valueOf(apiResourceName);
		if (reqType.equalsIgnoreCase("POST"))
		{
			response=reqSpec.when().post(apr.getAPIResources())	
					.then().spec(getResponseSpecificationBuilder())
					.extract().response();
		}
		else if (reqType.equalsIgnoreCase("GET")) {
			response=reqSpec.when().get(apr.getAPIResources())
					.then().spec(getResponseSpecificationBuilder())
					.extract().response();
		}		
		System.out.println("Response is "+response.asString());
	}

	@Then("^the API call should be successful with (\\d+) code$")
	public void the_API_call_should_be_successful_with_code(int arg1) throws Throwable {
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("^\"([^\"]*)\" in reponse body is \"([^\"]*)\"$")
	public void in_reponse_body_is(String key,String value) throws Throwable {
	    String expectedValue=convertRawReposneToJsonAndGetValue(response, key);
	    assertTrue(expectedValue.equalsIgnoreCase(value));
	}
	
	@Then("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_Id_created_maps_to_using(String expectedName, String reqResouces) throws Throwable {
		place_ID=convertRawReposneToJsonAndGetValue(response, "place_id");
		reqSpec=given().spec(getRequestSpecificationBuilder()).queryParam("place_id", place_ID);
		user_calls_with_http_request(reqResouces,"GET");
		System.out.println("Response is "+response.asString());
		String actualName=convertRawReposneToJsonAndGetValue(response, "name");
		assertTrue(expectedName.equalsIgnoreCase(actualName));	
		
	}


}
