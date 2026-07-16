package com.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir")+ "//reports//index.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("Web Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extReports = new ExtentReports();
        extReports.attachReporter(sparkReporter);
        extReports.setSystemInfo("Tester", "Ekanth");
        return extReports;

    }
}
