package com.AbstractComponents;

import com.pages.CartPage;
import com.pages.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait ww;
    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;


    public CartPage navigateToCartPage() {
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage navigateToOrderPage() {
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }

    public void waitForElementToAppear(By byLocator)
    {
        ww =new WebDriverWait(driver, Duration.ofSeconds(5));
        ww.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public void waitForWebElementToAppear(WebElement ele)
    {
        ww =new WebDriverWait(driver, Duration.ofSeconds(7));
        ww.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementToDissAppear(WebElement ele) throws InterruptedException {
        Thread.sleep(2000);
//        ww =new WebDriverWait(driver, Duration.ofSeconds(5));
//        ww.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void ActionClassSendkeys(WebElement ele, String txt)
    {
        Actions a = new Actions(driver);
        a.sendKeys(ele, txt).build().perform();
    }



}
