Feature: Successful Login Scenarios

  As a user
  I want to log in to the application
  So that I can access my account

  Background:
    Given I am on the login page

  Scenario Outline: Successful Login with different credentials
    When I enter my <credential_type> and password
    And I click the login button
    Then I should see my account dashboard
    And I should be logged in successfully

    Examples:
      | credential_type |
      | email           |
      | username        |

  Scenario Outline: Successful Login with Different Browsers
    When I enter my email and password using <browser> browser
    And I click the login button
    Then I should see my account dashboard
    And I should be logged in successfully

    Examples:
      | browser |
      | Chrome  |
      | Firefox |
      | Safari  |

  Scenario Outline: Successful Login with Different Devices
    When I enter my email and password using <device> device
    And I click the login button
    Then I should see my account dashboard
    And I should be logged in successfully

    Examples:
      | device  |
      | mobile  |
      | tablet  |
      | desktop |

  Scenario: Successful Login with Previously Invalid Credentials
    When I enter my previously invalid email and password
    And I click the login button
    Then I should see the login failed message
    And I should not be logged in successfully

  Scenario: Successful Login with Initially Empty Credentials
    When I enter empty email and password
    And I click the login button
    Then I should see the login failed message
    And I should not be logged in successfully

  Scenario: Verify HTTPS for Login Requests
    When I enter my email and password
    And I click the login button
    Then I should see the HTTPS certificate
    And I should be logged in successfully