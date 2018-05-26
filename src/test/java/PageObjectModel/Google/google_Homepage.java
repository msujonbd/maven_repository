package PageObjectModel.Google;

import PageObjectModel.AbstractMethods;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reusables.reusableMethods;

import java.awt.print.PageFormat;
import java.io.IOException;

import static reusables.reusableMethods_Report.getScreenshot;

public class google_Homepage extends AbstractMethods{
    ExtentTest logger;
    WebDriver driver;

    public google_Homepage(WebDriver driver){
        super();
        this.driver= AbstractMethods.driver;
        this.logger = AbstractMethods.logger;
    }

    public google_Homepage googleSearch(String textValue) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try{
            logger.log(LogStatus.INFO,"Enter in a value "+textValue+" on google search");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1st-ib']")));
        } catch(Exception e){
            System.out.println("Unable to enter user value on sendKeys "+e);
            logger.log(LogStatus.FAIL,"Unable to enter in a value on google search");
            getScreenshot(driver,logger);
        }

        return new google_Homepage(driver);
    }

    public google_Homepage googleSearchButton (String textValue) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try{
            logger.log(LogStatus.INFO,"Click on google search button");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnK']")));
        } catch(Exception e){
            System.out.println("Unable to click on google search button"+e);
            logger.log(LogStatus.FAIL,"Unable to click on google search button");
            getScreenshot(driver,logger);
        }

        return new google_Homepage(driver);
    }
}
