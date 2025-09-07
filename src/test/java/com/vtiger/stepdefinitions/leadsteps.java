package com.vtiger.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class leadsteps extends basesteps {

    @When("user click on new lead")
    public void click_on_new_lead() {
       hp.ClickNewLead();
        System.out.println("hello Laxmi");
    }
    @Then("fill all mandatory fields and click on save")
    public void create_lead() {
        ldp.createlead_with_mandatory_fields(dt.get(TCName).get("FirstName"),dt.get(TCName).get("Lastname"),dt.get(TCName).get("Company"));
    }

    @Then("lead should be created successfully")
    public void verify_lead_Creation() {

        ldp.verifyleadcreation(dt.get(TCName).get("FirstName"),dt.get(TCName).get("Lastname"),dt.get(TCName).get("Company"));
    }
}
