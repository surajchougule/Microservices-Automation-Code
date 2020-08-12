package stepDefinition;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import apiTest.GetConfigData;
import apiTest.RequestBody;
import apiTest.RestRequestRunner;
import apiTest.ServiceResponse;


import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth2Steps {
		
	private Response response;
	private RequestSpecification request;
	
	
	public static Map<String,String> headers=new HashMap<>();
    public static String url;
    public static String url1;
    public static String url2;
    public static String CONTENT_TYPE;
    public static String httpMethod;
    public static Map<String,String> responseBody=new HashMap<>();
    

    public static Map<String,String> queryParams=new HashMap<>();
    public static Map<String,String> formParams=new HashMap<>();
    public static Map<String,String> formParams2=null;
   // public static Boolean OAuth2=false;
    public static String OAuth2;
    org.json.simple.JSONObject json = null;
    List<Map<String, String>> list = null;
  
		
	@Given("^Set Up the form parameters$")
	public void Set_Up_the_form_parameters(DataTable data) throws Throwable {
	
		//List<List<String>> data = table.raw();
    	list = data.asMaps(String.class, String.class);
    	
    	System.out.println("Contents of List = " +list.get(0));
    	
    	Map<String, String> map = list.get(0);
    	 System.out.println("Contents of Map = " +map);
    	 System.out.println("Contents of Map.entrySet() = " +map.entrySet());
        for (Map.Entry<String, String> entry : map.entrySet()) {
        	formParams.put(entry.getKey(),entry.getValue());
            
        }
		
		
	}	
		@And("^Set Up the resources \"([^\"]*)\" and \"([^\\\"]*)\"$")
		   public void Set_Up_URL_resource(String resource1,String resource2) throws Throwable {
	    	RestAssured.baseURI=GetConfigData.getURI("SymfonyTokenApi");
	    	System.out.println("Base URI is :: "+RestAssured.baseURI);
	    	url1=resource1;
	    	url2=resource2;
	    	System.out.println("url resource assigned is : " +url); 
	    }
	
	    @And("^Set Up the http method \"([^\"]*)\"$") 
	    public void Set_Up_the_Service_method_as(String Method) throws Throwable {
	    	httpMethod=Method;    	
	    }
	
	   	
	    @When("^Run the service to generate the oauth2 access token$")
	    public void Run_the_service_to_generate_the_oauth2_access_token() throws Throwable {
	       	          
	    	response = RestRequestRunner.executeRequest("","", headers,queryParams,formParams,url1,json,httpMethod,OAuth2);
				           
	        OAuth2=response.jsonPath().getJsonObject("access_token");
			
			System.out.println("Token is successfully generated and the token value is : "+OAuth2);
			
			System.out.println("Response received form the service 1 is :::: " +response.jsonPath().prettify());
			
			System.out.println("Response-1 Status Code is :::: "+ServiceResponse.getServiceResponseStatCode(response));
			 
			//System.out.println("Token Response is ::::: "+resp.jsonPath().prettify());
	        
	    }
	    
	    @And("^Run the service to utilize the oauth2 access token$")
	    public void Run_the_service_to_utilize_the_oauth2_access_token() throws Throwable {
	    	
	    	response = RestRequestRunner.executeRequest("","", headers,queryParams,formParams2,url2,json,httpMethod,OAuth2);
			
	        System.out.println("Response received form the service 2 is :::: " +response.jsonPath().prettify());
	    	
	    	//System.out.println("Response Body is : " +response.jsonPath().prettify());
	        
	    }
		
	    @Then("^Validate the service ran successfully$")
	    public void Validate_the_service_ran_successfully() throws Throwable {
	       
	    	assertThat(ServiceResponse.getServiceResponseStatCode(response), equalTo(200));
	    	
	    	System.out.println("Response-2 Status Code is :::: "+ServiceResponse.getServiceResponseStatCode(response));
	    	
	    	//System.out.println("Response received form the service is :::: " +response.jsonPath().get("SuccessCode"));
	    	
	    	
	    
		
		
		
	    }
	}
	


