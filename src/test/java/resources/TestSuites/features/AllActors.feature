Feature: All Actors

  Background:
    Given the page "http://localhost:5173/all-actors" is loaded

  Scenario: Display all actors
    Then all actors should be listed on the page
    #And actors should have their first name and last name displayed


  Scenario Outline: Navigate to an actor's detail page
    When the user clicks on the actor link with ID "<actorId>"
    Then the user should be redirected to the correct actor page with ID "<actorId>"

    Examples:
      | actorId |
      | 1       |
      | 2       |
      | 3       |


