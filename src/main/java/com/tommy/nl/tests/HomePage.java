package com.tommy.nl.tests;

import Props.Props;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

import static com.tommy.nl.tests.SharedDriver.getWebDriver;

public class HomePage  extends  BasePageObject{
    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);
    private static final long DRIVER_WAIT_TIME = 15;

    private By acceptCookiesButton = By.cssSelector(".cookie-notice__agree-button.button");
    private By signInLink = By.cssSelector("[data-testid='sign-in-button']");
    private By createAccountLink = By.cssSelector("[data-testid='register']");
    private By emailAddressTextField = By.cssSelector("#create-account-email");
    private By incorrectEmailErrorMessage = By.cssSelector("#create-account-email-helper-text");
    private By incorrectPasswordErrorMessage = By.cssSelector("#create-account-password-helper-text");
    private By agreeTermsErrorMessage = By.cssSelector(".error-text");
    private By passwordTextField = By.cssSelector("#create-account-password");
    private By agreeTermCheckBox = By.cssSelector("[class='Checkbox agree-terms'] label");
    private By createAccountButton = By.cssSelector("[data-qa='Button-primary-new']");
    private By myAccountTitle = By.cssSelector("h1.my-account__title");
    private By overview = By.cssSelector("h2.my-account__subtitle");
    private By orders = By.cssSelector("div[class='overview__items']>a:nth-child(1)>h3");
    private By details = By.cssSelector("div[class='overview__items']>a:nth-child(2)>h3");
    private By addressBook = By.cssSelector("div[class='overview__items']>a:nth-child(3)>h3");
    private By emailPreferences = By.cssSelector("div[class='overview__items']>a:nth-child(4)>h3");
    private By newsletterPopUpCloseButton = By.cssSelector("[class='newsletter__close button--close']");
    private By addNewAddress = By.cssSelector("[data-testid='address-add-button']");

    private By firstNameField = By.cssSelector("#firstName");
    private By lastNameField = By.cssSelector("#lastName");
    private By addressField = By.cssSelector("#address1");
    private By townOrCityField = By.cssSelector("#city");
    private By postCodeField = By.cssSelector("#zipCode");
    private By saveButton = By.cssSelector("[data-testid='address-save-button']");
    private By addressDropdown = By.cssSelector(".address-book__form select");

    private By countryDrowndown = By.cssSelector(".select__label");
    private By countryOptions = By.cssSelector(".select__option");
    private By goButton = By.cssSelector(".cta-button.button");
    private By switchCountryBanner = By.cssSelector(".country-switch");
    private By switchCountryCloseButton = By.cssSelector(".country-switch>button");
    private By modal = By.cssSelector(".ReactModal__Body--open");

    public  void waitForModalReactNotToDisplay(){
        if(getWebDriver().findElements(modal).size()> 0){
            LOG.info("React modal is displayed");
            acceptCookiesIfDisplayed();
        }

    }


    public void navigateToHomePage(){
        getWebDriver().get(Props.getProp("site.url"));
    }



    public void acceptCookiesIfDisplayed(){
        if(getWebDriver().findElements(acceptCookiesButton).size() > 0){
            waitForExpectedElement(acceptCookiesButton).click();
        }else{
            LOG.info("Cookies banner is not displayed");
        }
    }


    public void clickOnCreateAccountLink(){
        waitForExpectedElement(createAccountLink).click();
    }

    public void clickOnSignInLink() throws InterruptedException {
        Thread.sleep(3000);
        waitForModalReactNotToDisplay();
        waitForElementToBeClickable(signInLink,DRIVER_WAIT_TIME).click();

    }

    public void enterEmailAddress(String email){
        LOG.info("Entering email : "+ email);
        waitForExpectedElement(emailAddressTextField).sendKeys(email);
    }


    public void enterPassword(String password){
        LOG.info("Entering password : "+ password);
        waitForExpectedElement(passwordTextField).sendKeys(password);
    }


    public void clickOnCreateAccountButton(){
        waitForExpectedElement(createAccountButton).click();
    }

    public void clickOnAgreeTermsCheckBox(){
        waitForExpectedElement(agreeTermCheckBox).click();
    }

    public String isMyAccountDisplayed(){
        return  waitForExpectedElement(myAccountTitle).getText();
    }

    public String getIncorrectEmailErrorMsg(){
        return  waitForExpectedElement(incorrectEmailErrorMessage).getText();
    }

    public String getIncorrectPasswordlErrorMsg(){
        return  waitForExpectedElement(incorrectPasswordErrorMessage).getText();
    }

    public String getAgreeTermsErrorMsg(){
        return  waitForExpectedElement(agreeTermsErrorMessage).getText();
    }


    public String isOverviewDisplayed(){
        return  waitForExpectedElement(overview).getText();
    }
    public String isOrdersDisplayed(){
        return  waitForExpectedElement(orders).getText();
    }
    public String isDetailsDisplayed(){
        return  waitForExpectedElement(details).getText();
    }
    public String isAddressBookDisplayed(){
        return  waitForExpectedElement(addressBook).getText();
    }
    public String isEmailPreferencesDisplayed(){
        return  waitForExpectedElement(emailPreferences).getText();
    }

    public void clickOnAddressBook(){
        waitForExpectedElement(addressBook).click();
    }


    public void closeNewsLetterPopUpIfDisplayed() throws InterruptedException {
        if (getWebDriver().findElements(newsletterPopUpCloseButton).size() > 0) {
            waitForExpectedElement(newsletterPopUpCloseButton).click();
        }else{
            LOG.info("News letter pop up is not displayed");
        }

    }
    public List<String> getAllAccountDetails() throws InterruptedException {
        closeNewsLetterPopUpIfDisplayed();
        List<String> accountDetails = new ArrayList<>();
        accountDetails.add(isMyAccountDisplayed());
        accountDetails.add(isOverviewDisplayed());
        accountDetails.add(isOrdersDisplayed());
        accountDetails.add(isDetailsDisplayed());
        accountDetails.add(isAddressBookDisplayed());
        accountDetails.add(isEmailPreferencesDisplayed());
        return accountDetails;
    }


    public void clickOnNewAddress(){
        waitForExpectedElement(addNewAddress).click();
    }

    public void clickOnSaveButton(){
        waitForExpectedElement(saveButton).click();
    }

    public void enterNewAddress(String firstName,String lastName, String address,String townOrCity,String postCode){
        waitForExpectedElement(firstNameField).sendKeys(firstName);
        waitForExpectedElement(lastNameField).sendKeys(lastName);
        waitForExpectedElement(addressField).sendKeys(address);
        waitForExpectedElement(townOrCityField).sendKeys(townOrCity);
        waitForExpectedElement(postCodeField).sendKeys(postCode);
    }

    public String getAddress(){
        Select select = new Select(waitForExpectedElement(addressDropdown));
        return  select.getFirstSelectedOption().getText();
    }

       public void closeSwitchCountryBannerIfDisplayed() throws InterruptedException {
         if(getWebDriver().findElements(switchCountryBanner).size() > 0){
             Thread.sleep(2000);
             waitForExpectedElement(switchCountryCloseButton).click();
         }
         Thread.sleep(2000);
       }

       private void selectCountryDropDown(){
        if(getWebDriver().findElements(switchCountryBanner).size()> 0){
            waitForExpectedElement(switchCountryCloseButton).click();
        }
           waitForElementToBeClickable(countryDrowndown,DRIVER_WAIT_TIME).click();
       }

    public void changeCountry(String country) throws InterruptedException {
        closeSwitchCountryBannerIfDisplayed();
        moveToElement(countryDrowndown);
        acceptCookiesIfDisplayed();
        Thread.sleep(3000);
        selectCountryDropDown();
       List<WebElement> countries = getWebDriver().findElements(countryOptions);
       WebElement countryOption = countries.stream().filter(option-> option.getAttribute("innerText").contains((country)))
                   .findFirst()
                   .orElseThrow(() -> new RuntimeException("Dropdown value not found for : " + country));
        countryOption.click();

    }

     public void clickOnGoButton(){
        waitForExpectedElement(goButton).click();
     }

    }




