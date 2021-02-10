package org.example;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Scenario_Steps {

    WebDriver webDriver = Driver.webDriver;

    @Step("Navigate to <url>")
    public void navigateTo(String url) {
        Driver.webDriver.get(url);
        webDriver.manage().window().maximize();
    }

    @Step("Search item <productName>")
    public void userSearchesProduct(String productName) {
        webDriver.findElement(By.name(System.getenv("search_bar"))).sendKeys(productName);
    }

    @Step("Filter product with <filter>")
    public void userFiltersSearchResult(String filter) {
        WebElement element = new WebDriverWait(webDriver, 60)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(System.getenv(filter))));
        element.click();
    }

    @Step("Click element <locator>")
    public void clickElement(String locator) {
        WebElement element = new WebDriverWait(webDriver, 60)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(System.getenv(locator))));
        element.click();
    }

    @Step("Select variant <variant>")
    public void selectVariant(String variant){
        scrollDown();
        WebElement element = new WebDriverWait(webDriver, 60)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(System.getenv(variant))));
        element.click();

    }
    @Step("User enters <total> number of product")
    public void userEntersTotal(String total) throws InterruptedException {
        scrollDown();
        WebElement element = new WebDriverWait(webDriver, 80)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(System.getenv("product_number"))));
        element.click();
        element.sendKeys(total);
        Thread.sleep(4000);
    }

    @Step("Add To Basket")
    public void userAddsToBasket() {
        scrollDown();
        WebElement element = new WebDriverWait(webDriver, 60)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(System.getenv("add_to_basket"))));
        element.click();
    }

    @Step("Go to Basket")
    public void goToBasket() throws InterruptedException {
        Actions actions = new Actions(webDriver);

        WebElement element = new WebDriverWait(webDriver, 80)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(System.getenv("go_to_basket"))));

        actions.moveToElement(element).perform();

        WebElement basket = new WebDriverWait(webDriver, 80)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(System.getenv("click_to_basket"))));

        basket.click();
    }

    public void scrollDown(){
        JavascriptExecutor jsx = (JavascriptExecutor)webDriver;
        jsx.executeScript("window.scrollBy(0,500)", "");
    }

}
