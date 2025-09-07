package com.vtiger.stepdefinitions;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class loginsteps extends basesteps {

  @Before
  public void getScenarioName(Scenario scenario)
  {
      if(htmlReporter==null)
          createExtentReport();
      TCName = scenario.getName();
      report = extent.createTest(TCName);
  }
  @After
  public void tierdown()
  {
      extent.flush();
      if(driver!=null)
      {
          driver.quit();
      }
  }




    @Given("user should be on login page")
    public void user_should_be_on_login_page() {
        initiation();
         lp = new LoginPage(driver);
         hp = new HomePage(driver);
        ldp = new LeadPage(driver);
    }
    @When("user enters valid credentials and click on login button")
    public void user_enters_valid_credentials_and_click_on_login_button() {
         lp.login(dt.get(TCName).get("Userid"),dt.get(TCName).get("Password"));
    }
    @Then("user should be navigated to home page")
    public void user_should_be_navigated_to_home_page() {
           hp.verifyHome();
    }
    @Then("user can see the logout link on home page")
    public void user_can_see_the_logout_link_on_home_page() {
         hp.verifyLogout();
    }

    @When("user enters invalid credentials and click on login button")
    public void user_enters_invalid_credentials_and_click_on_login_button() {
        lp.login(dt.get(TCName).get("Userid"),dt.get(TCName).get("Password"));
    }
    @Then("user should be login page")
    public void user_should_be_login_page() {
       lp.verifyLogin();

    }
    @Then("user can see the error message")
    public void user_can_see_the_error_message() {
        lp.verifyErrorMsg();
    }

    @When("user enters invalid credentials userid as {string} and password as {string} and click on login button")
    public void user_enters_invalid_credentials_userid_as_and_password_as_and_click_on_login_button(String uid, String pwd) {
        lp.login(uid,pwd);
    }

}
