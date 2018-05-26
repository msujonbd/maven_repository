package day10_testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reusables.reusableMethods;

import java.util.List;

public class yahoo_actionItem {
    WebDriver driver;
    SoftAssert softAssert;


    @BeforeMethod
    public void openBrowser(){
        //define the chrome driver using reusable method
        driver = reusableMethods.chromeDriver();



    }

    @Test
    public void testScenario() throws InterruptedException{
        softAssert = new SoftAssert();

        //Step 1: navigate to yahoo.com
        driver.navigate().to("https://www.yahoo.com");

        //Step 2: verify I'm on the right page by using assertion
        softAssert.assertEquals("Yahoo",driver.getTitle());


        //Step 3: get all navigation tabs
        List<WebElement> tabCount = driver.findElements(By.xpath("//*[contains (@class,'Mstart(21px)')]"));
        System.out.println("Tab count is: "+tabCount.size());

        //Step 4: Enter in nutrition
        reusableMethods.sendKeys(driver,"//*[contains (@id,'uh-search-box')]","Nutrition");

        //Step 5: Click on search
        reusableMethods.click(driver,"//*[@id='uh-search-button']");



        //Scrolling down to result number at the bottom of the page
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        jse.executeScript("scroll(0,10000)");

        //WebElement element = driver.findElement(By.xpath("//*[contains[]"))

        //storing the text for result number in string
        String message = driver.findElement(By.xpath("//*[contains(@class, 'reg searchBottom')]")).getText();
        String[] arraymsg = message.split("Next");
        System.out.println("result is "+ arraymsg[1]);



        jse.executeScript("scroll(0,-10000)");

        //Click sign in
        reusableMethods.click(driver,"//*[@id='yucs-login_signIn']");

        Boolean elementState = driver.findElement(By.xpath("//*[@id='persistent']")).isSelected();
        System.out.println("Is element selected? "+elementState);


        reusableMethods.sendKeys(driver,"//*[@id='login-username']","ABC");

        Thread.sleep(2500);

        reusableMethods.click(driver,"//*[@id='login-signin']");

        String capturedText= reusableMethods.getText(driver,"//*[@class='row error']");
        String hardCodedtext = "Sorry, we don't recognize this email.";

        softAssert.assertEquals(hardCodedtext,capturedText);


/*
        //iterate through rach tab and get the name of the tab by printing
        for (int i=0; i<tabCount.size();i++){

            //String getName = driver.findElements(By.xpath("//*[@class='menuitem']")).get(i).getText();
            System.out.println("The tabs are: "+tabCount.get(i).getText());
        }//end of for loop*/
    }//end of testScenario

    @AfterMethod
    public void closeBrowser(){
        //quitting the driver
      driver.quit();
    }

}//end of class
