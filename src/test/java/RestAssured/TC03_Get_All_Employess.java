package RestAssured;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employees.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC03_Get_All_Employess extends BaseClass {

	
	@BeforeClass
	void getAllEmployess() {
		
		
		logger.info("************Started TC03_Get_All_Employess******");
		
		
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employess");
		
		
	}
	
	@Test
	
	void checkResponseBody() {
		
		logger.info("Checking Response Body");
		
		String responseBody= response.getBody().asString();
		//logger.info("Response body:" + responseBody);
		System.out.println(responseBody);;
		Assert.assertTrue(responseBody!= null);
	}
	
	
	
}
