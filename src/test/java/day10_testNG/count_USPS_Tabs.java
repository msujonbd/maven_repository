package day10_testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reusables.reusableMethods;

import java.util.List;

public class count_USPS_Tabs {
    WebDriver driver;


    @BeforeMethod
    public void openBrowser(){
        //define the chrome driver using reusable method
        driver = reusableMethods.chromeDriver();

    }

    @Test
    public void testScenario() throws InterruptedException{

        //navigate to USPS.com
        driver.navigate().to("https://www.USPS.com");

        //wait a few seconds
        Thread.sleep(2500);

        //get all navigation tabs
        List<WebElement> tabCount = driver.findElements(By.xpath("//*[@class='menuitem']"));

        System.out.println("Tab count is: "+tabCount.size());

        //iterate through rach tab and get the name of the tab by printing
        for (int i=0; i<tabCount.size();i++){

            //String getName = driver.findElements(By.xpath("//*[@class='menuitem']")).get(i).getText();
            System.out.println("The tabs are: "+tabCount.get(i).getText());
        }//end of for loop
    }//end of testScenario

    @AfterMethod
    public void closeBrowser(){
        //quitting the driver
        driver.quit();
    }

}//end of class
