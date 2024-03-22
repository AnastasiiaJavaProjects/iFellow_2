package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {

    private final SelenideElement summaryHeadline = $x("//h1[@id='summary-val']").as("Заголовок \"Тема бага\"");
    private final SelenideElement statusField = $x("//span[@id='status-val']").as("Поле статуса бага");
    private final SelenideElement inProgressButton = $x("//span[contains(.,'В работе')]").as("Кнопка перевода бага в статус \"В РАБОТЕ\"");
    private final SelenideElement businessProcessButton = $x("//span[contains(.,'Бизнес-процесс')]").as("Кнопка \"Бизнес-процесс\"");
    private final SelenideElement resolvedButton = $x("//span[contains(.,'Исполнено')]").as("Кнопка перевода бага в статус \"РЕШЕННЫЕ\"");
    private final SelenideElement doneButton = $x("//span[contains(.,'Выполнено')]").as("Кнопка перевода бага в статус \"ГОТОВО\"");

    public void verifyTask(String summary, String status1){
        summaryHeadline.shouldHave(text(summary));
        verifyStatus(status1);
    }

    public void verifyStatus(String status){
        statusField.shouldHave(text(status), Duration.ofSeconds(5));
    }

    @Step("Перевод задачи в статус \"В РАБОТЕ\"")
    public void setInProgressStatus(String status2){
        inProgressButton.shouldBe(Condition.visible).click();
        verifyStatus(status2);
    }

    @Step("Перевод задачи в статус \"РЕШЕННЫЕ\"")
    public void setResolvedStatus(TaskCreationPage taskCreationPage, String status3){
        businessProcessButton.shouldBe(Condition.visible).click();
        resolvedButton.shouldBe(Condition.visible).click();
        taskCreationPage.resolveTask();
        verifyStatus(status3);
    }

    @Step("Перевод задачи в статус \"ГОТОВО\"")
    public void setDoneStatus(String status4){
        businessProcessButton.shouldBe(Condition.visible).click();
        doneButton.shouldBe(Condition.visible).click();
        verifyStatus(status4);
    }
}
