package se.yrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.yrgo.utils.Utils;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private By searchForm = By.cssSelector("form.max-w-md.bg-base-300");
    private By searchInputAuthor = By.xpath("//input[@placeholder='Author']");
    private By searchResultItems = By.cssSelector("table.table.w-full tbody tr");

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

    public boolean isSearchFormClickable() {
        WebElement form = Utils.waitForElementToBeClickable(driver, searchForm, Duration.ofSeconds(10));
        return form.isDisplayed();
    }

    public void searchForAuthor(String authorName) {
        WebElement inputField = Utils.waitForElementToBeClickable(driver, searchInputAuthor, Duration.ofSeconds(10));
        Utils.clearAndType(inputField, authorName);
    }

    public void printSearchResult () {
        List<WebElement> resultRows = driver.findElements(searchResultItems);
        for (WebElement row : resultRows) {
            String title = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            String author = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            System.out.println("Title: " + title + ", Author: " + author);
        }
    }

}


