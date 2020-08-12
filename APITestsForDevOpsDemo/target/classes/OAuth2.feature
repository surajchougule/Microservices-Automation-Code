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
@OAuth2Test
Feature: To test Restful service to Generate and Use OAuth2 access token

  @OAuthToken
  Scenario Outline: Generate and Use OAuth2 access token for accessing the API
    Given Set Up the form parameters
    | client_id    | client_secret                    | grant_type         |
    | SURAJAPITEST | cc6540fe86d0acba7e4147de3aa8f490 | client_credentials |
    
    And Set Up the resources "<resource1>" and "<resource2>"
    And Set Up the http method "<method>"
    When Run the service to generate the oauth2 access token
    And Run the service to utilize the oauth2 access token
    Then Validate the service ran successfully

    Examples: 
     | resource1 | resource2              | method |
     | /token    | /api/674/chickens-feed | POST   |
