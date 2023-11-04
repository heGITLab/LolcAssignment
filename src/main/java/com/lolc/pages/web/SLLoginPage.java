package com.lolc.pages.web;

import com.lolc.common.BasicCommands;
import com.lolc.utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SLLoginPage extends BasicCommands {

    SLHomePage slHomePage = new SLHomePage();

    private String id_field_username = "user-name";
    private String id_field_password = "password";
    private String id_btn_login = "login-button";
    private String class_label_logo = "login_logo";
    private String xpath_label_login_validation = "//h3[@data-test='error']";

    //  Locators
    public WebElement getUsernameField() {

        return getWebdriver().findElement(By.id(id_field_username));

    }

    public WebElement getPasswordField() {

        return getWebdriver().findElement(By.id(id_field_password));

    }

    public WebElement getLoginButton() {

        return getWebdriver().findElement(By.id(id_btn_login));

    }

    public WebElement getLogoLabel() {

        return  getWebdriver().findElement(By.className(class_label_logo));

    }

    public WebElement getLoginValidationLabel() {

        return getWebdriver().findElement(By.xpath(xpath_label_login_validation));

    }

    //  Operations
    public void validateSuccessLogin(String username, String password) {

        type(getUsernameField(), username);
        type(getPasswordField(), password);
        click(getLoginButton());
        waitUntilLoad(By.className("title"), Util.WAIT_5_SEC);
        Assert.assertEquals(slHomePage.getProductsLabel().getText(),"Products");

    }

    public void validateInvalidUsername(String username, String password) {

        type(getUsernameField(), username);
        type(getPasswordField(), password);
        click(getLoginButton());
        waitUntilLoad(By.className("login_logo"), Util.WAIT_5_SEC);
        Assert.assertEquals(getLoginValidationLabel().getText(),"Epic sadface: Username and password do not match any user in this service");

    }

    public void validateInvalidPassword(String username, String password) {

        type(getUsernameField(), username);
        type(getPasswordField(), password);
        click(getLoginButton());
        waitUntilLoad(By.className("login_logo"), Util.WAIT_5_SEC);
        Assert.assertEquals(getLoginValidationLabel().getText(),"Epic sadface: Username and password do not match any user in this service");

    }

    public void validateEmptyUsername(String username, String password) {

        type(getUsernameField(), username);
        type(getPasswordField(), password);
        click(getLoginButton());
        waitUntilLoad(By.className("login_logo"), Util.WAIT_5_SEC);
        Assert.assertEquals(getLoginValidationLabel().getText(),"Epic sadface: Username is required");

    }

    public void validateEmptyPassword(String username, String password) {

        type(getUsernameField(), username);
        type(getPasswordField(), password);
        click(getLoginButton());
        waitUntilLoad(By.className("login_logo"), Util.WAIT_5_SEC);
        Assert.assertEquals(getLoginValidationLabel().getText(),"Epic sadface: Password is required");

    }

}
