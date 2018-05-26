package day9_testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reusables.reusableMethods;

import javax.swing.text.Utilities;

public class google_testNG {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    public void openBrowser(){
        //define the chrome driver using reusable method
        driver = reusableMethods.chromeDriver();

    }

    @Test
    public void testScenario(){
        softAssert = new SoftAssert();
        driver.navigate().to("https://google.com");
        //verify I'm on the right page by using assertion
        softAssert.assertEquals("Go0ogle",driver.getTitle());


        //Let;s verify if the google image is displayed or not using assertTure();
        softAssert.assertTrue(driver.findElement(By.xpath("//*[@id='hplogo']")).isDisplayed(),"Logo is displayed");

        //entering name on search field
        reusableMethods.sendKeys(driver,"//*[@name='q']","Brooklyn");
        //click on somewhere outside of the page to minimize the search dropdown
        reusableMethods.click(driver,"//*[@id='body']");
        reusableMethods.click(driver,"//*[@name='btnK']");
        String message = reusableMethods.getText(driver,"//*[@id='resultStats']");
        //String[] results= message.split(" ");

        String[] results= message.split(" ");
        System.out.println("Search results: "+results[1]);
    }

    @AfterMethod
    public void closeBrowser(){
        //quitting the driver
        driver.quit();
        //Captures exception at end of test execution.
        softAssert.assertAll();

    }
}
