//Express Action Item
package day13_05202018;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reusables.reusableMethods_Report;
import sun.rmi.runtime.Log;

import java.io.IOException;

public class ExpressTest extends AbstractClass{

    @Test
    public void expressCheckout() throws IOException {

        //navigate to express.com
        driver.navigate().to("https://www.express.com");

        //hover over Women using reusable mouse action method
        reusableMethods_Report.mouseHover(driver,"//*[@href='/womens-clothing']",logger,"Women's Tab");

        //hover over accessories
        reusableMethods_Report.mouseHover(driver,"//*[@class='subnav-target' and contains(@href,'/womens-clothing/accessories/')]",logger,"Acessories");

        //click on Jewelry
        reusableMethods_Report.click(driver,"//*[contains(@href,'/womens-clothing/accessories/jewelry/')]",logger,"Jewelry");

        //click on earrings
        reusableMethods_Report.click(driver,"//*[text()='Earrings']",logger,"earrings");

        //Assert page title
        String pageName= driver.getTitle();
        if(pageName.equalsIgnoreCase("Earrings for Women m- Earrings")){
            logger.log(LogStatus.INFO,"Page name is correct");
        } else{
            logger.log(LogStatus.FAIL,"Page name is not correct");
            reusableMethods_Report.getScreenshot(driver,logger);
        }

        //Step 7: Hover over first item
        reusableMethods_Report.mouseHover(driver,"//*[contains(@alt,'large metal hoop earrings')]",logger,"first item");

        //Step 8: Click on Express view Link
        reusableMethods_Report.click(driver,"//*[contains(@href,'large-metal-hoop-earrings')]",logger,"Express view button");

     /*   //Step 9: Choose the second color (index:1)
        driver.findElements(By.xpath("//*[@class='color-swatch']")).get(1).click();
*/
        //Step 10: Click on Add to bag link
        reusableMethods_Report.click(driver,"//*[@data-add-to-bag-text='Add to Bag']",logger,"Add to bag");

        //Step 11: on the top right corner there is a bag icon in black, hover over it
        reusableMethods_Report.mouseHover(driver,"//*[@class='bag-icon']",logger,"bag icon");

        //Step 12: Click on Checkout button
        reusableMethods_Report.click(driver,"//*[contains(@href,'/check-out')]",logger,"checkout button");

    }

    @Test(dependsOnMethods = "expressCheckout")
    public void addQuantity() throws IOException {
        //click on the quantity drop down
        reusables.reusableMethods_Report.click(driver,"//*[@id='qdd-0-quantity']",logger,"Quantity Dropdown");
        //click on the quantity value
        reusables.reusableMethods_Report.click(driver,"//*[@value='2']",logger,"Quantity Value");
    }
}
