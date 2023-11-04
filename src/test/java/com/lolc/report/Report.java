package com.lolc.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.lolc.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report extends TestBase {

    public static ExtentHtmlReporter extentHtmlReporter = null;
    public static ExtentReports extentReports = null;
    public static ExtentTest extentTest = null;
    public static String screenshot = null;

    @BeforeTest
    public void setupReport() {

        extentHtmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")) + "/test-output/LOLCReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);

        extentHtmlReporter.config().setDocumentTitle("LOLC Assignment");
        extentHtmlReporter.config().setReportName("LOLC Report");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);

        extentReports.setSystemInfo("OS", "MacOS");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Release Version", "1.0");
        extentReports.setSystemInfo("Tester", "Harinda Ekanayake");

    }

    @AfterMethod
    public void createReport(ITestResult result) {

        try {

            if (result.getStatus() == ITestResult.SUCCESS) {

                extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Passed", ExtentColor.GREEN));

            } else if (result.getStatus() == ITestResult.SKIP) {

                extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Skipped", ExtentColor.YELLOW));
                extentTest.skip(result.getThrowable());

            } else if (result.getStatus() == ITestResult.FAILURE) {

                extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Failed", ExtentColor.RED));
                extentTest.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShot(result.getName())).build());

            } else {

                System.out.println("Received unexpected result");

            }

        } catch (IOException ex) {

            System.out.println("Cannot access file");
            System.out.println(ex.getStackTrace());

        }

    }

    public String getScreenShot(String test) {

        screenshot = null;

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = format.format(new Date());

            TakesScreenshot capture = (TakesScreenshot) getWebdriver();
            screenshot = capture.getScreenshotAs(OutputType.BASE64);

            File source = OutputType.FILE.convertFromBase64Png(screenshot);
            File destination = new File(System.getProperty("user.dir") + "/resources/screenshot/" + date + "_" + test + ".png");

            FileUtils.copyFile(source, destination);

        } catch (IOException ex) {

            System.out.println("Cannot access file");
            System.out.println(ex.getStackTrace());

        }

        return screenshot;

    }

    public void createTest(String testCaseName, String testCaseDescription) {

        extentTest = extentReports.createTest(testCaseName, testCaseDescription);

    }

    @AfterTest
    public void tearDown() {

        extentReports.flush();
        System.out.println("Report flushed in : " + extentHtmlReporter.getFilePath());
//        getWebdriver().quit();

    }

}
