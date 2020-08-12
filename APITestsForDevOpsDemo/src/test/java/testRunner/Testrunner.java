package testRunner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import apiTest.GetConfigData;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/feature",
		monochrome=true,
	    strict=true,
	    dryRun=false,
		//tags= {"@OAuth2Test,@CountryDetails,@TwitterTest,@MedicareDetails,@RegisterCustomerTest,@WeatherDetails"},
		tags= {"@CountryDetails,@OAuth2Test"},
	    plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json","junit:target/cucumber-reports/Cucumber.xml","html:target/cucumber-reports","com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		glue={""} 
		)

public class Testrunner
{
	@AfterClass
	 public static void writeExtentReport() {
	 Reporter.loadXMLConfig(new File(GetConfigData.getReportConfigPath()));
	 
	 Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
     Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
     Reporter.setSystemInfo("Machine", "Windows 10, " + "64 Bit");
     Reporter.setSystemInfo("Java Version", "1.8.0_20");
	 
	 }
	
	
}

