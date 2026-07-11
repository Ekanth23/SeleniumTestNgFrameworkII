package com.pages;

import com.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;
    public OrderPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "tr td:nth-of-type(2)")
    List<WebElement> eleOrderPageProduct;

    public boolean verifyProductInOrderPageHistory(String product)
    {
       return eleOrderPageProduct.stream().anyMatch(orderedProduct -> orderedProduct.getText().equalsIgnoreCase(product));

    }







}
