package com.hkw;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherInfoTests {

	@Test(enabled = false)
	public void GetWeatherDetails() {
		// Specify the base URL to the RESTful web service
		/*
		 * RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
		 */
		RestAssured.baseURI = "https://data.weather.gov.hk/weatherAPI/opendata/earthquake.php?dataType=qem&lang=en";
		RestAssured.useRelaxedHTTPSValidation();

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method
		// URL.
		// This will return the Response from the server. Store the response in a
		// variable.

		Response response = httpRequest.request(Method.GET, "/");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

	}

	@Test(enabled = false)
	public void RegistrationSuccessful() {
		RestAssured.baseURI = "https://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender"); // Cast
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");

		requestParams.put("Email", "sample2ee26d9@gmail.com");
		request.body(requestParams.toString());
		Response response = request.post("/register");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, "201");
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals("Correct Success code was returned", successCode, "OPERATION_SUCCESS");
	}

	@Test(enabled = false)

	public void attemptOne() {

	}

	@Test(enabled = false)
	public void getCallWeather() {

		// Add a header as key value
		RestAssured.given().header("someHeader", "somevalue");
		RestAssured.given().headers("someHeader", "somevalue");

		// Add header as a Map
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("someHeader", "somevalue");
		RestAssured.given().headers(requestHeaders);

		// Add header using Header class
		Header requestHeader1 = new Header("someHeader", "somevalue");
		RestAssured.given().header(requestHeader1);

		// Add header using Header and Headers class
		Header requestHeader2 = new Header("someHeader", "somevalue");
		Headers requestHeaders3 = new Headers(requestHeader2);
		RestAssured.given().headers(requestHeaders3);

		// Add header with multiple values
		RestAssured.given().header("someHeader", "someFirstvalue", "someSecondvalue");

		// Changing default behavior of merging
		RestAssuredConfig.config().headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("header1"));

		// Change overwrite behavior if not
		RestAssuredConfig.config().headerConfig(HeaderConfig.headerConfig().mergeHeadersWithName("header1"));

	}

	/*
	 * import static io.restassured.RestAssured.given;
	 * 
	 * import org.json.simple.JSONObject; import org.testng.Assert; import
	 * org.testng.annotations.Test;
	 * 
	 * import io.restassured.http.ContentType; import
	 * io.restassured.response.ValidatableResponse;
	 * 
	 * public class WeatherInfoTests {
	 * 
	 * public static void main(String[] args) {
	 * 
	 * // get("/lotto").then().body("lotto.lottoId", equalTo(5));
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test public void doPost() { JSONObject jsonObj = new JSONObject();
	 * jsonObj.put("name", "Task 1"); jsonObj.put("category", "R&D");
	 * jsonObj.put("status", "Completed");
	 * 
	 * ValidatableResponse response = given() .contentType(ContentType.JSON)
	 * .accept(ContentType.JSON) .body(jsonObj.toJSONString()) .when()
	 * .post(BASE_URI + "/tasks") .then();
	 * 
	 * System.out.println("POST Response:\n" +
	 * response.extract().body().asString());
	 * 
	 * TASK_ID = response.extract().body().jsonPath().get("_id"); // Tests
	 * Assert.assertEquals(response.extract().statusCode(), 201);
	 * Assert.assertEquals(response.extract().body().jsonPath().get("name"),
	 * "Task 1");
	 * Assert.assertEquals(response.extract().body().jsonPath().get("category"),
	 * "R&D");
	 * Assert.assertEquals(response.extract().body().jsonPath().get("status[0]"),
	 * "Completed"); } }
	 */

	@Test(enabled = true)
	public void testGetListOfUsers() {
		/*
		 * Response response = RestAssured.given() .header("Authorization", "")
		 * .accept(JSON) .queryParam("page", "1") .queryParam("limit", "50") .when()
		 * .get(CONEXT_PATH + "/users") .then() .statusCode(200) .contentType(JSON)
		 * .extract() .response();
		 *
		 * https://www.appsdeveloperblog.com/rest-assured-http-get-request-with-query-
		 * parameters/#:~:text=To%20include%20Query%20String%20Request,value%20of%20the%
		 * 20request%20parameter.
		 *
		 */
		System.out.println("STARTING");

		RestAssured.baseURI = "https://data.weather.gov.hk";

		Response response = RestAssured.given().queryParam("dataType", "qem").queryParam("lang", "en").when()
				.get(RestAssured.baseURI + "/weatherAPI/opendata/earthquake.php").then().statusCode(200).extract()
				.response();

		String jsonBody = response.getBody().asString();
		System.out.println("jsonbody ******* " + jsonBody);

		try {
			JSONArray array = new JSONArray(jsonBody);
			System.out.println("array size " + array.length());
			assertNotNull(array);
			assertTrue(array.length() > 0);
		} catch (JSONException ex) {
			fail(ex.getLocalizedMessage());
		}

	}
}