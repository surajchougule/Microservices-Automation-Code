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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostMethodSteps {

    private Response response;
    public static Map<String,String> headers=new HashMap<>();
    public static String url;
    public static String CONTENT_TYPE;
    public static String httpMethod;
    public static String body;
    JSONObject queryParams=new JSONObject();
    JSONObject json = new JSONObject();
    public static Map<String,String> formParams=new HashMap<>();
    public static String OAuth2;
    
    @Given("^Set Up URL using Base URI \"([^\"]*)\"$")
    public void Set_Up_URL_using_Base_URI(String Resource) throws Throwable {
    	RestAssured.baseURI=GetConfigData.getURI("RegCustURI");
    	System.out.println("Register Customer Base URI is :: "+RestAssured.baseURI);
    	url=Resource;
    	System.out.println("url resource assigned is : " +url);    }
    
    @And("^I set header content type as \"([^\"]*)\"$")
	public void setHeader(String contentType) {
		if (contentType != null && !contentType.isEmpty()) {
			CONTENT_TYPE = contentType;}
    	
    System.out.println("Headers are successfully configured");
    
    
    //headers=RequestHeaders.addRequestHeaders(key,value);
    
   // HashMap<String, String> headers = new HashMap<>();
    headers.put("Content-Type",CONTENT_TYPE);
            
    }
    
    @And("^Set Up the Register Customer Service method as \"([^\"]*)\"$") 
    public void Set_Up_the_Register_Customer_Service_method_as(String Method) throws Throwable {
    	
    	httpMethod=Method;    	
    	
    }
    
    
    @SuppressWarnings("unchecked")
	@When("^Run the service to Register Customer$")
    public void Run_the_service_to_Register_Customer(DataTable data) throws Throwable {

    	//List<List<String>> data = table.raw();
    	List<Map<String, String>> list = data.asMaps(String.class, String.class);
    	
        //Set body
       // HashMap<String, String> body = new HashMap<>();
               
        
		json.put("FirstName", list.get(0).get("FirstName"));
		json.put("LastName", list.get(0).get("LastName"));
		json.put("UserName", list.get(0).get("UserName"));
		json.put("Password", list.get(0).get("Password"));
		json.put("Email", list.get(0).get("Email"));

        //Perform operation
           
		response = RestRequestRunner.executeRequest("","", headers,queryParams,formParams,url,json,httpMethod,OAuth2);
		
        System.out.println("Response received form the service is :::: " +response.getBody().asString());
        
    }

    @Then("^Validate the service ran successfully and Customer is Registered$")
    public void Validate_the_service_ran_successfully_and_Customer_is_Registered() throws Throwable {
       // assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
    	assertThat(ServiceResponse.getServiceResponseStatCode(response), equalTo(201));
    	
    	System.out.println("Response Status Code is :::: "+ServiceResponse.getServiceResponseStatCode(response));
    	
    	System.out.println("Response received form the service is :::: " +response.jsonPath().get("SuccessCode"));
    }
    
    @Given("^This is background code$")
	public void this_is_background_code() throws Throwable {
		System.out.println("Background is running");
    
    }
        
    }