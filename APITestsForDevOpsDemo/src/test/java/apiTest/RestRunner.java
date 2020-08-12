package apiTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestRunner {

    public static RequestSpecification Request;
    RequestSpecBuilder builder = new RequestSpecBuilder();
    RequestSpecification requestSpec;
   
    public static ResponseOptions<Response> executePostRequest(String url, Map<String, String> headers, HashMap<String, String> body)  {
        
    	/*RequestSpecBuilder builder = new RequestSpecBuilder();
    	           	
    	//Request.headers(headers);
    	//Request.header("Content-Type","application/json");
    	
        Request.body(body);
        RequestSpecification requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
        return Request.post(url);*/
        
        // Building request by using requestSpecBuilder
		 RequestSpecBuilder builder = new RequestSpecBuilder();
		 
		 //Set API's Headers
		 if(headers!=null) {
			 builder.addHeaders(headers); 
		 }

		 //Add required API's parameters
		 builder.addParams(body);
		 
		 //Setting content type as application/json
		 builder.setContentType("application/json; charset=UTF-8");
		 
		 RequestSpecification requestSpec = builder.build();
		
		 Request = RestAssured.given().spec(requestSpec);
		// Request.post(url);
		 
		 //Configure with required parametes and run the service
		 /*ResponseOptions<Response> response = given() 
		        .spec(requestSpec)
			    .when()
				.request(Method.POST,url);*/
		
	return Request.post(url);
        
    }
    
    
    
}