package pages;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Driver;

import java.util.List;

public class ToDoListPage extends BasePage {

    @FindBy(xpath = "//h1[text()='todos']")
    private WebElement toDoHeader;

    @FindBy(className = "new-todo")
    private WebElement newToDoInput;

    @FindBy(css = ".todo-count>strong")
    private WebElement toDoCounter;

    @FindBy(css = "li[data-testid]")
    private List<WebElement> toDoItemList;

    @FindBy(xpath = "(//input[@class='toggle'])[1]")
    private WebElement completeCheckbox;

    @FindBy(className = "clear-completed")
    private WebElement clearCompleted;

    @FindBy(className = "todo-list")
    private WebElement toDoListElement;


    public ToDoListPage(Driver pageDriver) {
        super(pageDriver);
        waitForElementVisible(toDoHeader);
    }

    public void addNewToDoItem(String addItemText) {

        clearElement(newToDoInput);
        setTextAs(newToDoInput, addItemText + Keys.ENTER);
    }

    public long getCounterValue() {
        return Long.parseLong(getText(toDoCounter));
    }

    public long getToDoItemsListSize() {
        return getSizeOfWebElementList(toDoItemList);
    }

    public void deleteSpecificToDoItem(String toDoItem) {
        By specificTask = By.xpath(String.format("//label[text()='%s']", toDoItem));
        By specificButton = By.xpath(String.format("//label[text()='%s']/following-sibling::button", toDoItem));
        deleteSpecificTask(specificTask, specificButton);
    }

    public void completeSpecificToDoItem(String toDoItem) {
        By specificCompleteButtonTask = By.xpath(String.format("//label[text()='%s']/../input",toDoItem));
        clickOnCheckBok(specificCompleteButtonTask);
    }

    public void clickOnClearCompletedButton() {
        clickElement(clearCompleted);
    }

    public void verifyIfClearCompleteButtonIsHidden() {
        verifyIfElementIsNotDisplayed(clearCompleted);
    }

    public void selectFilterOption(String filterOption){
        By filterSelected = By.linkText(filterOption);
        selectSpecificFilter(filterSelected);
    }

    public boolean verifyIfTheTextOfToDoItemHasDuplicates(String toDoItem)
    {
        return verifyIfTheTextElementIsDuplicate(toDoItemList, toDoItem);
    }

    public void verifyIfListToDoListIsHidden(){
        verifyIfElementIsNotDisplayed(toDoListElement);
    }

    public void verifyIfASpecificToDoItemIsCompleted(String toDoItem)
    {
        By specificItem = By.xpath(String.format("//label[text()='%s']/ancestor::li[@class='completed']", toDoItem));
        verifyIfAElementIsDisplayed(specificItem);
    }
}
