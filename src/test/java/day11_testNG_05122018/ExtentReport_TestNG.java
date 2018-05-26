package day11_testNG_05122018;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import reusables.reusableMethods;

import java.io.File;
import java.io.IOException;

public class ExtentReport_TestNG {
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;

    @BeforeSuite
    public void openDriver(){
        driver = reusableMethods.chromeDriver();
        report = new ExtentReports("C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\report.html",true);
    }

    @AfterSuite
    public void closeDriver(){
        //driver.quit();

        //line below will flush the report by adding all you log information to the log
        report.flush();

        //line below will close & end your report
        report.close();

    }

    @Test
    public void testScenario() throws InterruptedException, IOException {
        //log test name using logger
        logger= report.startTest("Google Search");

        //navigate to google.com
        logger.log(LogStatus.INFO,"navigating to Google.com");
        driver.navigate().to("https://www.google.com");

        //Wait few seconds
        logger.log(LogStatus.INFO,"Waiting for page to load");
        Thread.sleep(2500);

        //Assert that title page is google
        logger.log(LogStatus.INFO,"Verify the title of the page is Google");
        //Assert.assertEquals("Google",driver.getTitle());
        //to use assetions with Extend report
        String actualTitle= driver.getTitle();
        if(actualTitle.equalsIgnoreCase("goo0gle")){
            logger.log(LogStatus.PASS,"Title of page is Google");
        } else{
            logger.log(LogStatus.FAIL,"Title of page is not Google, it is: "+actualTitle);
            //define the path of the image
            String imagePath="C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\googleTitle.png";

            //line below allows you to take the screenshot (don't need to memorize the command)
            File sourceFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            //Now you can do whatever you need to do with this, for example copy somewhere
            FileUtils.copyFile(sourceFile, new File(imagePath));

            String image = logger.addScreenCapture(imagePath);

            logger.log(LogStatus.FAIL,"Verify Google title",image);
        }

        //end to the test
        report.endTest(logger);



    }


   // @Test (dependsOnMethods = "testScenario")


}
