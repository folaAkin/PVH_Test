package com.tommy.nl.tests;

import Props.Props;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SharedDriver {
    private static final Logger LOG = LoggerFactory.getLogger(SharedDriver.class);
    private static WebDriver REAL_DRIVER;
    private static final  Thread CLOSE_THREAD  =  new Thread() {

        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };


    static {
        try {
            Props.loadConfigProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lunchChromeDriver();
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public static  WebDriver getWebDriver(){

        return REAL_DRIVER;
    }

    public static void lunchChromeDriver(){
        WebDriverManager.chromedriver().setup();
        REAL_DRIVER = new ChromeDriver();
        LOG.info("Chrome driver is initialising");
        SharedDriver.getWebDriver().manage().deleteAllCookies();
        LOG.info("Clearing browser cookies");
        //REAL_DRIVER.manage().window().maximize();
    }
}
