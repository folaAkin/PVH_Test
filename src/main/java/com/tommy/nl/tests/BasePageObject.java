package com.tommy.nl.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.tommy.nl.tests.SharedDriver.getWebDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class BasePageObject {

    private static final Logger LOG = LoggerFactory.getLogger(BasePageObject.class);
    private static final long DRIVER_WAIT_TIME = 15;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePageObject() {
        this.driver = getWebDriver();
        if(driver == null){
            LOG.info("driver is null");
        }else{
            LOG.info("driver is not null");
        }
        this.wait =  new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME));
    }


    public WebElement waitForExpectedElement(final By by) {
        try {
            wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(DRIVER_WAIT_TIME));
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException | TimeoutException e) {
            e.getMessage();
            return null;
        }
    }

    public void moveToElement(final By by) {
        Actions builder = new Actions(getWebDriver());
        builder.moveToElement(getWebDriver().findElement(by));
        builder.perform();
    }

    public WebElement waitForElementToBeClickable(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(waitTimeInSeconds));
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }


}
