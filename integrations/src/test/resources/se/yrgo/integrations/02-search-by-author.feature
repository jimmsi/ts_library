Feature: Searching for books by Author name
  As a user I want to be able to search for available books by a specifik Author.
  Scenario: Getting to the search page and type "Astrid Lindgren" in the search form.
    Given the user is on the start page.
    Then the user navigates to the search page.
    When the user types "Astrid Lindgren" in the search form.
    Then the search results should include all books written by "Astrid Lindgren".