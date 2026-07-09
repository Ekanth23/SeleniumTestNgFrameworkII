package com.pages;

import com.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> productsList;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;



    By productsListLoc = By.cssSelector(".mb-3");
    By addToCartByloc = By.cssSelector(".card-body button:last-of-type");
    By toastContainerByloc = By.cssSelector("#toast-container");


    public List<WebElement> getProducts() {

        waitForElementToAppear(productsListLoc);
        return productsList;

    }

    public WebElement getProductName(String productName) {
        WebElement prod = getProducts().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return prod;

    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductName(productName);
        prod.findElement(addToCartByloc).click();
        waitForElementToAppear(toastContainerByloc);
        waitForElementToDissAppear(spinner);
    }

    public CartPage navigateToCartPage() {
        cartHeader.click();

        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }


}
