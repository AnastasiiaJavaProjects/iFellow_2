package tests;

import hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

@DisplayName("JiraTest")
public class JiraTest extends WebHooks {

    String summary = "Не работает кнопка \"Сохранить\" на вкладке \"Мои предложения\"";
    String description = "Описание";
    String version = "Version 2.0";
    String status1 = "СДЕЛАТЬ";
    String status2 = "В РАБОТЕ";
    String status3 = "РЕШЕННЫЕ";
    String status4 = "ГОТОВО";

    @DisplayName("Создание новой задачи и проведение ее по статусам")
    @Test
    public void createNewTask(){
        TaskCreationPage taskCreationPage = new TaskCreationPage();
        taskCreationPage.createTask(summary, description, version);

        TaskPage taskPage =  taskCreationPage.clickTask(summary, status1);
        taskPage.setInProgressStatus(status2);
        taskPage.setResolvedStatus(taskCreationPage, status3);
        taskPage.setDoneStatus(status4);
    }
}