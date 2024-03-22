package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class TaskCreationPage {

    private final SelenideElement taskCreationHeadline = $x("//h2[@id='jira-dialog2__heading']").as("Заголовок диалога \"Создание задачи\"");

    private final SelenideElement summaryInput = $x("//input[@id='summary']").as("Поле для ввода темы бага");
    private final SelenideElement descriptionInput = $x("//textarea[@id='description']").as("Поле для ввода описания бага");

    private final SelenideElement textButton = $x("//button[text()='Текст']").as("Кнопка \"Текст\"");

    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']").as("Кнопка \"Создать\"");
    private final SelenideElement resolveButton = $x("//input[@id='issue-workflow-transition-submit']").as("Кнопка переведения в статус \"РЕШЕНО\"");

    public void verifyTaskCreationForm(){
        taskCreationHeadline.shouldHave(text("Создание задачи"));
    }

    @Step("Выбираем версию")
    private SelenideElement getVersionByText(String version){
        return $x("//select[@id='fixVersions']//option[contains(.,'" + version + "')]").as("Версия для выбора");
    }

    @Step("Создание новой задачи")
    public void createTask(String summary, String description, String version){
        JiraHeaderPage jiraHeaderPage = new JiraHeaderPage();
        jiraHeaderPage.clickCreateButton();
        verifyTaskCreationForm();
        summaryInput.shouldBe(Condition.visible).sendKeys(summary);
        textButton.shouldBe(Condition.visible).click();
        descriptionInput.sendKeys(description);
        getVersionByText(version).shouldBe(Condition.visible).click();
        createButton.shouldBe(Condition.visible).click();
    }

//    @Step("Нажимаем кнопку \"Исполнено\"")
    public void resolveTask (){
        resolveButton.shouldBe(Condition.visible).click();
    }

    @Step("Проверка описания задачи и начального статуса \"СДЕЛАТЬ\"")
    public TaskPage clickTask(String summary, String status1){
        getTaskByText(summary).click();
        TaskPage taskPage = new TaskPage();
        taskPage.verifyTask(summary, status1);
        return taskPage;
    }

    @Step("Находим задачу по названию")
    private SelenideElement getTaskByText(String summary){
        return $x("//div[contains(.,'Запрос')]/a[contains(.,'" + summary + "')]").as("Задача по названию");
    }
}
