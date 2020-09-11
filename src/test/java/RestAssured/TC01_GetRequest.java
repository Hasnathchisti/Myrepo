package RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.employees.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC01_GetRequest extends BaseClass {
	

	@Test
	public void getWeatherDetails() {
		
		//Specify base URI
		
		RestAssured.baseURI="http://reqres.in/api/users";
		
		//Request Object
		 httpRequest= RestAssured.given();
		logger.info("Request sent");
		
		//Response Object
		
		 response= httpRequest.request(Method.GET, 
				"?page=2");
		
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is:"+ responseBody);
		//validating response body
		Assert.assertEquals(responseBody.contains("6"), true);
		
		//validating all the values from the response
		JsonPath jsonPath = response.jsonPath();
		
		System.out.println(jsonPath.get("page"));
		System.out.println(jsonPath.get("per_page"));
		
		//status code validation
		
		int statusCode= response.getStatusCode();
		
		System.out.println("Status code is: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//status line verification
		String statusLine= response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		//capture details of headers from response
		
		String contentType= response.header("Content-type");
	    System.out.println(contentType);
	    
	    Headers allHeaders= response.headers();// capture all the headers from response
	    
	    for(Header header:allHeaders) {
	    	
	    	System.out.println(header.getName() +"  "+header.getValue());
	    }
	    
	    
		
		
	}
}
