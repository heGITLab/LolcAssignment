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

    //  Operations
    public void validateSuccessLogin(String username, String password) {

        type(getUsernameField(), username);
        type(getPasswordField(), password);
        click(getLoginButton());
        waitUntilLoad(By.className("title"), Util.WAIT_5_SEC);
        Assert.assertEquals(slHomePage.getProductsLabel().getText(),"Products");

    }
}
