package testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.resources.ExtentReportTestNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

// Ctrl+ I used to get all the umimplementd methods
public class Listeners extends BaseClass implements ITestListener {
    ExtentReports extentRepo=ExtentReportTestNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extendTestThreadLocal = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
       test = extentRepo.createTest(result.getMethod().getMethodName());
       extendTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        extendTestThreadLocal.get().log(Status.PASS, "Test Passed");
    }


    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        extendTestThreadLocal.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String screenshotPath = null;
        try {
            screenshotPath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extendTestThreadLocal.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        extendTestThreadLocal.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extentRepo.flush();
    }
}
