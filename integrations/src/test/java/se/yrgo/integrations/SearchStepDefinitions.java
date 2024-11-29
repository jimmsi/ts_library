package se.yrgo.integrations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import se.yrgo.pages.SearchPage;
import se.yrgo.pages.StartPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchStepDefinitions  {

    public static StartPage startPage;
    public static SearchPage searchPage;

    @And("the user navigates to the book search.")
    public void the_user_navigates_to_the_book_search() {
        startPage = GeneralStepDefinitions.getStartPage();
        searchPage = startPage.navigateToSearchPage();
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        assertTrue(searchPage.isSearchFormVisible(), "search form is not visible");
    }

    @When("the user types {string} in the search form.")
    public void the_user_types_in_the_search_form(String authorName) {
        if (searchPage.isSearchFormClickable()) {
            searchPage.searchForAuthor(authorName);
        }
    }

    @Then("the search results should include all books written by {string}.")
    public void the_search_results_should_include_all_books_written_by(String authorName) {
        List<WebElement> resultRows = searchPage.getSearchResults();
        assertFalse(resultRows.isEmpty(), "No search results found for author: " + authorName);
         for (WebElement row : resultRows) {
            String displayedAuthor = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            assertEquals(authorName, displayedAuthor, "Found a book by a different author");
        }
    }

    @Then("a message should be displayed saying {string}.")
    public void a_message_should_be_displayed_saying(String message) {
        boolean areResultsEmpty = searchPage.areResultsEmpty();
        assertTrue(areResultsEmpty, "Expected no results but found one or more");
        assertTrue(searchPage.isNoBooksFoundMessageVisible(), "'No books found'-Message is not visible");
        assertTrue(searchPage.isCorrectNoBooksFoundMessageDisplayed(message), "Incorrect 'No books found'-Message");
    }

}
