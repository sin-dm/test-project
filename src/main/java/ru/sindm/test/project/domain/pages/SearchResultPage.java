package ru.sindm.test.project.domain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends PageObject {

    @FindBy(xpath = "//*[@data-zone-name='snippetList']")
    private WebElement searchResultsBlock;

    public int getSearchResultItemsCount() {
        int productsCount = searchResultsBlock.findElements(By.xpath("//*[@data-autotest-id='product-snippet']")).size();
        int offersCount = searchResultsBlock.findElements(By.xpath("//*[@data-autotest-id='offer-snippet']")).size();
        return productsCount + offersCount;
    }

    public WebElement getProductWithHighPriority() {
        return searchResultsBlock.findElements(By.xpath("//*[@data-autotest-id='product-snippet']")).get(0);
    }

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }
}
