package com.pages;

import com.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrderPage extends AbstractComponent {

    WebDriver driver;

    public PlaceOrderPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder*='Country']")
    WebElement eleCountrydd;

    @FindBy(css = ".ta-results button:last-of-type")
            WebElement myCountry;
    @FindBy(xpath = "//div[text()='CVV Code ']/self::div/following-sibling::input")
            WebElement cvvField;
    @FindBy(xpath = "//div[text()='Name on Card ']/self::div/following-sibling::input")
            WebElement customerNameField;

    @FindBy(css = ".action__submit")
    WebElement btnSubmit;

    By ddResult = By.cssSelector(".ta-results");


    public OrderConfirmationPage placeOrder()
    {

        btnSubmit.click();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        return orderConfirmationPage;
    }

    public void selectCountry(String country, String cvv, String customerName)
    {
        ActionClassSendkeys(eleCountrydd, country);
        waitForElementToAppear(ddResult);
        myCountry.click();
        cvvField.sendKeys(cvv);
        customerNameField.sendKeys(customerName);

    }


}
