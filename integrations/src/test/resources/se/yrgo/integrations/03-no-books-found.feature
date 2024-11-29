Feature: Displaying a message when no books by the searched author are found
  As a user I want to see a clear message when searching for an author who has no books in the library
  So that I know the library does not have books by that author.

  Scenario: Searching for an author who is not in the library
    Given the user is on the start page.
    And the user navigates to the book search.
    When the user types "Unknown Author" in the search form.
    Then a message should be displayed saying "No books found".