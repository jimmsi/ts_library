package se.yrgo.integrations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import se.yrgo.pages.SearchPage;
import se.yrgo.pages.StartPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewSearchFormStepDefinitions {

    private final WebDriver driver = GeneralStepDefinitions.getDriver();
    private final StartPage startPage = new StartPage(driver);
    private final SearchPage searchPage = new SearchPage(driver);

    @When("the user navigates to the book search.")
    public void the_user_navigates_to_the_book_search() {
        startPage.navigateToSearchPage();
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        assertTrue(searchPage.isSearchFormVisible(), "search form is not visible");
    }
}
