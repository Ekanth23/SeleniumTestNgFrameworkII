package tests;

import com.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testComponents.BaseClass;

import java.io.IOException;
import java.util.*;

public class SubmitOrderTest extends BaseClass {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = "Purchase")
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("pass"));

        productCatalogue.addProductToCart(input.get("prod"));
        CartPage cartPage = productCatalogue.navigateToCartPage();

        boolean match = cartPage.verifyProductDisplayedInCartPage(input.get("prod"));
        Assert.assertTrue(match);
        PlaceOrderPage placeOrderPage = cartPage.proceedToCheckOut();


        placeOrderPage.selectCountry("India", "123", "autotester001");
        OrderConfirmationPage orderConfirmationPage = placeOrderPage.placeOrder();

        String actualConfirmationMessage = orderConfirmationPage.orderConfirmation();
        Assert.assertEquals(actualConfirmationMessage, "THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("autotester001@gmail.com", "Pass12345");
        OrderPage orderPage = productCatalogue.navigateToOrderPage();
        Assert.assertTrue(orderPage.verifyProductInOrderPageHistory(productName));
        ;

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");

        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

    //        return new Object[][]{{"autotester001@gmail.com", "Pass12345", "ZARA COAT 3"}, {"autotester002@gmail.com", "Pass12345", "ADIDAS ORIGINAL"}};

//    HashMap<String, String> map = new HashMap<>();
//        map.put("email", "autotester001@gmail.com");
//        map.put("pass", "Pass12345");
//        map.put("prod", "ZARA COAT 3");
//
//    HashMap<String, String> map1 = new HashMap<>();
//        map1.put("email", "autotester002@gmail.com");
//        map1.put("pass", "Pass12345");
//        map1.put("prod", "ADIDAS ORIGINAL");

    }
