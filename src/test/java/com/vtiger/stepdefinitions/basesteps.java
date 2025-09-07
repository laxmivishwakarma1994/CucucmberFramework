package com.vtiger.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class basesteps {
    public static Properties prop;
    public static WebDriver driver;
    public static Map<String,Map<String,String>> dt;

    public static String TCName;

    public static LoginPage lp;
    public static HomePage hp;
    public static LeadPage ldp;

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest report;

    public void initiation()
    {

        if(dt==null)
        readExcel();
        if(prop==null)
        readproperties();
        if(driver==null)
        launchApp();
    }

    public void launchApp()
    {
        if(prop.getProperty("browser").equalsIgnoreCase("edge"))
        {
            driver = new EdgeDriver();
        }
        else if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }
        else if(prop.getProperty("browser").equalsIgnoreCase("headless"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode
            options.addArguments("--disable-gpu"); // (Windows specific) to avoid errors
            options.addArguments("--window-size=1920,1080"); // Recommended for visibility
            driver = new ChromeDriver(options);
        }
        else
        {
            driver = new ChromeDriver();
        }
        driver.get(prop.getProperty("AppUrl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("globalwait"))));
    }


    public void readproperties()
    {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(fis);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //jexcel
    //apache POI
    //fillo

    public void readExcel()
    {
        try {
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(System.getProperty("user.dir") +"/src/test/resources/TestData/data.xlsx");
            String strQuery = "Select * from Sheet1";
            Recordset recordset = connection.executeQuery(strQuery);
            int rows = recordset.getCount();
            List<String> ls = recordset.getFieldNames();
            int colms = ls.size();
            dt = new HashMap<>();
            while(recordset.next())
            {
                Map<String,String> rowdata = new HashMap<>();
                for(int j=1;j<colms;j++)
                {
                    String colname = ls.get(j);
                    String colmval = recordset.getField(ls.get(j));
                    rowdata.put(ls.get(j),recordset.getField(ls.get(j)));
                }
                dt.put(recordset.getField("TCName"),rowdata);
            }

            System.out.println(dt);

            recordset.close();
            connection.close();
        }
        catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
    }

    public void createExtentReport()
    {
        //report_13042025104034.html
        Date d = new Date();
        DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
        String fileName = ft.format(d);
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/ExtentReport_"+fileName+".html");
        // Create an object of Extent Reports
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Automation Test Hub");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("User Name", "Rajesh U");
        htmlReporter.config().setDocumentTitle("Title of the Report Comes here ");
        // Name of the report
        htmlReporter.config().setReportName("Name of the Report Comes here ");
        // Dark Theme
        htmlReporter.config().setTheme(Theme.STANDARD);

    }



}
