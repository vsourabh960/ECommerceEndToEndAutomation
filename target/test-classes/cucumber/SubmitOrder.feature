@tag
Feature: Purchase the order from Ecommerce Website

Background:
Given I landed on Ecommerce website

@Regression
Scenario Outline: Positive test of Submitting the order
    Given I logged in with email "<email>" and password "<password>"
    When  I added the product "<productName>" to the cart
    And I proceeded to checkout "<productName>" and submitted the order
    Then I should see the confirmation message "Thankyou for the order."

    Examples:
      | email               | password 		| productName     |
      | gaurav324@gmail.com | Gaurav324@	| ZARA COAT 3	  |
