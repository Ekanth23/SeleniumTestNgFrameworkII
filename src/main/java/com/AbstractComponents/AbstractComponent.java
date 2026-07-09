package com.AbstractComponents;

import com.pages.CartPage;
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


    public CartPage navigateToCartPage() {
        cartHeader.click();
        return null;
    }

    public void waitForElementToAppear(By byLocator)
    {
        ww =new WebDriverWait(driver, Duration.ofSeconds(5));
        ww.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
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
