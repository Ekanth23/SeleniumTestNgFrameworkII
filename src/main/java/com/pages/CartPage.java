package com.pages;

import com.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".cartSection h3")
    List<WebElement> eleCartProduct;

    @FindBy(css = ".totalRow button")
    WebElement btnCheckOut;



    public boolean verifyProductDisplayedInCartPage(String productName)
    {

        return eleCartProduct.stream().anyMatch(cartProduct  -> cartProduct.getText().equalsIgnoreCase(productName));
    }

    public PlaceOrderPage proceedToCheckOut()
    {
        btnCheckOut.click();
        PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
        return placeOrderPage;
    }



}
