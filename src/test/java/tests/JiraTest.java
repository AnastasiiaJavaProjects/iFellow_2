package tests;

import hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.refresh;

public class JiraTest extends WebHooks {

    private final JiraHeaderPage jiraHeaderPage = new JiraHeaderPage();
    private final JiraDashboardPage jiraDashboardPage = new JiraDashboardPage();
    private TestProjectPage testProjectPage;
    private TaskPage taskPage;
    private TaskCreationPage taskCreationPage;

    String summary1 = "TestSelenium";
    String summary2 = "Не работает кнопка \"Сохранить\" на вкладке \"Мои предложения\"";
    String description = "Описание";
    String version = "Version 2.0";
    String status1 = "СДЕЛАТЬ";
    String status2 = "В РАБОТЕ";
    String status3 = "РЕШЕННЫЕ";
    String status4 = "ГОТОВО";


    @DisplayName("Проверка авторизации в Jira")
    @Test
    public void checkJiraDashboardPage(){
        jiraDashboardPage.verifyDashboardHeadline();
    }

    @DisplayName("Открытие проекта")
    @Test
    public void goToTestProject(){
        testProjectPage = jiraHeaderPage.clickTestProject();
        testProjectPage.verifyTestProject();
    }

    @DisplayName("Проверка общего количества заведенных задач в проекте")
    @Test
    public void checkTasks(){
        testProjectPage = jiraHeaderPage.clickTestProject();
        testProjectPage.verifyTasksNumberIsPresent();
    }

    @DisplayName("Проверка статуса задачи и версии")
    @Test
    public void checkTestSeleniumTask(){
        taskPage = jiraHeaderPage.searchTask(summary1);
        taskPage.verifySummary(summary1);
        taskPage.verifyStatus(status1);
        taskPage.verifyVersion(version);
    }

    @DisplayName("Создание нового бага")
    @Test
    public void createNewTask(){
        testProjectPage = jiraHeaderPage.clickTestProject();
        int currentTasksNumber = testProjectPage.readTasksNumber();

        //Creating a new task
        taskCreationPage = jiraHeaderPage.clickCreateButton();
        taskCreationPage.verifyTaskCreationForm();
        taskCreationPage.createTask(summary2, description, version);

        //Verifying task number change
        refresh();
        testProjectPage.checkTasksNumber(currentTasksNumber);

        //Changing statuses
        taskPage = testProjectPage.sortByCreatedDate(summary2);
        taskPage.verifySummary(summary2);
        taskPage.verifyStatus(status1);

        //Set "In progress" status
        taskPage.setInProgressStatus();
        taskPage.verifyStatus(status2);

        //Set "Resolved" status
        taskPage.setResolvedStatus();
        taskCreationPage.resolveTask();
        taskPage.verifyStatus(status3);

        //Set "Done" status
        taskPage.setDoneStatus();
        taskPage.verifyStatus(status4);
    }
}
