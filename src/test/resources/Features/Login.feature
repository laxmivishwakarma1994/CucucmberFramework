
Feature: Login functionality

  Background:
    Given user should be on login page
  @smoke @regression @valid
  Scenario: Valid_Login_TC02
    When user enters valid credentials and click on login button
    Then user should be navigated to home page
    And user can see the logout link on home page

    @xyz @smoke @regression
  Scenario Outline: Invalid_Login_TC01
    When user enters invalid credentials userid as "<username>" and password as "<password>" and click on login button
    Then user should be login page
    And user can see the error message
    Examples:
    |username | password|
    |admin1   | pwd1    |
  #  |admin2   | pwd2    |
  #  |admin3   | pwd3    |
