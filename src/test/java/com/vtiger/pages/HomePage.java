package com.vtiger.pages;

import com.vtiger.Utilities.UIActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends UIActions {



    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(linkText="Home")
    WebElement lnk_Home;

    @FindBy(linkText="Logout")
    WebElement lnk_Logout;

    @FindBy(linkText="New Lead")
    WebElement lnk_NewLead;


    public void verifyHome()
    {
       ElementExist(lnk_Home,"Home Tab is displaying on Home Page");
    }

    public void verifyLogout()
    {

        ElementExist(lnk_Logout,"Link Logout exist on Home Page");
    }

    public void ClickLogout()
    {

        ClickElement(lnk_Logout,"Logout link clicked");
    }

    public void ClickNewLead()
    {

        ClickElement(lnk_NewLead,"New Lead link clicked");
    }
}
