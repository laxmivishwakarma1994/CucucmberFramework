package com.vtiger.Utilities;

import com.vtiger.stepdefinitions.basesteps;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class UIActions {

    public WebDriver driver;
    public WebDriverWait wait;

    public UIActions(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void SetInput(WebElement elm, String value, String msg)
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.clear();
            elm.sendKeys(value);
            basesteps.report.pass(msg+ getScreenshot());

        }
        catch (Exception e)
        {
            basesteps.report.fail(e.getMessage()+ getScreenshot());
            e.printStackTrace();

        }
    }

    public void ClickElement(WebElement elm, String msg)
    {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elm));
            elm.click();
            basesteps.report.pass(msg+getScreenshot());

        }
        catch (Exception e)
        {
            basesteps.report.fail(e.getMessage()+getScreenshot());
            e.printStackTrace();

        }
    }

    public void ElementExist(WebElement elm, String msg)
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.isDisplayed();
            basesteps.report.pass(msg+getScreenshot());

        }
        catch (Exception e)
        {
            basesteps.report.fail(e.getMessage()+getScreenshot());
            e.printStackTrace();

        }

    }

    public void verifyText(WebElement elm,String ExpText, String msg)
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            String actText = elm.getText();
            if(actText.trim().equals(ExpText))
            basesteps.report.pass(msg+getScreenshot());
            else
                basesteps.report.pass(ExpText+" Expected text did not match with actual text"+actText+"   "  +getScreenshot());
        }
        catch (Exception e)
        {
            basesteps.report.fail(e.getMessage()+getScreenshot());
            e.printStackTrace();

        }

    }


    public String getScreenshot()
    {
        Date d = new Date();
        DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
        String fileName = ft.format(d);
        String path = System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/screenshot/"+fileName+".png";
        TakesScreenshot ts = ((TakesScreenshot)driver);
        File SrcFile=ts.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile=new File(path);
        //Copy file at destination
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String imagepath = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+path+"'><span class='label time-taken grey lighten-1 white-text'>Screenshot</span><a>";
        return imagepath;
    }

}
