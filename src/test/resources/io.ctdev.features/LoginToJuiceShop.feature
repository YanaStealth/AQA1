
Feature: Customer SignUp
  Scenario Outline: User is able to login to application
    Given user opens login page
    When user enters login "<login>" and password "<password>"
    And user clicks on login button
    Then user "<login>" should be logged to application
Examples:
    |login          |password|
    |yana4@gmail.com| qQ2$4|
    |yana5@gmail.com| qQ2$4|