package Tests;

import common.BasePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ToDoListPage;
import utils.Driver;

import static constant.Constant.*;

public class ToDoTest extends BasePage {

    private Driver driver;
    private ToDoListPage toDoListPage;

    @BeforeMethod
    public void startup() {

        driver = new Driver();
        toDoListPage = new ToDoListPage(driver);
    }

    @Feature("Todo items action")
    @Story("The clear completed button should clear complete items of the todo item list")
    @Owner("Rodrigo Amurrio")
    @Description("THA-1: User can successfully clear completed items of a todo item list with one item completed.")
    @Test
    public void userCanSuccessfullyClearCompletedItemsOfAToDoItemListWithOneItemCompleted() {

        toDoListPage.addNewToDoItem(TASK_1);
        toDoListPage.addNewToDoItem(TASK_2);
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), toDoListPage.getCounterValue(), "The counter value: " + toDoListPage.getCounterValue() + "is not the same than quantity to do Items: " + toDoListPage.getCounterValue());
        toDoListPage.completeSpecificToDoItem(TASK_2);
        toDoListPage.verifyIfASpecificToDoItemIsCompleted(TASK_2);
        toDoListPage.clickOnClearCompletedButton();
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), toDoListPage.getCounterValue(), "The counter value: " + toDoListPage.getCounterValue() + "is not the same than quantity to do Items: " + toDoListPage.getCounterValue());
        toDoListPage.verifyIfClearCompleteButtonIsHidden();
    }

    @Feature("Todo items action")
    @Story("Users can add duplicate todo item in the todo item list")
    @Owner("Rodrigo Amurrio")
    @Description("THA-2: User can successfully make duplicate todo items.")
    @Test
    public void userCanSuccessfullyMakeDuplicateToDoItems() {

        toDoListPage.addNewToDoItem(TASK_1);
        toDoListPage.addNewToDoItem(TASK_1);
        Assert.assertTrue(toDoListPage.verifyIfTheTextOfToDoItemHasDuplicates("Task 1"), "The user cannot a todo item with the same text value");
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), 2, "The to do item list size should be equals 2 because enter two to do items");
        Assert.assertEquals(toDoListPage.getCounterValue(), 2, "The counter should be equals 2 because enter two to do items");
    }

    @Feature("Todo items action")
    @Story("Todo item counter functionality according to the status and filter applied")
    @Owner("Rodrigo Amurrio")
    @Description("THA-3: Number of 'items left' is the same as count of active todo items in the list with 'all' filter.")
    @Test
    public void NumberOfLeftIsTheSameAsCountOfActiveTodoItemsInTheListWithAllFilter() {

        toDoListPage.addNewToDoItem(TASK_1);
        toDoListPage.addNewToDoItem(TASK_2);
        toDoListPage.selectFilterOption(ALL_FILTER);
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), toDoListPage.getCounterValue(), "The counter value: " + toDoListPage.getCounterValue() + "is not the same than quantity to do Items: " + toDoListPage.getCounterValue());
        toDoListPage.addNewToDoItem(TASK_3);
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), toDoListPage.getCounterValue(), "The counter value: " + toDoListPage.getCounterValue() + "is not the same than quantity to do Items: " + toDoListPage.getCounterValue());
    }

    @Feature("Todo items action")
    @Story("Delete a todo item")
    @Owner("Rodrigo Amurrio")
    @Description("THA-4: User can successfully delete a todo item of a todo item list with two todo items.")
    @Test
    public void UserCanSuccessfullyDeleteToDoItemOfAToDoItemListWithTwoToDoItems() {
        toDoListPage.addNewToDoItem(TASK_1);
        toDoListPage.addNewToDoItem(TASK_2);
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), toDoListPage.getCounterValue(), "The counter value: " + toDoListPage.getCounterValue() + "is not the same than quantity to do Items: " + toDoListPage.getCounterValue());
        toDoListPage.deleteSpecificToDoItem(TASK_2);
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), toDoListPage.getCounterValue(), "The counter value: " + toDoListPage.getCounterValue() + "is not the same than quantity to do Items: " + toDoListPage.getCounterValue());
    }

    @Feature("Todo items action")
    @Story("Delete a todo item")
    @Owner("Rodrigo Amurrio")
    @Description("THA-5: User can successfully delete a todo item of a todo item list with only one todo item.")
    @Test
    public void UserCanSuccessfullyDeleteToDoItemOfAToDoItemListWithOnlyToDoItem() {
        toDoListPage.addNewToDoItem(TASK_1);
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), toDoListPage.getCounterValue(), "");
        toDoListPage.deleteSpecificToDoItem(TASK_1);
        toDoListPage.verifyIfListToDoListIsHidden();
    }

    @Feature("Todo items action")
    @Story("The clear completed button should clear complete items of the todo item list")
    @Owner("Rodrigo Amurrio")
    @Description("THA-6: User can successfully clear completed items of a todo item list with only one item completed.")
    @Test
    public void userCanSuccessfullyClearCompletedItemsOfAToDoItemListWithOnlyOneItemCompleted() {

        toDoListPage.addNewToDoItem(TASK_1);
        Assert.assertEquals(toDoListPage.getToDoItemsListSize(), toDoListPage.getCounterValue(), "The ");
        toDoListPage.completeSpecificToDoItem(TASK_1);
        toDoListPage.verifyIfASpecificToDoItemIsCompleted(TASK_1);
        toDoListPage.clickOnClearCompletedButton();
        toDoListPage.verifyIfListToDoListIsHidden();
        toDoListPage.verifyIfClearCompleteButtonIsHidden();
    }

    @AfterMethod
    public void tearDownTest() {
        driver.closeDriver();
    }
}
