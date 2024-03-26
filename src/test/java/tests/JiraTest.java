package tests;

import hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import static util.TestProperties.getProperty;

@DisplayName("JiraTest")
public class JiraTest extends WebHooks {

    @DisplayName("Создание новой задачи и проведение ее по статусам")
    @Test
    public void createNewTask(){
        TaskCreationPage taskCreationPage = new TaskCreationPage();
        taskCreationPage.createTask(getProperty("summary"), getProperty("description"), getProperty("version"));

        TaskPage taskPage =  taskCreationPage.clickTask(getProperty("summary"), getProperty("status.todo"));
        taskPage.setInProgressStatus(getProperty("status.inprogress"));
        taskPage.setResolvedStatus(taskCreationPage, getProperty("status.resolved"));
        taskPage.setDoneStatus(getProperty("status.done"));
    }
}