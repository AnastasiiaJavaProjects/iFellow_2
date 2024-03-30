package tests;

import hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TaskCreationPage;
import pages.TaskPage;

import static tests.TestData.*;

@DisplayName("JiraTest")
public class JiraTest extends WebHooks {

    @DisplayName("Создание новой задачи и проведение ее по статусам")
    @Test
    public void createNewTask(){
        TaskCreationPage taskCreationPage = new TaskCreationPage();
        taskCreationPage.createTask(SUMMARY, DESCRIPTION, VERSION);

        TaskPage taskPage =  taskCreationPage.clickTask(SUMMARY, TODO_STATUS);
        taskPage.setInProgressStatus(IN_PROGRESS_STATUS);
        taskPage.setResolvedStatus(taskCreationPage, RESOLVED_STATUS);
        taskPage.setDoneStatus(DONE_STATUS);
    }
}