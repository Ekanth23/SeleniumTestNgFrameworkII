package tests;

import com.pages.CartPage;
import com.pages.OrderConfirmationPage;
import com.pages.PlaceOrderPage;
import com.pages.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.BaseClass;

import java.io.IOException;

public class ErrorValidationTest extends BaseClass {
    @Test
    public void loginErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        landingPage.loginApplication("autotester002@gmail.com", "Pass");
        String actualLoginErrorMessage = landingPage.getLoginErrorMessage();
        Assert.assertEquals(actualLoginErrorMessage, "Incorrect email or password.");

    }

    @Test
    public void productErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("autotester003@gmail.com", "Pass12345");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage =productCatalogue.navigateToCartPage();

        boolean match = cartPage.verifyProductDisplayedInCartPage("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}
