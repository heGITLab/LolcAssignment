package com.lolc.pages.web;

import com.lolc.common.BasicCommands;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SLHomePage extends BasicCommands {

    private String class_label_products = "title";

    //  Locators
    public WebElement getProductsLabel() {

        return getWebdriver().findElement(By.className(class_label_products));

    }
}
