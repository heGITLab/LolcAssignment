package com.lolc.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TestBase {

    public static Properties properties = null;
    public static WebDriver driver = null;
    public String url;
    public String payLoadObject;

    public static Properties readProperties() {

        try {

            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/resources/configurations/config.properties");
            properties = new Properties();
            properties.load(file);

        } catch (IOException ex) {

            System.out.println("Cannot access file.");
            System.out.println(ex.getStackTrace());

        }

        return properties;
    }

    public static void setupWebdriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            System.setProperty(readProperties().getProperty("chrome_driver"), readProperties().getProperty("chrome_driver_path"));
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {

            System.setProperty(readProperties().getProperty("firefox_driver"), readProperties().getProperty("firefox_driver_path"));
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("safari")) {

            driver = new SafariDriver();

        } else {

            System.out.println("Please enter either chrome, firefox or safari");

        }

        driver.manage().window().maximize();
        driver.get(readProperties().getProperty("website"));

    }

    public static WebDriver getWebdriver() {

        return driver;

    }

    public String getUrl() {

        url = readProperties().getProperty("url");

        return url;

    }

    public String getPayloadString(String fileName) {

        try {

            String filePath = System.getProperty("user.dir") + "/src/payload/" + fileName;
            payLoadObject = new String(Files.readAllBytes(Paths.get(filePath)));

        } catch (IOException e) {

            e.printStackTrace();

        }

        return payLoadObject;

    }
}
