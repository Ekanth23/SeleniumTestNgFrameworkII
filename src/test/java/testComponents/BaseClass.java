package testComponents;

import com.pages.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\resources\\GlobalData.properties");
        prop.load(fis);
        String browserName=prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            driver=new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        } else if(browserName.equalsIgnoreCase("edge"))
        {
            driver=new EdgeDriver();
        } else{
            System.out.println("Please provide a valid browser name in GlobalData.properties file");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;

    }


    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();
    }
}
