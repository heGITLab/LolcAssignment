package com.lolc.testcases.web;

import com.lolc.pages.web.SLLoginPage;
import com.lolc.report.Report;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SLLoginTestCase extends Report {

    SLLoginPage slLoginPage = new SLLoginPage();

    @BeforeMethod
    public void initialLoad() {

        setupWebdriver("chrome");

    }

    @Test(priority = 1)
    public void verifySuccessLogin() {

        createTest("Verify Success Login", "Verify Success Login Test Case");
        slLoginPage.validateSuccessLogin("standard_user","secret_sauce");

    }

    @Test(priority = 2)
    public void verifyInvalidUsername() {

        createTest("Verify Invalid Username", "Verify Invalid Username Test Case");
        slLoginPage.validateInvalidUsername("invalid_user","secret_sauce");

    }

    @Test(priority = 3)
    public void verifyInvalidPassword() {

        createTest("Verify Invalid Password", "Verify Invalid Password Test Case");
        slLoginPage.validateInvalidPassword("standard_user","invalid_password");

    }

    @Test(priority = 4)
    public void verifyEmptyUsername() {

        createTest("Verify Empty Username", "Verify Empty Username Test Case");
        slLoginPage.validateEmptyUsername("","secret_sauce");

    }

    @Test(priority = 5)
    public void verifyEmptyPassword() {

        createTest("Verify Empty Password", "Verify Empty Password Test Case");
        slLoginPage.validateEmptyPassword("standard_user","");

    }

    @AfterMethod
    public void closure() {

        getWebdriver().quit();

    }

}
