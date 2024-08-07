Feature: Add Actor
  Background:
    Given the page "http://localhost:5173/add-actor" is loaded

  Scenario: The user submits add actor form
    When the user types the first name "Sam"
    And types the last name "Doe"
    And clicks the Add Actor Button
    Then the user should see a "Actor added!" message
    And should be redirected to the actor page
    And should see the actor "Sam" "Doe" on the actor's page
    And should see the actor "Sam Doe" in the actors list

  Scenario: The user submits add actor form with empty fields
    When the first name is empty
    And the second name is empty
    And the user clicks the Add Actor Button
    Then the user should see and error message for first name
    And should see error message for last name
