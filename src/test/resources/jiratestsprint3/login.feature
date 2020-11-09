Feature: login

Scenario: login with valid credentials

  Given I open the Chrome
  And I am on the Jira login page
  When I fill in Username
  And I fill in Password
  And I press Login
  Then I should be logged in