
@tag
Feature: Error Validation
  To check if error is showing or not when entering wrong login details

  @ErrorValidation
  Scenario Outline: Negative Test 
    Given I landed on Ecommerce Page
    When Logged in with username <name> and passowrd <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  								| password 		| 
      | pegasus@wolverine.com | Pegsfbs007@ |	