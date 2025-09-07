package com.vtiger.pages;

import com.vtiger.Utilities.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage extends UIActions {


    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

   // String userid = "user_name";

  //  By userid = By.xpath("//input[@name='user_name']");

    @FindBy(xpath="//input[@name='user_name']")
    WebElement tb_userid;
    @FindBy(name="user_password")
    WebElement tb_password;
    @FindBy(name="Login")
    WebElement btn_login;

    @FindBy(xpath="//*[contains(text(),'You must specify a valid username and password.')]")
    WebElement txt_ErrorMsg;





    public void login(String uid, String pwd)
    {
        SetUserID(uid);
        SetPassword(pwd);
        clickLogin();
    }

    public void SetUserID(String uid)
    {
        SetInput(tb_userid,uid,uid+" has been entered into user name fields");
    }

    public void SetPassword(String pwd)
    {
       SetInput(tb_password,pwd,pwd+" has been entered into password fields");
    }

    public void clickLogin()
    {
        ClickElement(btn_login,"Login button clicked");
    }

    public void verifyLogin()
    {
       ElementExist(btn_login,"Login button exist on login page");
    }

    public void verifyErrorMsg()
    {
       ElementExist(txt_ErrorMsg,"Error message validated successfully");
    }


}
