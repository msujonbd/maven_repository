package day12_XMLSuite_05192018;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import reusables.reusableMethods_Report;

import java.io.IOException;

public class Parallel_Testing {
    WebDriver driver;
    ExtentReports reports;
    ExtentTest logger;
    @Parameters("Browser")
    @BeforeMethod
    public void openBrowser(String Browser){
        reports = new ExtentReports("C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\report.html",false);
        if(Browser.equalsIgnoreCase("firefox")){
            //defining the path for geckodriver
            System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
            //maximize firefox
            driver.manage().window().maximize();
        } else if(Browser.equalsIgnoreCase("chrome")){
            //defining the path for chromedriver
            System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
            //define the chrome option
            ChromeOptions options = new ChromeOptions();
            options.addArguments("incognito","start-maximized");
            driver= new ChromeDriver(options);
        }

    }

    @Test
    public void bingSearch() throws InterruptedException, IOException {
        driver.navigate().to("https://www.bing.com");
        logger=reports.startTest("Bing Search");
        logger.log(LogStatus.INFO,"Navigating to Bing.com");
        driver.navigate().to("https://www.bing.com");
        Thread.sleep(2500);

        //click on image using reusable method with logger and screenshot
        reusableMethods_Report.click(driver,"//*[@id='scpl']",logger,"Images");

        //end the test
        reports.endTest(logger);
    }

   /* @Test(dependsOnMethods = "bingSearch")
    public void


    @AfterMethod
    public void closeBrowser(){
       // driver.quit();
        reports.flush();
        reports.close();


    }*/


} //end of class

