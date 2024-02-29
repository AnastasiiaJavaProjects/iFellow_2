import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class JiraTest {

    private final JiraHeaderPage jiraHeaderPage = new JiraHeaderPage();
    private JiraDashboardPage jiraDashboardPage;
    private TestProjectPage testProjectPage;
    private TaskPage taskPage;
    private TaskCreationPage taskCreationPage;

    @BeforeEach
    public void loginToJira(){
        openPage("https://edujira.ifellow.ru/login.jsp");
        JiraLoginPage jiraLoginPage = new JiraLoginPage();
        jiraDashboardPage = jiraLoginPage.fillForm("AT13", "Qwerty123");
    }

    @Test
    public void checkJiraDashboardPage(){
        jiraDashboardPage.verifyDashboardHeadline();
    }

    @Test
    public void goToTestProject(){
        testProjectPage = jiraHeaderPage.clickTestProject();
        testProjectPage.verifyTestProject();
    }

    @Test
    public void checkTasks(){
        testProjectPage = jiraHeaderPage.clickTestProject();
        testProjectPage.verifyTasksNumberIsPresent();
    }

    @Test
    public void checkTestSeleniumTask(){
        String summary = "TestSelenium";
        String status = "СДЕЛАТЬ";
        String version = "Version 2.0";

        taskPage = jiraHeaderPage.searchTask(summary);
        taskPage.verifySummary(summary);
        taskPage.verifyStatus(status);
        taskPage.verifyVersion(version);
    }

    @Test
    public void createNewTask(){
        String summary = "Не работает кнопка \"Сохранить\" на вкладке \"Мои предложения\"";
        String description = "Описание";
        String version = "Version 2.0";
        String status1 = "СДЕЛАТЬ";
        String status2 = "В РАБОТЕ";
        String status3 = "РЕШЕННЫЕ";
        String status4 = "ГОТОВО";

        testProjectPage = jiraHeaderPage.clickTestProject();
        int currentTasksNumber = testProjectPage.readTasksNumber();

        //Creating a new task
        taskCreationPage = jiraHeaderPage.clickCreateButton();
        taskCreationPage.verifyTaskCreationForm();
        taskCreationPage.createTask(summary, description, version);

        //Verifying task number change
        refresh();
        testProjectPage.checkTasksNumber(currentTasksNumber);

        //Changing statuses
        taskPage = testProjectPage.sortByCreatedDate(summary);
        taskPage.verifySummary(summary);
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

    private void openPage(String url) {
        open(url);
        getWebDriver().manage().window().maximize();
    }
}
