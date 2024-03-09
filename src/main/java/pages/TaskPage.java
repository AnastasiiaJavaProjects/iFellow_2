package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {

    private final SelenideElement summaryHeadline = $x("//h1[@id='summary-val']");

    private final SelenideElement statusField = $x("//span[@id='status-val']");
    private final SelenideElement versionField = $x("//span[@id='fixVersions-field']");

    private final SelenideElement inProgressButton = $x("//span[contains(.,'В работе')]");
    private final SelenideElement businessProcessButton = $x("//span[contains(.,'Бизнес-процесс')]");
    private final SelenideElement resolvedButton = $x("//span[contains(.,'Исполнено')]");
    private final SelenideElement doneButton = $x("//span[contains(.,'Выполнено')]");

    public void verifySummary(String summary){
        Assertions.assertEquals(summary, summaryHeadline.getText());
    }

    public void verifyStatus(String status){
        statusField.shouldHave(text(status), Duration.ofSeconds(5));
        Assertions.assertEquals(status, statusField.getText());
    }

    public void verifyVersion(String version){
        Assertions.assertEquals(version, versionField.getText());
    }

    public void setInProgressStatus(){
        inProgressButton.shouldBe(Condition.visible).click();
    }

    public void setResolvedStatus(){
        businessProcessButton.shouldBe(Condition.visible).click();
        resolvedButton.shouldBe(Condition.visible).click();
    }

    public void setDoneStatus(){
        businessProcessButton.shouldBe(Condition.visible).click();
        doneButton.shouldBe(Condition.visible).click();
    }
}