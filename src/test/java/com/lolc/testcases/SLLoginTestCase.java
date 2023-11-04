package com.lolc.testcases;

import com.lolc.pages.web.SLLoginPage;
import com.lolc.report.Report;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SLLoginTestCase extends Report {

    SLLoginPage slLoginPage = new SLLoginPage();

    @BeforeTest
    public void initialLoad() {

        setupWebdriver("chrome");

    }

    @Test
    public void verifySuccessLogin() {

        createTest("Verify Success Login", "Verify Success Login Test Case");
        slLoginPage.validateSuccessLogin("standard_user","secret_sauce");

    }

}
