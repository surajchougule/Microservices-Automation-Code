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
@MedicareDetails
Feature: To test Restful service to Get the Medicare Details

  Background: User is Logged In
  Given This is background code running
 
 	@Providers
 	Scenario Outline: Get Medicare Details
 	  Given Set Up URL resource as "<Resource>"
 	  And Set Up the Service method as "<Method>"
 	  And Set Up the Query Parameters
 	  | provider_number | county |
    | 052761  | LOS ANGELES |
    When Run the service to get Provider details
 	  Then Validate the service ran successfully and Provider details are fetched
    
    Examples:
    | Resource | Method |
 	  | /23ew-n7w9.json | GET |