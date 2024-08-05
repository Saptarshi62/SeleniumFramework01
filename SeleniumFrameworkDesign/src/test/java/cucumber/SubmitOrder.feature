
@tag
Feature: Purchase the order from Ecommerce Website
  This is used to place an order form a Ecommerce Website

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username <name> and passowrd <password>
    When Add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name  								| password 		| productName			|
      | pegasus@wolverine.com | Pegasus007@ |	ADIDAS ORIGINAL	|
      | abc@cdf.com					  | Abc@cdf123 	|	IPHONE 13 PRO		|
