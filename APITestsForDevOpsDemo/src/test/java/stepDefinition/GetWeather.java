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

public class GetWeather {

    private Response response;
    public static Map<String,String> headers=new HashMap<>();
    public static JSONObject body=new JSONObject();
    public static String url;
    public static String CONTENT_TYPE;
    public static String httpMethod;
    public static JSONObject queryParams=null;
    public static Map<String,String> formParams=new HashMap<>();
    public static String OAuth2;
    
    @Given("^Set Up URL \"([^\"]*)\"$")
    public void Set_Up_URL(String Resource) throws Throwable {
    	RestAssured.baseURI=GetConfigData.getURI("getWeatherURI");
    	System.out.println("Base URI is :: "+RestAssured.baseURI);
    	url= Resource;
    	System.out.println("URL is :: "+url);
    }
    
    @And("^Set Up the Service method \"([^\"]*)\"$") 
    public void Set_Up_the_Service_method(String Method) throws Throwable {
    	
    	httpMethod=Method;    	
    	
    }
           
    @When("^Run the service to get weather details$")
    public void Run_the_service_to_get_weather_details() throws Throwable {

    	
       // response = RestRequestRunner.runGetRequest("","", "",url, "");
        response = RestRequestRunner.executeRequest("","",headers,queryParams,formParams,url,body ,httpMethod,OAuth2);
        
        
        System.out.println("Response received form the service is :::: " +response.body().asString());
    }

    @Then("^Validate the service ran successfully and weather details are fetched$")
    public void Validate_the_service_ran_successfully_and_weather_details_are_fetched() throws Throwable {
       
    	assertThat(ServiceResponse.getServiceResponseStatCode(response), equalTo(200));
    	
    	System.out.println("Response Status Code is :::: "+ServiceResponse.getServiceResponseStatCode(response));
    	
    	System.out.println("Response received form the service is :::: " +response.getStatusLine().toString());
    }
    
    @Given("^This is background code running$")
	public void this_is_background_code_running() throws Throwable {
		System.out.println("Background is running");
    
    }
        
    }