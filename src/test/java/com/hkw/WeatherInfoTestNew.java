package com.hkw;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONPropertyName;
import org.junit.Before;
import org.junit.BeforeClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherInfoTestNew {
	

	@AfterTest
	public void testComponents()
	{
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
		// = "https://data.weather.gov.hk";
		
		int arr[] = {1,2,3};
		int[] arr2 = new int [-10];
		System.out.println("arr2"+arr2);
		
	}
	
	

	@Test(enabled = true)
	public void GetWeatherDetails() {
		
		RestAssured.baseURI = "https://data.weather.gov.hk/weatherAPI/opendata/earthquake.php?dataType=qem&lang=en";
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

	}

	@Test(enabled = true)
	public void testGetListOfUsers() {
		System.out.println("STARTING");

		RestAssured.baseURI = "https://data.weather.gov.hk";
		RestAssured.useRelaxedHTTPSValidation();

		Response response = RestAssured.given().queryParam("dataType", "qem").queryParam("lang", "en").when()
				.get(RestAssured.baseURI + "/weatherAPI/opendata/earthquake.php").then().statusCode(200).extract()
				.response();

		String jsonBody = response.getBody().asString();
		System.out.println("jsonbody ******* " + jsonBody);
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		String s = jsonPathEvaluator.get("region");
		System.out.println("region is ###"+ s);
		
		try {/*
				 * JSONArray array = new JSONArray(jsonBody); System.out.println("array size " +
				 * array.length()); assertNotNull(array); assertTrue(array.length() > 0);
				 
				 */
			
		} catch (JSONException ex) {
			fail(ex.getLocalizedMessage());
		}

	}
}
