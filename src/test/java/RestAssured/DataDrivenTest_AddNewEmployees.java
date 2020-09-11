package RestAssured;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {
	
	
	@Test(dataProvider= "getEmpData")
	void postNewEmployess(String empName, String empSalary,String empAge) {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		
		RequestSpecification httpRequest = RestAssured.given();
		//creating json object for json body for post request
		JSONObject requestParams= new JSONObject();
		
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		//header specifying what type of content
		httpRequest.header("Content-type", "application/json");
		
		
		httpRequest.body(requestParams.toJSONString());
		
		//POST request...
		
		Response response = httpRequest.request(Method.POST,"/create");
		
		//capture response body to perform validation
		String responseBody= response.getBody().asString();
		
		Assert.assertEquals(responseBody.contains(empName), true);
		
        
		int statusCode= response.getStatusCode();
		System.out.println("Status code is: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		
	}

	
	@DataProvider
	String[][]getEmpData() throws IOException{
		//read data from excel
		String path = System.getProperty("user.dir")+"/src/test/java/RestAssured/EmpData.xlsx";
		//String path= "C:\\Users\\kriks\\eclipse-workspace\\RestAssuredAutomation\\src\\test\\java\\RestAssured\\EmpData.xlsx";
		int rowNum= XLUtils.getRowCount(path, "Sheet1");
		
		int columnNum= XLUtils.getCellCount(path, "Sheet1", 1);
		//static array
		String empData[][]= new String[rowNum][columnNum];
		
		for(int i=1; i<=rowNum; i++) {
			for(int j=0; j<columnNum; j++) {
				
				empData[i-1][j]= XLUtils.getCellData(path, "Sheet1",i, j);
			}
			
		}
		
		//dynamic array
		//String empData[][]= {{"mikeVolta","25000","21" },{"johnKalish","70000","62"},{"LauraVoilet","60000","45"}};
		return (empData);
	}
}



