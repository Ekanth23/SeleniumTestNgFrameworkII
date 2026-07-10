package tests;

import com.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseClass;

import java.io.IOException;
import java.util.*;

import java.time.Duration;

public class SubmitOrderTest extends BaseClass {
    @Test
    public void submitOrder() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("autotester001@gmail.com", "Pass12345");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage =productCatalogue.navigateToCartPage();

        boolean match = cartPage.verifyProductDisplayedInCartPage(productName);
        Assert.assertTrue(match);
        PlaceOrderPage placeOrderPage=cartPage.proceedToCheckOut();


        placeOrderPage.selectCountry("India", "123", "autotester001");
        OrderConfirmationPage orderConfirmationPage=placeOrderPage.placeOrder();

        String actualConfirmationMessage = orderConfirmationPage.orderConfirmation();
        Assert.assertEquals(actualConfirmationMessage, "THANKYOU FOR THE ORDER.");
    }
}
