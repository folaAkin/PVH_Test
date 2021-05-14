Feature: Account Registration


  As a user
  I want to create new account
  So that I can view my account details


  Background:
    Given user is on Tommy home page
    And I change country to "United Kingdom"


  Scenario: Verify that user can create account with valid  data
    When user clicks on sign in button
    And user clicks on create account link
    And user enters the following details
      | Email    |
      | password |
    And user confirms the terms and conditions
    And user clicks on create an account button
    Then user should be on the my account page and following details should be displayed
      | MY ACCOUNT        |
      | OVERVIEW          |
      | ORDERS            |
      | MY DETAILS        |
      | ADDRESS BOOK      |
      | EMAIL PREFERENCES |

  Scenario: Verify that user can create account with invalid  data
    When user clicks on sign in button
    And user clicks on create account link
    And user enters the following details
      | invalidEmail    |
      | invalidPassword |
    And user clicks on create an account button
    Then the following error messages should be displayed
      | Sorry, that doesn't look like an email address             |
      | Your password needs to be between 5 and 20 characters long |
      | Please confirm you accept the terms and conditions         |

  Scenario: Verify that registered user can add new address
    When user clicks on sign in button
    And user clicks on create account link
    And user enters the following details
      | Email    |
      | password |
    And user confirms the terms and conditions
    And user clicks on create an account button
    And user clicks on address book
    And user clicks on add new address
    And user enters add new address details with the following
      | firstName |
      | lastName  |
      | address   |
      | town      |
      | postcode  |
    And user saves address details
    Then the address should be should displayed "address"






