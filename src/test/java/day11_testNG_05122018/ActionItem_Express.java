package day11_testNG_05122018;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import reusables.reusableMethods;

public class ActionItem_Express {
    WebDriver driver;
    SoftAssert softAssert;


    @BeforeSuite
    public void openBrowser(){
        //define the chrome driver using reusable method
        driver = reusableMethods.chromeDriver();

    }

    @Test(priority=1)
    public void testCase1(){
        //define the softAssert
        softAssert= new SoftAssert();

        //navigate to express.com
        driver.navigate().to("https://www.express.com");

        //hover over Women using reusable mouse action method
        reusableMethods.hover(driver,"//*[@href='/womens-clothing']");

        //hover over accessories
        reusableMethods.hover(driver,"//*[@class='subnav-target' and contains(@href,'/womens-clothing/accessories/')]");

        //click on Jewelry
        reusableMethods.click(driver,"//*[contains(@href,'/womens-clothing/accessories/jewelry/')]");

        //click on earrings
        reusableMethods.click(driver,"//*[text()='Earrings']");

        //Assert page title
        softAssert.assertEquals("Earrings for Women - Earrings",driver.getTitle(),"Title is good");

        //Step 7: Hover over first item
        reusableMethods.hover(driver,"//*[contains(@alt,'large metal hoop earrings')]");

        //Step 8: Click on Express view Link
        reusableMethods.click(driver,"//*[contains(@href,'large-metal-hoop-earrings')]");

        //Step 9: Choose the second color (index:1)
        driver.findElements(By.xpath("//*[@class='color-swatch']")).get(1).click();

        //Step 10: Click on Add to bag link
        reusableMethods.click(driver,"//*[@data-add-to-bag-text='Add to Bag']");

        //Step 11: on the top right corner there is a bag icon in black, hover over it
        reusableMethods.hover(driver,"//*[@class='bag-icon']");

        //Step 12: Click on Checkout button
        reusableMethods.click(driver,"//*[contains(@href,'/check-out')]");

    }

    @Test (dependsOnMethods = "testCase1")
    public void testCase2() throws InterruptedException {
        Thread.sleep(2500);
        //select quantity 2
       /* WebElement quantity= driver.findElement(By.xpath("//*[@id='qdd-0-quantity']"));
        Select dropDown = new Select(quantity);
        //select by visible text
        dropDown.selectByIndex(1);*/

       reusableMethods.click(driver,"//*[@id='qdd-0-quantity']" );
        reusableMethods.click(driver,"//*[@value='2']" );

    }

    @AfterSuite
    public void closeBrowser(){
        //quitting the driver
       // driver.quit();
    }
}
