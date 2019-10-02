package NopCommerce;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static NopCommerce.BasePage.driver;

public class Utils extends BasePage {

    //1. method to Navigate to URL
    public static void NavigatetoURL (String text){
        driver.navigate().to(text); }


    //2. get selected value from dropdown
    public static void selectByValue(By by, String value) {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value); }

    //3. isdorpdown present
    public static void selectByVisibleText(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text); }

    //4. dropdown select by index
    public static void selectByIndex(By by, int no) {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(0); }
    //5. generateEmail
    public String generateEmail(String startvalue) {
        String email = startvalue.concat(new Date().toString());
        return email; }

    //6. Enter text in input field
    public void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text); }

    //7. get text method
    public String getText(By by) {
        return driver.findElement(by).getText(); }

    //8. Clicking element
    public void clickElement(By by) {
        driver.findElement(by).click(); }

    //9. Date format
    public static String randomDate() {
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date()); }

    //10. method for screenshot2
    public static void getScreenshot(){
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("src\\main\\Resources\\ScreenShot"+randomDate()+".png"));
        } catch (IOException e) {
            e.printStackTrace(); }}

        //wait for element to be clickable for given time in seconds
        public static void waitElementForClickble(By by, long time){
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable(by)); }
    //wait for element to be visible
    public static void waitForElementVisible(By by, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by)); }
    //wait for AlertPresent
    public static void waitForAlertPresent(long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.alertIsPresent()); }
    //Checking WebElement displayed or not
    public void checkingWebelementDisplayedOrNot(By by, String text) {
        driver.findElement(by).isDisplayed(); }




}
