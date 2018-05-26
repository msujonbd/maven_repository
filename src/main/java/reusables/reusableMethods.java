//Mohammed Sujon, Java & Selenium: Assignment 5
package reusables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class reusableMethods {

    /*method below allows you to create a reuseable method to click on an element and you are passing in two arguments,
    * 1- webdriver that you are using
    * 2- the locator you are locating the element with
    * */

    public static int timeout = 50;

    public static WebDriver chromeDriver(){
        //Connecting Chrome driver
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe"); //Set the path for the chrome driver.
        ChromeOptions options = new ChromeOptions();   //Define Chrome options
        options.addArguments("--start-maximized","disable-extensions");     //Start Chrome maximized
        WebDriver driver = new ChromeDriver(options);  //defines new web-driver

        //we are returning the driver value becuase it's storing the chromedriver option
        return driver;
    }


    public static void click(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
        }catch(Exception e){
            System.out.println("Unable to click on the element "+e);
        }
    }//end of click method

    public static void sendKeys(WebDriver driver, String locator, String userValue){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).sendKeys(userValue);
        }catch(Exception e){
            System.out.println("Unable to enter the user values on sendKeys"+e);
        }
    }//end of sendKeys method

    public static String getText(WebDriver driver, String locator){
        WebDriverWait wait = new WebDriverWait(driver,timeout);

        String text = null;
        try{
            text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).getText();

        }catch(Exception e){
            System.out.println("Unable to get text from locator."+e);
        }
        return text;
    }//end of getText method



    public static void hover(WebDriver driver, String locator){
       Actions mouseAction = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver,timeout);

        try{

            //now wait until element "Quick Tool" appears on the page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

            //now I can store the above element in a web element locator to hover using mouse action.
            WebElement itemToHoverOver = driver.findElement(By.xpath(locator));

            //line below ill over over to that element using mouse movement
            mouseAction.moveToElement(itemToHoverOver).build().perform();

        }catch(Exception e){
            System.out.println("Unable to find hover."+e);
        }
    }//end of hover method


   // public static void getScreenshot(WebDriver driver, ExtentTest logger, String sc)



}//end of class
