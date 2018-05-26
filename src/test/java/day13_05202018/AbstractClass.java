package day13_05202018;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.UUID;

public class AbstractClass {

    //declaring variable for webdriver
    WebDriver driver;
    //declaring variable for softAssert
    SoftAssert softAssert;
    //declaring variable for extent report
    ExtentReports reports;
    //declaring variable for extent logger
    ExtentTest logger;

    @Parameters("browser")
    @BeforeSuite //
    public void defineBrowse(String browser){
        reports = new ExtentReports("C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\Firefox ExtentReport "+ UUID.randomUUID()+".html",true);
        if(browser.equalsIgnoreCase("firefox")){
            //defining the path for geckodriver
            System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
            //maximize firefox
            driver.manage().window().maximize();
        } else if(browser.equalsIgnoreCase("chrome")){
            reports = new ExtentReports("C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\Chrome ExtentReport "+ UUID.randomUUID()+".html",true);
            //defining the path for chromedriver
            System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
            //define the chrome option
            ChromeOptions options = new ChromeOptions();
            options.addArguments("incognito","start-maximized");
            driver= new ChromeDriver(options);
        }
    }//end of BeforeSuite

    //Before method is only defined to capture your test method name to imported on your html report
    @BeforeMethod
    public void methodName(Method method) {
        logger = reports.startTest(method.getName());
    }

    //After method to end the test that your are running on your xml suite
    @AfterMethod
    public void endTest(){
        reports.endTest(logger);
    }

    //Close and flush the report and either quit the driver or open your html report automatically
    @AfterSuite
    public void close(){
        //flush the report
        reports.flush();
        //close the report
        reports.close();
        //quit the driver
        driver.quit();
    }



} //end of class
