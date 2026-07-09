package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait ww =new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.cssSelector("#userEmail")).sendKeys("autotester001@gmail.com");
        driver.findElement(By.cssSelector("#userPassword")).sendKeys("Pass12345");
        driver.findElement(By.cssSelector("#login")).click();

        java.util.List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod =products.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        ww.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        boolean match =driver.findElements(By.cssSelector(".cartSection h3")).stream()
                .anyMatch(cartProduct  -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));

        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();



        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Country']")), "india").build().perform();
        ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
        driver.findElement(By.xpath("//div[text()='CVV Code ']/self::div/following-sibling::input")).sendKeys("123");
        driver.findElement(By.xpath("//div[text()='Name on Card ']/self::div/following-sibling::input")).sendKeys("autotester001");


        driver.findElement(By.cssSelector(".action__submit")).click();

       String expectedConfirmationMessage =driver.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertEquals(expectedConfirmationMessage, "THANKYOU FOR THE ORDER.");

        driver.quit();
    }
}
