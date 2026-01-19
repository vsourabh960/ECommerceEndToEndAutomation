@tag
Feature: Error Validation

Background:
Given I landed on Ecommerce website

@ErrorValidation
Scenario Outline: Varifying the error by giving wrong credentials
    Given I logged in with email "<email>" and password "<password>"
    Then I should see the error message "Incorrect email or password."

    Examples:
      | email               | password 	|
      | gaurav324@gmail.com | Gaurav32	|
