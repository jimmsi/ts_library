package se.yrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.yrgo.utils.Utils;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPage {
    private WebDriver driver;
    private By searchForm = By.cssSelector("form.max-w-md.bg-base-300");
    private By searchInputAuthor = By.cssSelector("input[placeholder=\"Author\"]");
    private By searchButton = By.cssSelector("input[value='Search']");
    private By searchResultItems = By.cssSelector("table.table.w-full tbody tr");
    private By noBooksFoundMessage = By.xpath("//*[text()='No books found']");


    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }
    public List<WebElement> getSearchResults() {
        return Utils.waitForVisibilityOfAllElements(driver, searchResultItems, Duration.ofSeconds(10));
    }

    public boolean isSearchFormVisible() {
        WebElement form = Utils.waitForVisibility(driver, searchForm, Duration.ofSeconds(10));
        return form.isDisplayed();
    }

    public boolean isNoBooksFoundMessageVisible() {
        WebElement form = Utils.waitForVisibility(driver, noBooksFoundMessage, Duration.ofSeconds(10));
        return form.isDisplayed();
    }

    public boolean isSearchFormClickable() {
        WebElement form = Utils.waitForElementToBeClickable(driver, searchForm, Duration.ofSeconds(10));
        return form.isDisplayed();
    }

    public void searchForAuthor(String authorName) {
        WebElement inputField = Utils.waitForElementToBeClickable(driver, searchInputAuthor, Duration.ofSeconds(10));
        Utils.clearAndType(inputField, authorName);
        driver.findElement(searchButton).click();
    }

    public boolean isCorrectNoBooksFoundMessageDisplayed(String message) {
        WebElement messageElement = Utils.waitForVisibility(driver, noBooksFoundMessage, Duration.ofSeconds(5));
        return messageElement.getText().equals(message);
    }

    public boolean areResultsEmpty() {
        List<WebElement> resultRows = driver.findElements(searchResultItems);
        return resultRows.isEmpty();
    }
}


