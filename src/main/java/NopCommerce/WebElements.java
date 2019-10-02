package NopCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import static NopCommerce.BasePage.driver;

public class WebElements extends Utils {

LoadProperties loadProperties = new LoadProperties();
SoftAssert softAssert = new SoftAssert();


    @BeforeMethod
    public void openBrowsers(){
    System.setProperty("webdriver.chrome.driver","src\\main\\Resources\\WebBrowsers\\chromedriver.exe");
     //open the browser
    driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    //open the website
        driver.get(loadProperties.getProperty("url")); }
      //  @AfterMethod
        //public void closeBrowsers(){
          //  driver.quit(); }

        @Test (priority = 0)
    public void userShouldBeAbleToCompareTheProductListSuccessFully(){
        //click on first product to compare
         clickElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/1\"),!1']"));
         //wait element for display
            waitForElementVisible(By.xpath("//p/a[@href=\"/compareproducts\"]"),10);
        //check the product comparison
        softAssert.assertTrue(driver.findElement(By.xpath("//p/a[@href=\"/compareproducts\"]")).isDisplayed());

        //click on second product to commpare
       clickElement(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/43\"),!1']"));
            //wait element for display
            waitForElementVisible(By.xpath("//p/a[@href=\"/compareproducts\"]"),10);
        softAssert.assertTrue(driver.findElement(By.linkText("product comparison")).isDisplayed());
        clickElement(By.linkText("product comparison"));

      //  waitElementForClickble(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/1\"),!1']"),10);
        waitElementForClickble(By.linkText("Compare products list"),10);
        clickElement(By.linkText("Compare products list"));
        //comparing the right product
        String expectedProduct1 = "Build your own computer";
        String actualProduct1 = getText(By.linkText("Build your own computer"));
        softAssert.assertEquals(expectedProduct1,actualProduct1);

        String expectedProduct2 = "$25 Virtual Gift Card";
        String actualProduct2 = getText(By.linkText("$25 Virtual Gift Card"));
        softAssert.assertEquals(expectedProduct2,actualProduct2);
        //click on clear list
            clickElement(By.xpath("//a[@onclick='setLocation(\"/clearcomparelist\")']"));

        String expectedResult = "You have no items to compare.";
        String actualResult = getText(By.xpath("//div[@class=\"no-data\"]"));
        softAssert.assertEquals(expectedResult,actualResult);
    }
    @Test (priority = 1)
    public void userShouldBeAbleToMakeACommentInNewOnlineStoreOpen() {
        //click on Details
        clickElement(By.xpath("//div/div/div/a[@href=\"/new-online-store-is-open\" and@class=\"read-more\"]"));
        //enter the Title
        enterText(By.xpath("//input[@class=\"enter-comment-title\"]"),loadProperties.getProperty("title"));
        // enter the commment
        enterText(By.xpath("//textarea[@id=\"AddNewComment_CommentText\"]"),loadProperties.getProperty("comment"));
        //click on New Comment
        clickElement(By.xpath("//input[@name=\"add-comment\"]"));

        String expectedResult = "News comment is successfully added.";
        String actualResult = getText(By.xpath("//div[@class=\"result\"]"));

        softAssert.assertEquals(expectedResult,actualResult);
        //making a list of all comments
        List<WebElement> commentList = driver.findElements(By.xpath("//div[@class=\'comment-info\']"));
        //storing value in the last
        WebElement lastcomment = commentList.get(commentList.size()-1);
        //getting text to last element
        String lastCommentText = (lastcomment.getText());
        softAssert.assertEquals(lastcomment,lastCommentText);

    }
    @Test (priority = 2)
    public void userShouldAbletoSearchProductByName(){
        //click on search
        clickElement(By.xpath("//input[@class=\"search-box-text ui-autocomplete-input\"]"));
        //Enter the Product Name for Search
        enterText(By.xpath("//input[@class=\"search-box-text ui-autocomplete-input\"]"),loadProperties.getProperty("ProductName"));
        //click on search button
        clickElement(By.xpath("//input[@type=\"submit\"]"));


        //making ArryList
        List<WebElement>al = driver.findElements(By.xpath("//div[@class=\"item-box\"]")); //("//div[@class=\"details\"]"));
        // find the Array Size
        System.out.println(al.size());
        int count = 0;
        for (WebElement e: al) {

            if (e.getAttribute("outerHTML").contains("Nike")){
               count++;
            } else {
                System.out.println("No product available" + e.getText());
            }
            System.out.println(count);

            //enter the new product name
            enterText(By.xpath("//input[@class=\"search-box-text ui-autocomplete-input\"]"),loadProperties.getProperty("NewProductName"));
            //click on search button
            clickElement(By.xpath("//input[@type=\"submit\"]"));

            String expectedMessage ="No products were found that matched your criteria.";
            String actualMessage = getText(By.xpath("//div[@class=\"no-result\"]"));

            Assert.assertEquals(expectedMessage,actualMessage);




        }

    }}




