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

public class CountryInfo {

    private Response response;
    public static Map<String,String> headers=new HashMap<>();
    public static JSONObject body=new JSONObject();
    public static String url;
    public static String CONTENT_TYPE;
    public static String httpMethod;
    public static JSONObject queryParams=null;
    public static Map<String,String> formParams=new HashMap<>();
    public static String OAuth2;
    
    @Given("^Set Up Country URL \"([^\"]*)\"$")
    public void Set_Up_URL(String Resource) throws Throwable {
    	RestAssured.baseURI=GetConfigData.getURI("CountriesURI");
    	System.out.println("Base URI is :: "+RestAssured.baseURI);
    	url= Resource;
    	System.out.println("URL is :: "+url);
    }
    
    @And("^Set Up the method \"([^\"]*)\"$") 
    public void Set_Up_the_Service_method(String Method) throws Throwable {
    	
    	httpMethod=Method;    	
    	
    }
           
    @When("^Run the service to get country details$")
    public void Run_the_service_to_get_country_details() throws Throwable {

    	
       // response = RestRequestRunner.runGetRequest("","", "",url, "");
        response = RestRequestRunner.executeRequest("","",headers,queryParams,formParams,url,body ,httpMethod,OAuth2);
        
        
        System.out.println("Response received form the service is :::: " +response.jsonPath().prettify());
    }

    @Then("^Validate the service ran successfully and country details are fetched$")
    public void Validate_the_service_ran_successfully_and_country_details_are_fetched() throws Throwable {
       
    	assertThat(ServiceResponse.getServiceResponseStatCode(response), equalTo(200));
    	
    	System.out.println("Response Status Code is :::: "+ServiceResponse.getServiceResponseStatCode(response));
    	
    	System.out.println("Response received form the service is :::: " +response.getStatusLine().toString());
    }
    
    
        
    }