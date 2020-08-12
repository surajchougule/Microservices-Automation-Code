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
@ExcelMedicare
Feature: To test Restful service to Get the Medicare Details

  Background: User is Logged In
  Given This is background code running
 
 	@ReadExcel
 	Scenario: Get Medicare Details using Excel Data
 	  Given Set Up Base URI
 	  And Read data from Excel
    When Run the service to get the details
 	  Then Validate the service ran successfully and the correct details are received in the response
  
