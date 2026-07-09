package tests;

import com.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.*;

import java.time.Duration;

public class SubmitOrderTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String productName = "ZARA COAT 3";

        WebDriverWait ww =new WebDriverWait(driver, Duration.ofSeconds(5));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        com.pages.ProductCatalogue productCatalogue =landingPage.launchApplication("autotester001@gmail.com", "Pass12345");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage =productCatalogue.navigateToCartPage();

        boolean match = cartPage.verifyProductDisplayedInCartPage(productName);
        Assert.assertTrue(match);
        PlaceOrderPage placeOrderPage=cartPage.proceedToCheckOut();


        placeOrderPage.selectCountry("India", "123", "autotester001");
        OrderConfirmationPage orderConfirmationPage=placeOrderPage.placeOrder();

        String actualConfirmationMessage = orderConfirmationPage.orderConfirmation();
        Assert.assertEquals(actualConfirmationMessage, "THANKYOU FOR THE ORDER.");

        driver.quit();
    }
}
