package com.vtiger.pages;

import com.vtiger.Utilities.UIActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadPage extends UIActions {

    public LeadPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    @FindBy(xpath="//input[@name='firstname']")
    WebElement tb_fname;
    @FindBy(name="lastname")
    WebElement tb_lname;
    @FindBy(name="company")
    WebElement tb_comp;

    @FindBy(name="button")
    WebElement btn_save;


    @FindBy(xpath="//td[text()='First Name:']/following::td[1]")
    WebElement txt_fname;

    @FindBy(xpath="//td[text()='Last Name:']/following::td[1]")
    WebElement txt_lname;

    @FindBy(xpath="//td[text()='Company:']/following::td[1]")
    WebElement txt_comp;





    public void createlead_with_mandatory_fields(String fname, String lname,String comp)
    {
        SetFirstName(fname);
        SetLastname(lname);
        SetCompany(comp);
        clickSave();
    }

    public void SetFirstName(String fname)
    {
        SetInput(tb_fname,fname,fname+" has been entered into first name fields");
    }

    public void SetLastname(String lname)
    {
        SetInput(tb_lname,lname,lname+" has been entered into lastname fields");
    }

    public void SetCompany(String comp)
    {
        SetInput(tb_comp,comp,comp+" has been entered into company fields");
    }

    public void clickSave()
    {
        ClickElement(btn_save,"Save button clicked");
    }

    public void verifyleadcreation(String fname, String lname, String comp)
    {
        verifyText(txt_fname,fname,fname+" expected text matched with actual text");
        verifyText(txt_lname,lname,lname+" expected text matched with actual text");
        verifyText(txt_comp,comp,comp+" expected text matched with actual text");
    }



}
