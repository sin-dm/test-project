package ru.sindm.test.project.domain.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class YandexMainPage extends PageObject {

    @FindBy(id = "header-search")
    public WebElement searchField;

    @FindBy(xpath = "//*[@data-zone-name='MultiScrollBox']")
    public WebElement adviceBlock;

    @FindBy(xpath = "//*[@data-auto='upsale-content']")
    public WebElement upsaleModalWindow;

    @FindBy(xpath = "//*[@data-zone-name='category-link']")
    private List<WebElement> productCategories;

    @FindBy(xpath = "//*[@data-apiary-widget-name='@MarketNode/CatalogPage']")
    public WebElement categoryPage;

    @FindBy(xpath = "//*[@data-tid='60d9ec65']")
    public WebElement searchResultBlock;

    @FindBy(xpath = "//*[@data-zone-name='clarify-category']")
    public WebElement searchResultCategories;


    public YandexMainPage(WebDriver driver) {
        super(driver);
    }

    public void inputSearchWord(String searchRequest) {
        searchField.sendKeys(searchRequest);
    }

    public void clickSearchButton() {
        searchField.submit();
    }

    public List<WebElement> getAdviceCategories() {
        return adviceBlock.findElements(By.xpath("//*[@data-zone-name='tab']"));
    }

    public List<WebElement> getAdviceCategoryProducts() {
        return adviceBlock.findElement(By.xpath("//*[@data-zone-name='ScrollBox']")).findElements(By.xpath("//*[@data-zone-name='product']"));
    }

    public List<WebElement> getProductCategories() {
        return productCategories.stream().filter(category -> !category.getAttribute("data-zone-data").equals("{\"id\":\"pokupki\"}")).collect(Collectors.toList());
    }
}
