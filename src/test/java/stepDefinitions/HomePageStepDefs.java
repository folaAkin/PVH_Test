package stepDefinitions;

import Props.Props;
import com.tommy.nl.tests.HomePage;
import com.tommy.nl.tests.RandomGenerator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class HomePageStepDefs {

    private HomePage homePage;

    public HomePageStepDefs(HomePage homePage) {
        this.homePage = homePage;
    }

    @Given("user is on Tommy home page")
    public void userIsOnTommyHomePage()  {
        homePage.navigateToHomePage();
        homePage.acceptCookiesIfDisplayed();


    }

    @When("user clicks on sign in button")
    public void userClickOnSignInButton() throws InterruptedException {
        homePage.clickOnSignInLink();
    }

    @And("user enters the following details")
    public void userEnterTheFollowingMandatoryFields(DataTable data) {
        List<String> list = data.asList(String.class);

        if(list.get(0).equalsIgnoreCase("Email")){
            homePage.enterEmailAddress(RandomGenerator.randomEmailAddress(7));
        }else{
            homePage.enterEmailAddress(Props.getProp("invalidEmail"));
        }
        if(list.get(1).equalsIgnoreCase("password")){
            homePage.enterPassword(Props.getProp(list.get(1)));
        }else {
            homePage.enterPassword(Props.getProp("invalidPassword"));
        }
        homePage.clickOnCreateAccountButton();
    }

    @And("user confirms the terms and conditions")
    public void userConfirmsTheTermsAndConditions() {
        homePage.clickOnAgreeTermsCheckBox();
    }

    @And("user clicks on create an account button")
    public void userClicksOnCreateAnAccountButton() {
        homePage.clickOnCreateAccountButton();
    }

    @Then("user should be on the my account page and following details should be displayed")
    public void userShouldBeOnTheMyAccountPageAndFollowingDetailsShouldBeDisplayed(List<String> accountDetails) throws InterruptedException {
        Thread.sleep(5000);
        assertThat(homePage.getAllAccountDetails().equals(accountDetails)).as("Lists are not equal").isTrue();

    }

    @And("user clicks on create account link")
    public void userClicksOnCreateAccountLink() {
        homePage.clickOnCreateAccountLink();
    }

    @And("user enters add new address details with the following")
    public void userEntersAddNewAddressDetailsWithTheFollowing(List<String> addressDetails) throws InterruptedException {
        String firstName = Props.getProp(addressDetails.get(0));
        String lastName = Props.getProp(addressDetails.get(1));
        String address = Props.getProp(addressDetails.get(2));
        String town = Props.getProp(addressDetails.get(3));
        String postcode = Props.getProp(addressDetails.get(4));
        homePage.enterNewAddress(firstName, lastName, address, town, postcode);
    }

    @And("user saves address details")
    public void usersNewAddressDetails() {
        homePage.clickOnSaveButton();
    }

    @Then("the address should be should display (.*)")
    public void theAddressShouldBeShouldDisplay(String address) {
        assertThat(homePage.getAddress()).as("Address is not displayed").isEqualTo(Props.getProp(address));
    }

    @Then("the address should be should displayed {string}")
    public void the_address_should_be_should_displayed(String address) {
        assertThat(homePage.getAddress()).as("Address is not displayed").contains(Props.getProp(address));
    }


    @And("user clicks on address book")
    public void userClicksOnAddressBook() {
        homePage.clickOnAddressBook();
    }

    @And("user clicks on add new address")
    public void userClicksOnAddNewAddress() throws InterruptedException {
        homePage.closeNewsLetterPopUpIfDisplayed();
        homePage.clickOnNewAddress();
    }

    @Then("the following error messages should be displayed")
    public void theFollowingErrorMessagesShouldBeDisplayed(List<String> errorMessages) {
        assertThat(homePage.getIncorrectEmailErrorMsg()).as("Incorrect email error message is not displayed").isEqualTo(errorMessages.get(0));
        assertThat(homePage.getIncorrectPasswordlErrorMsg()).as("Incorrect password error message is not displayed").isEqualTo(errorMessages.get(1));
        assertThat(homePage.getAgreeTermsErrorMsg()).as( "Agree terms error message:is not displayed").isEqualTo(errorMessages.get(2));
    }

    @And("I change country to {string}")
    public void iChangeCountryTo(String country) throws InterruptedException {
        homePage.changeCountry(country);
        homePage.clickOnGoButton();
        homePage.acceptCookiesIfDisplayed();
    }
}
