package apiTest;

import static io.restassured.RestAssured.given;

import java.util.Map;

//import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;


public class RestRequestRunner {
	
	//RequestSpecification Request = RestAssured.given();
	
	
	//@SuppressWarnings("unchecked")
	public static Response executeRequest(String userName, String password,Map<String,String> headers,Map<String,String> queryParams,Map<String,String> formParams,String URI,JSONObject body, String httpMethod, String Oauth) throws Throwable {
		
		RequestSpecification Request = RestAssured.given();
		Response response=null;
		
		if(Oauth!=null)
		{
			Request
		 	.given()
			.auth()
			.oauth2(Oauth);
		}
		
		if(formParams!=null){
			Request.formParams(formParams);
		}
		
		if(queryParams!=null){
			Request.queryParams(queryParams);
		}		
		
		if(body!=null) {
			Request.body(body);
			//Request.body(body.toJSONString());
			}
		
		if(headers!=null) {
		Request.headers(headers);
		}
		
		switch(httpMethod)
		{
			case "POST":
						response =  Request.request(Method.POST,URI);
				break;
			case "GET":	
						response = Request.request(Method.GET,URI);
				break;
			case "PUT":	
						response = Request.request(Method.PUT,URI);
				break;
			case "DELETE":	
						response = Request.request(Method.DELETE,URI);
				break;
		}
		return response;
		
	}
	
	
	
	
	public static Response runRequestWithGivenParams(String userName, String password,Map<String,String> headers,String baseUri,Map<String,String> body) {  
     		 
		 // Building request by using requestSpecBuilder
		// RequestSpecBuilder builder = new RequestSpecBuilder();
		// RequestSpecification request = RestAssured.given();
		 /*if(headers!=null) {
			 builder.addHeaders(headers); 
		 }*/
		 
		 System.out.println("Headers received in the test runner: " +headers);
		// RequestSpecification requestSpec = builder.build();
		 RequestSpecification Request = RestAssured.given();
		 
		 Request.body(body);
		 Request.headers(headers);
		/* Request.header("Content-Type", "application/json");
		 Request.body(body.toString());*/
		 //Response response = Request.post(baseUri);
		 
		 
		 Response response =  Request.request(Method.POST,baseUri);
		 
		// System.out.println("Response in RestRequestRunner :::: " +response.toString());
		 
		/* //Set API's Headers
		 if(headers!=null) {
			 builder.addHeaders(headers); 
		 }

		 //Add required API's parameters
		 builder.addParams(requestBody);
		 
		 //Setting content type as application/json
		// builder.setContentType("application/json; charset=UTF-8");
		 
		 
		
				 
		 //Configure with required parametes and run the service
		 Response response = given().spec(requestSpec)
			    .when()
				.request(Method.POST,baseUri);*/
		
	return response;
	}
	
	public static Response runGetRequest(String userName, String password,String headers,String baseUri,String body) {
	
		//Request Object
				RequestSpecification httpRequest=RestAssured.given();
				System.out.println("BASE URI == "+baseUri);
				//Response Object
				Response response=httpRequest.request(Method.GET,baseUri);
				
				return response;
	}

}
