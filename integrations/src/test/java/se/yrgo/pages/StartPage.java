package se.yrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StartPage {

    private WebDriver driver;
    private By findBookButton = By.linkText("FIND A BOOK");

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToStartPage () {
        driver.get("http://frontend");
    }

    public boolean isOnStartPage() {
        return "The Library".equals(driver.getTitle());
    }

    public SearchPage navigateToSearchPage() {
        driver.findElement(findBookButton).click();
        return new SearchPage(driver);
    }



}
