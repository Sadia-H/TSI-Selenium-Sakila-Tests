Feature: Add Actor

  Scenario: The user submits add actor form
    Given the page "http://localhost:5173/add-actor" is loaded
    When the user types the first name "Sam"
    And types the last name "Doe"
    And clicks the Add Actor Button
    Then the user should see a "Added Actor!" message
    And should be redirected to the actor's page
    And should see the actor "Sam Doe" in the actors list
