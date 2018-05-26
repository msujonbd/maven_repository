package actionItems_testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reusables.reusableMethods;

import java.util.List;

public class actionItem_6 {
    WebDriver driver;
    SoftAssert softAssert;


    @BeforeMethod
    public void openBrowser(){
        //define the chrome driver using reusable method
        driver = reusableMethods.chromeDriver();



    }

    @Test
    public void testCase() throws InterruptedException{
        softAssert = new SoftAssert();

        //Step 1: navigate to express.com
        driver.navigate().to("https://www.express.com");

        //Step 2: Hover over Women tab
        reusableMethods.hover(driver,"//*[@href='/womens-clothing']");

        //Step 3: Hover over Accessories
        reusableMethods.hover(driver,"//*[@href='/womens-clothing/accessories/cat740011']");

        //Step 4: Click on Jewelry
        reusableMethods.click(driver,"//*[@aria-label='Jewelry. Press escape to exit sub menu.']");

        Thread.sleep(2500);

        //Step 5: Click on Earrings
       reusableMethods.click(driver,"/html/body/main/div/aside/div/div[2]/div/nav/div/ul/li[4]/a");

       // reusableMethods.click(driver,"//*[contains(@aria-label,'Earrings ')]");

        //Step 6: Assert that you are on Earrings page which is 'Earrings for Women - Earrings'
        softAssert.assertEquals("Earrings for Women - Earrings",driver.getTitle());

        //Step 7: Hover over first item
        reusableMethods.hover(driver,"//*[contains(@alt,'large metal hoop earrings')]");

        //Step 8: Click on Express view Link
        reusableMethods.click(driver,"//*[contains(@href,'large-metal-hoop-earrings')]");

        Thread.sleep(2500);

        //Step 9: Choose the second color (index:1)
        driver.findElements(By.xpath("//*[@class='color-swatch']")).get(1).click();

        //Step 10: Click on Add to bag link
        reusableMethods.click(driver,"//*[@data-add-to-bag-text='Add to Bag']");

        //Step 11: on the top right corner there is a bag icon in black, hover over it
        reusableMethods.hover(driver,"//*[@class='bag-icon']");

        Thread.sleep(2500);
        //Step 12: Click on Checkout button
        reusableMethods.click(driver,"//*[contains(@href,'/check-out')]");



        //Test Case 2
        //Step 1: Assert that you are on shopping bag page 'My Shopping Bag | Express'
        softAssert.assertEquals("My Shopping Bag | Express",driver.getTitle());

        //Step 2: on quantity dropdown select quantity to 2
        WebElement quantity= driver.findElement(By.xpath("//*[@id='qdd-0-quantity']"));
        Select dropDown = new Select(quantity);
        //select by visible text
        dropDown.selectByVisibleText("2");

        //Step 3: Click on 'Checkout' button
        reusableMethods.click(driver,"//*[@aria-label='Continue to Checkout']");

        //Step 4: on pop up click on 'Continue as guest' button
        reusableMethods.click(driver,"//*[@class='btn _2u-IO _1n1el _1MBdF']");

        //Step 5: on contact info enter first name
        reusableMethods.sendKeys(driver,"//*[@name='firstname']","Bill");

        //Step 6: enter last name
        reusableMethods.sendKeys(driver,"//*[@name='lastname']","Gates");

        //Step 7: enter valid email
        reusableMethods.sendKeys(driver,"//*[@name='email']","billg@microsoft.com");

        //Step 8: enter valid email again
        reusableMethods.sendKeys(driver,"//*[@name='confirmEmail']","billg@microsoft.com");

        //Step 9: enter some phone number
        reusableMethods.sendKeys(driver,"//*[@name='phone']","2067093140");

        //Step 10: click on 'Continue' button
        reusableMethods.click(driver,"//*[@class='btn _2u-IO _1n1el _1MBdF _2SogC']");

        //Test Cases 3
        // 1. Enter First Name
        // driver.findElement(By.xpath("//*[@name='shipping.firstName']")).clear();
        // reusableMethods.sendKeys(driver,"//*[@name='shipping.firstName']","Melinda");

        //2. Enter Last name
        //driver.findElement(By.xpath("//*[@name='shipping.lastName']")).clear();
        //  reusableMethods.sendKeys(driver,"//*[@name='shipping.lastName']","Gates");

        //3. Enter Street Address
        reusableMethods.sendKeys(driver,"//*[@name='shipping.line1']","7400 Northeast 18th Street");

        //4. Enter Zipcode
        reusableMethods.sendKeys(driver,"//*[@name='shipping.postalCode']","98039");

        //5. Enter City
        reusableMethods.sendKeys(driver,"//*[@name='shipping.city']","Medina");

        //6. Select State
        WebElement state= driver.findElement(By.xpath("//*[@name='shipping.state']"));
        Select dropDownState = new Select(state);
        //select by visible text
        dropDownState.selectByVisibleText("Washington");

        //7. Verify the check box for billing address same as shipping is checked by using assertTrue with isSelected();
        Boolean checkbox = driver.findElement(By.xpath("//*[@name='rvn-chkbx-2']")).isSelected();
        softAssert.assertTrue(checkbox, "Billing address check box is selected");

        //8. Click on Continue Button
        reusableMethods.click(driver,"//*[@class='btn _2u-IO _1n1el _1MBdF _2SogC']");

        //9. On Checkout Page capture the contact information and print it*/
        System.out.println(driver.findElements(By.xpath("//*[@class='v_dFo']")).get(0).getText());
        System.out.println(driver.findElements(By.xpath("//*[@class='v_dFo']")).get(1).getText());
        System.out.println(driver.findElements(By.xpath("//*[@class='v_dFo']")).get(2).getText());


    }//end of testScenario


    @AfterMethod
    public void closeBrowser(){
        //quitting the driver
       // driver.quit();
    }

}//end of class
