package RestAssured;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_Post_Request {

	
	@Test
	public void postRequest() {
		
		RestAssured.baseURI = "https://reqres.in/";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject requestParams= new JSONObject();
		requestParams.put("name", "john volta");
		requestParams.put("job", "leader");
		
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());// attach data to the request
		
		Response response = httpRequest.request(Method.POST, "/api/users");
		
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is:"+ responseBody);
		
	int statusCode= response.getStatusCode();
		
		System.out.println("Status code is: "+ statusCode);
		Assert.assertEquals(statusCode, 201);
		
		String successCode= response.jsonPath().get("SuccessCode");
		System.out.println("Success code is: "+ successCode);
		
		
	}
}
