package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;


public abstract class BasePage {

    protected static final Logger log = Logger.getLogger(BasePage.class.getName());
    protected Driver pageDriver;

    public BasePage() {
    }

    public BasePage(Driver pageDriver) {
        this.pageDriver = pageDriver;
        PageFactory.initElements(pageDriver.driver, this);
    }

    protected void waitForElementClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(pageDriver.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(pageDriver.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementNotVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(pageDriver.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void setTextAs(WebElement element, String text) {
        waitForElementClickable(element);
        element.sendKeys(text);
    }

    protected void clearElement(WebElement element) {
        waitForElementClickable(element);
        element.clear();
    }

    protected void clickElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }

    protected void clickOnCheckBok(By element) {

        pageDriver.driver.findElement(element).click();
    }

    protected String getText(WebElement element) {
        waitForElementVisible(element);
        return element.getText();
    }

    protected Driver getWebDriver() {
        return this.pageDriver;
    }

    protected long getSizeOfWebElementList(List<WebElement> elements) {
        return elements.size();
    }

    protected boolean verifyIfTheTextElementIsDuplicate(List<WebElement> elements, String taskName){
        int counter = 0;
        for (WebElement element: elements){
            String g = element.getText();
            if(element.getText().equals(taskName)){
                counter++;
            }
        }
        return (counter > 1);
    }

    protected void verifyIfElementIsNotDisplayed(WebElement element) {
        waitForElementNotVisible(element);
    }

    protected void deleteSpecificTask(By specificTask, By specificDeleteButton) {
        Actions action = new Actions(getWebDriver().getDriver());
        action.moveToElement(pageDriver.driver.findElement(specificTask));
        action.moveToElement(pageDriver.driver.findElement(specificDeleteButton));
        action.click().build().perform();
    }

    protected void selectSpecificFilter(By element) {
        waitForElementVisible(pageDriver.driver.findElement(element));
        pageDriver.driver.findElement(element).click();
    }

    protected void verifyIfAElementIsDisplayed(By element){
        waitForElementVisible(pageDriver.driver.findElement(element));
    }
}
