Feature: All Actors

  Scenario: Display and navigate through all actors page
    Given the page "http://localhost:5173/all-actors" is loaded
    Then all actors should be listed on the page
