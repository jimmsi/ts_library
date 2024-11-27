package se.yrgo.integrations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import se.yrgo.pages.SearchPage;
import se.yrgo.pages.StartPage;

public class SearchByAuthorStepDefinitions {
    private final WebDriver driver = GeneralStepDefinitions.getDriver();
    private final StartPage startPage = new StartPage(driver);
    private final SearchPage searchPage = new SearchPage(driver);

    @Then("the user navigates to the search page.")
    public void the_user_navigates_to_the_search_page() {
        startPage.navigateToSearchPage();
    }

    @When("the user types {string} in the search form.")
    public void the_user_types_in_the_search_form(String authorName) {
        if (searchPage.isSearchFormClickable()) {
            searchPage.searchForAuthor(authorName);
        }
    }

    @Then("the search results should include all books written by {string}.")
    public void the_search_results_should_include_all_books_written_by(String authorName) {
        searchPage.printSearchResult();
    }

}

/*

Feature: Searching for books by Author name
  As a user I want to be able to search for available books by a specifik Author.
  Scenario: Getting to the search page and type "Astrid Lindgren" in the search form.
    Given the user navigates to the search page.
    When the user types "Astrid Lindgren" in the search form.
    Then the search results should include all books written by "Astrid Lindgren".

*/