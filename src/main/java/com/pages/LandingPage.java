package com.pages;

import com.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#userEmail")
    WebElement eleEmail;

    @FindBy(css = "#userPassword")
    WebElement elePass;

    @FindBy(css = "#login")
    WebElement eleLogin;





    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");

    }

    public ProductCatalogue launchApplication(String txtEmail, String txtPass)
    {
        eleEmail.sendKeys(txtEmail);
        elePass.sendKeys(txtPass);
        eleLogin.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }




}
