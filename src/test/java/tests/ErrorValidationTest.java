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

    String productName = "ZARA COAT 3";
    @Test(groups = {"loginError"})
    public void loginErrorValidation() throws IOException, InterruptedException {

        landingPage.loginApplication("autotester002@gmail.com", "Pass");
        String actualLoginErrorMessage = landingPage.getLoginErrorMessage();
        Assert.assertEquals(actualLoginErrorMessage, "Incorrect email password."); //provided incorrext assertion

    }

    @Test
    public void productErrorValidation() throws IOException, InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginApplication("autotester003@gmail.com", "Pass12345");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage =productCatalogue.navigateToCartPage();

        boolean match = cartPage.verifyProductDisplayedInCartPage("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}
