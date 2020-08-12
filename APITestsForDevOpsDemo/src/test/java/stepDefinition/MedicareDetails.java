package stepDefinition;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;

import lombok.var;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.IsNot;
//import org.json.JSONObject;
import org.json.simple.JSONObject;

import apiTest.GetConfigData;
import apiTest.RequestHeaders;
import apiTest.RestRequestRunner;
//import utilities.EARestAssuredV2;
import apiTest.RestRunner;
import apiTest.ServiceResponse;

import javax.xml.crypto.Data;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class MedicareDetails {
	
	    public static Map<String,String> headers=new HashMap<>();
	    public static String url;
	    public static String CONTENT_TYPE;
	    public static String httpMethod;
	    public static Map<String,String> responseBody=new HashMap<>();
	    private Response response;
	    public static Map<String,String> queryParams=new HashMap<>();
	    public static Map<String,String> formParams=new HashMap<>();
	    JSONObject json = null;
	    List<Map<String, String>> list = null;
	    public static String OAuth2=null;
	
	    /*@Given("^This is background code running$")
		public void this_is_background_code_running() throws Throwable {
			System.out.println("Background is running");
	    
	    }*/
	    
	    @Given("^Set Up URL resource as \"([^\"]*)\"$")
	    public void Set_Up_URL_resource(String Resource) throws Throwable {
	    	RestAssured.baseURI=GetConfigData.getURI("medicareURI");
	    	System.out.println("medicare Base URI is :: "+RestAssured.baseURI);
	    	url=Resource;
	    	System.out.println("url resource assigned is : " +url); 
	    }
	
	    @And("^Set Up the Service method as \"([^\"]*)\"$") 
	    public void Set_Up_the_Service_method_as(String Method) throws Throwable {
	    	
	    	httpMethod=Method;    	
	    	
	    }
	
	    
		@And("^Set Up the Query Parameters$") 
	    public void Set_Up_the_Query_Parameters_as(DataTable data) throws Throwable {
	    	
	    	//List<List<String>> data = table.raw();
	    	list = data.asMaps(String.class, String.class);
	    	
	    	//JSONObject queryParams = new JSONObject();
					
			/*@SuppressWarnings("unchecked")
			Set<String> keys = ((Map<String,String>) list).keySet();
	        for(String key: keys){
	        	queryParams.put(key,list.get(0).get(key));
	        }*/
	    	
	    	
	       System.out.println("Contents of List = " +list.get(0));
	    	
	    	Map<String, String> map = list.get(0);
	    	 System.out.println("Contents of Map = " +map);
	    	 System.out.println("Contents of Map.entrySet() = " +map.entrySet());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                queryParams.put(entry.getKey(),entry.getValue());
                
            }
	    	
	    }
	    
	    
	    
	    @When("^Run the service to get Provider details$")
	    public void Run_the_service_to_get_Provider_details() throws Throwable {
	       	          
	    	response = RestRequestRunner.executeRequest("","", headers,queryParams,formParams,url, json,httpMethod,OAuth2);
			
	        System.out.println("Response received form the service is :::: " +response.getBody().asString());
	        
	    }
	    
	    @Then("^Validate the service ran successfully and Provider details are fetched$")
	    public void Validate_the_service_ran_successfully_and_Provider_details_are_fetched() throws Throwable {
	       // assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
	    	assertThat(ServiceResponse.getServiceResponseStatCode(response), equalTo(200));
	    	
	    	System.out.println("Response Status Code is :::: "+ServiceResponse.getServiceResponseStatCode(response));
	    	
	    	//System.out.println("Response received form the service is :::: " +response.jsonPath().get("SuccessCode"));
	    	
	    	System.out.println("Response Body is : " +response.jsonPath().prettify());
			
						
			String extractedProviderNumber=response.then().extract().path("provider_number").toString().replace("[", "").replace("]", "");
			String extractedCounty=response.then().extract().path("county").toString().replace("[", "").replace("]", "");
			
			System.out.println("Extracted Provider Number is : " +extractedProviderNumber);
			System.out.println("Extracted County is : " +extractedCounty);
			
			assertThat(response.then().extract().path("provider_number").toString().replace("[", "").replace("]", "").toString(), equalTo(list.get(0).get("provider_number")));
			assertThat(response.then().extract().path("county").toString().replace("[", "").replace("]", "").toString(), equalTo(list.get(0).get("county")));
	    }
	     
	

}
