package steps;

import hooks.WebHooks;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.*;

public class JiraSteps extends WebHooks {

    private final JiraDashboardPage jiraDashboardPage = new JiraDashboardPage();
    private final JiraHeaderPage jiraHeaderPage = new JiraHeaderPage();
    private TestProjectPage testProjectPage;
    private TaskPage taskPage;
    private TaskCreationPage taskCreationPage;

    String summary = "Не работает кнопка \"Сохранить\" на вкладке \"Мои предложения\"";
    String description = "Описание";
    String version = "Version 2.0";
    String status1 = "СДЕЛАТЬ";

    @Когда("вводим логин и пароль")
    public void doLogin() {
        loginToJira();
    }

    @Тогда("осуществляется вход на страницу Dashboard")
    public void checkJiraDashboardPage() {
        jiraDashboardPage.verifyDashboardHeadline();
    }

    @Когда("выбираем проект")
    public void goToTestProject() {
        testProjectPage = jiraHeaderPage.clickTestProject();
    }

    @Тогда("осуществляется вход на страницу проекта")
    public void verifyTestProject() {
        testProjectPage.verifyTestProject();
    }

    @Тогда("отображается общее количество задач в проекте")
    public void checkTasks() {
        testProjectPage.verifyTasksNumberIsPresent();
    }

    @Тогда("осуществляется вход на страницу задачи")
    public void verifyTaskSummary() {
        taskPage.verifySummary(summary);
    }

    @Тогда("отображается статус задачи")
    public void verifyTaskStatus() {
        taskPage.verifyStatus(status1);
    }

    @Тогда("отображается версия, указанная в задаче")
    public void verifyTaskVersion() {
        taskPage.verifyVersion(version);
    }

    @Когда("нажимаем кнопку Create")
    public void openTaskCreationForm() {
        taskCreationPage = jiraHeaderPage.clickCreateButton();
    }

    @Тогда("открывается форма создания бага")
    public void verifyTaskCreationFormOpen() {
        taskCreationPage.verifyTaskCreationForm();
    }

    @Когда("заполняем форму Create и нажимаем Создать")
    public void createNewTask() {
        taskCreationPage.createTask(summary, description, version);
    }

    @Когда("выбираем созданную задачу")
    public void findProject() {
        taskPage = testProjectPage.clickTask(summary);
    }

    @Когда("устанавливаем статус {string}")
    public void setStatus(String newStatus){
        switch(newStatus){
            case "В РАБОТЕ":
                taskPage.setInProgressStatus();
                break;
            case "РЕШЕННЫЕ":
                taskPage.setResolvedStatus();
                taskCreationPage.resolveTask();
                break;
            case "ГОТОВО":
                taskPage.setDoneStatus();
                break;
        }
    }

    @Тогда("проверяем статус {string}")
    public void checkStatus(String expectedStatus){
        taskPage.verifyStatus(expectedStatus);
    }
}