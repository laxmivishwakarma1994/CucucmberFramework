Feature: Lead functionality

  Background:
    Given user should be on login page
    When user enters valid credentials and click on login button
    Then user should be navigated to home page

    @CreateLead
  Scenario: Create_Lead_with_Mandatory_Fields_TC03
    When user click on new lead
    And fill all mandatory fields and click on save
    Then lead should be created successfully