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
@RegisterCustomerTest
Feature: To test Restful service to Register Customer

  Background: User is Logged In
  Given This is background code
 
 	@RegCust
 	Scenario Outline: Register Customer
 	  Given Set Up URL using Base URI "<Resource>"
 	  And I set header content type as "<Content-Type>"
 	  And Set Up the Register Customer Service method as "<Method>"
    When Run the service to Register Customer
    | FirstName | LastName | UserName | Password | Email 					 |
    | Suraj208  | Chougule208 | suraj208 | Password | Email208@email.com |
    
    Then Validate the service ran successfully and Customer is Registered
    
    Examples:
    | Resource  | Content-Type | Method |
    | /register | application/json | POST |