#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@CountryDetails
Feature: To test Restful service to Get the Country Details

  Background: User is Logged In
  Given This is background code running
 
 	@GetCountryInfo
 	Scenario Outline: Get Country Information
 	  Given Set Up Country URL "<Resource>"
 	  And Set Up the method "<Method>"
 	  When Run the service to get country details
    Then Validate the service ran successfully and country details are fetched
    
    Examples:
    | Method | Resource  |
 	  | GET 	 | /rest/v1/ |