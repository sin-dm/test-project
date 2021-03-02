import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sindm.test.project.domain.pages.SearchResultPage;
import ru.sindm.test.project.domain.pages.YandexMainPage;
import ru.sindm.test.project.domain.utils.model.ProductData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YandexMainPageTest {

    private static ChromeDriverService service;
    private static WebDriver driver;
    private static final String baseUrl = "https://market.yandex.ru/";

    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/test/resources/drivers/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @BeforeMethod
    public void setUpDriver() {
        driver = new ChromeDriver(service);
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }

    @Test(description = "Тест проверки поиска")
    public void testSearch() {
        YandexMainPage mainPage = new YandexMainPage(driver);
        SearchResultPage resultPage = new SearchResultPage(driver);
        driver.get(baseUrl);
        mainPage.inputSearchWord("test");
        mainPage.clickSearchButton();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        Assert.assertEquals(resultPage.getSearchResultItemsCount(), 48);
        WebElement firstProduct = resultPage.getProductWithHighPriority();
        String firstProductSku = new Gson().fromJson(firstProduct.getAttribute("data-zone-data"), ProductData.class).getSkuId();
        Assert.assertEquals(firstProductSku, "100660484027");
    }

    @Test(description = "Тест добавления продукта из блока рекомендаций в корзину")
    public void testAddProductFromAdviceBlockToCard() {
        YandexMainPage mainPage = new YandexMainPage(driver);
        driver.get(baseUrl);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        Assert.assertTrue(mainPage.getAdviceCategories().get(0).isDisplayed());
        List<WebElement> adviceCategoryProducts = mainPage.getAdviceCategoryProducts();
        Assert.assertEquals(adviceCategoryProducts.size(), 20);
        adviceCategoryProducts.get(0).findElement(By.xpath("//*[@data-tid='42de86b 2554fbc8']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        Assert.assertTrue(mainPage.upsaleModalWindow.isDisplayed());
    }

    @Test(description = "Тест перехода в категорию продукта")
    public void testSelectProductCategory() {
        YandexMainPage mainPage = new YandexMainPage(driver);
        driver.get(baseUrl);
        mainPage.getProductCategories().get(0).click();
        Assert.assertTrue(mainPage.categoryPage.isDisplayed());
    }
}
