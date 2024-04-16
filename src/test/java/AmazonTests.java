import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmazonTests extends BaseTest {
    private By areNotARobotForm = By.xpath("//div[@class='a-box a-color-offset-background']");
    private By tryDifferentImage = By.xpath("//a[@onclick='window.location.reload()']");
    private By searchInput = By.id("twotabsearchtextbox");
    private By searchIcon = By.id("nav-search-submit-button");
    private By itemPrices = By.xpath("//div//child::span[@class='a-price']");
    private By itemLinks = By.xpath("//div//child::span[@class='a-price']//parent::a");
    private By selectedItemPrice = By.xpath("//span[@class='a-price a-text-price a-size-medium']");
    private By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");
    private By skipGuaranty = By.xpath("//input[@aria-labelledby='attachSiNoCoverage-announce']");
    private By cartButton = By.xpath("//a[@id='nav-cart']");
    private By amountInCart = By.id("nav-cart-count");
    private By cartItemPrice = By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']");
    private By deleteItem = By.xpath("//input[@data-action='delete']");
    private Actions actions = new Actions();


    @Test
    public void FisrtTestCase(){
        SkipYouAreNotARobot();
        actions.SendText(driver.findElement(searchInput), "Samsung Galaxy Note 20");
        actions.Click(driver.findElement(searchIcon));

        List<WebElement> prices = driver.findElements(itemPrices);
        List<WebElement> links = driver.findElements(itemLinks);
        double priceFromListItem = getPriceFromText(prices.getFirst());

        Assert.assertTrue(links.getFirst().isDisplayed());

        actions.Click(links.getFirst());
        double priceFromItem = getPriceFromText(driver.findElement(selectedItemPrice));

        Assert.assertEquals(priceFromItem, priceFromListItem);

        actions.Click(driver.findElement(addToCartButton));
        actions.Click(driver.findElement(skipGuaranty));
        wait.until(ExpectedConditions.textToBe(amountInCart, "1"));
        actions.Click(driver.findElement(cartButton));
        double priceFromCart= getPriceFromText(driver.findElement(cartItemPrice));

        Assert.assertEquals(priceFromCart, priceFromListItem);

        actions.Click(driver.findElement(deleteItem));
    }

    @Test
    public void SecondTestCase(){
        SkipYouAreNotARobot();
        actions.SendText(driver.findElement(searchInput), "Samsung Galaxy S20 FE 5G");
        actions.Click(driver.findElement(searchIcon));

        List<WebElement> prices = driver.findElements(itemPrices);
        List<WebElement> links = driver.findElements(itemLinks);
        double priceFromListItem = getPriceFromText(prices.getFirst());

        Assert.assertTrue(links.getFirst().isDisplayed());

        actions.Click(links.getFirst());
        double priceFromItem = getPriceFromText(driver.findElement(selectedItemPrice));

        Assert.assertEquals(priceFromItem, priceFromListItem);

        actions.Click(driver.findElement(addToCartButton));
        actions.Click(driver.findElement(skipGuaranty));
        wait.until(ExpectedConditions.textToBe(amountInCart, "1"));
        actions.Click(driver.findElement(cartButton));
        double priceFromCart= getPriceFromText(driver.findElement(cartItemPrice));

        Assert.assertEquals(priceFromCart, priceFromListItem);

        actions.Click(driver.findElement(deleteItem));
    }

    private double getPriceFromText(WebElement priceElement) {
        wait.until(ExpectedConditions.visibilityOf(priceElement));
        String price = priceElement.getText().replace("\n", ".");
        Pattern pattern = Pattern.compile("\\d+(,\\d{3})*(\\.\\d+)?");
        Matcher matcher = pattern.matcher(price);
        if (matcher.find()) {
            String numericValue = matcher.group().replaceAll(",", "");
            return Double.parseDouble(numericValue);
        } else {
            return -1;
        }
    }

    private void SkipYouAreNotARobot(){
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(areNotARobotForm)));
            actions.Click(driver.findElement(tryDifferentImage));
        } catch (NoSuchElementException ex) {

        }
    }
}
