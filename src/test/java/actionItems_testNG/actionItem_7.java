package actionItems_testNG;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;

public class actionItem_7 {
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;

    @BeforeSuite
    public void openDriver(){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe"); //Set the path for the chrome driver.
        ChromeOptions options = new ChromeOptions();   //Define Chrome options
        options.addArguments("--start-maximized");     //Start Chrome maximized
        driver = new ChromeDriver(options);  //defines new web-driver

        report = new ExtentReports("C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\ExtentReport.html",true);
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();

        //line below will flush the report by adding all you log information to the log
        report.flush();

        //line below will close & end your report
        report.close();

    }

    @Test
    public void testScenario() throws InterruptedException, IOException {
        //log test name using logger
        logger= report.startTest("Action Item 7, UPS");

        //navigate to google.com
        logger.log(LogStatus.INFO,"navigating to UPS.com");
        driver.navigate().to("https://www.ups.com/us");

        //Wait few seconds
        logger.log(LogStatus.INFO,"Waiting for page to load");
        Thread.sleep(2500);

        //Assert that title page is google
        logger.log(LogStatus.INFO,"Verify the title of the page is 'Shipping | UPS'");
        //Assert.assertEquals("Google",driver.getTitle());
        //to use assetions with Extend report
        String expectedTitle,actualTitle;
        expectedTitle="Shipping | UPS";
        actualTitle=driver.getTitle();


        if(actualTitle.equalsIgnoreCase(expectedTitle)){
            logger.log(LogStatus.PASS,"Title of page is UPS");
        } else{
            logger.log(LogStatus.FAIL,"Title of page is not \""+expectedTitle+ "\"it is: "+actualTitle);
            //define the path of the image
            String imagePath="C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\UPSTitle.png";

            //line below allows you to take the screenshot (don't need to memorize the command)
            File sourceFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            //Now you can do whatever you need to do with this, for example copy somewhere
            FileUtils.copyFile(sourceFile, new File(imagePath));

            String image = logger.addScreenCapture(imagePath);

            logger.log(LogStatus.FAIL,"Verify UPS title",image);
        }


        //Click on Quote form quickstart dropdown
        driver.findElement(By.xpath("//*[@id='ups-quickStartQuote']")).click();
        Thread.sleep(2500);

        WebElement type= driver.findElement(By.xpath("//*[@name='shipmentType']"));
        Select dropDown = new Select(type);
        //select by visible text
        dropDown.selectByVisibleText("Letter");

        WebElement origin= driver.findElement(By.xpath("//*[@name='origCountry']"));
        dropDown = new Select(origin);
        //select by visible text
        dropDown.selectByVisibleText("United States");

        WebElement destination= driver.findElement(By.xpath("//*[@name='destCountry']"));
        dropDown = new Select(destination);
        //select by visible text
        dropDown.selectByVisibleText("United States");

        driver.findElement(By.xpath("//*[@name='weight']")).sendKeys("2");
        driver.findElement(By.xpath("//*[@name='origPostalCode']")).sendKeys("11219");
        driver.findElement(By.xpath("//*[@name='destPostalCode']")).sendKeys("10004");

        boolean isSelected = driver.findElement(By.xpath("//*[@type='checkbox']")).isSelected();

        if(isSelected==false){

            logger.log(LogStatus.PASS,"Checkbox is selected by default.");

        } else{
            //define the path of the image
            String imagePath="C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\checkbox.png";

            //line below allows you to take the screenshot (don't need to memorize the command)
            File sourceFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            //Now you can do whatever you need to do with this, for example copy somewhere
            FileUtils.copyFile(sourceFile, new File(imagePath));

            String image = logger.addScreenCapture(imagePath);

            logger.log(LogStatus.FAIL,"Error,checkbox is selected by default",image);
        }


        driver.findElement(By.xpath("//*[@name='--qs']")).click();



        //end to the test
        report.endTest(logger);


    }

    @Test (dependsOnMethods = "testScenario")
    public  void testScenario2()throws InterruptedException, IOException {

        String actualTitle = driver.getTitle();
        String expectedTitle= "Calculate Time and Cost: UPS";

        if(actualTitle.equalsIgnoreCase(expectedTitle)){
            logger.log(LogStatus.PASS,"Title of page is Calculate Time and Cost: UPS");
        } else{
            logger.log(LogStatus.FAIL,"Title of page is not \""+expectedTitle+ "\"it is: "+actualTitle);
            //define the path of the image
            String imagePath="C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\Calculate_Time_Title.png";

            //line below allows you to take the screenshot (don't need to memorize the command)
            File sourceFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            //Now you can do whatever you need to do with this, for example copy somewhere
            FileUtils.copyFile(sourceFile,new File(imagePath));

            String image = logger.addScreenCapture(imagePath);

            logger.log(LogStatus.FAIL,"Verify UPS calculate time and cost title",image);
        }


        driver.findElements(By.xpath("//*[@value='Ship Now']")).get(0).click();
        Thread.sleep(2500);

         actualTitle = driver.getTitle();
         expectedTitle= "Shipping: UPS";

        if(actualTitle.equalsIgnoreCase(expectedTitle)){
            logger.log(LogStatus.PASS,"Title of page is Shipping: UPS");
        } else{
            logger.log(LogStatus.FAIL,"Title of page is not \""+expectedTitle+ "\"it is: "+actualTitle);
            //define the path of the image
            String imagePath="C:\\Users\\Mohammed Sujon\\Pictures\\ExtentReport\\Shipping_UPS_Title.png";

            //line below allows you to take the screenshot (don't need to memorize the command)
            File sourceFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            //Now you can do whatever you need to do with this, for example copy somewhere
            FileUtils.copyFile(sourceFile,new File(imagePath));

            String image = logger.addScreenCapture(imagePath);

            logger.log(LogStatus.FAIL,"Verify UPS shipping page title",image);
        }
    }
}
