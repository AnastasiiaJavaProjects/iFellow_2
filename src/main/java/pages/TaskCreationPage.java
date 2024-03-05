package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class TaskCreationPage {

    private final SelenideElement taskCreationHeadline = $x("//h2[@id='jira-dialog2__heading']");

    private final SelenideElement summaryInput = $x("//input[@id='summary']");
    private final SelenideElement descriptionInput = $x("//textarea[@id='description']");

    private final SelenideElement textButton = $x("//button[text()='Текст']");

    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']");
    private final SelenideElement resolveButton = $x("//input[@id='issue-workflow-transition-submit']");

    public void verifyTaskCreationForm(){
        Assertions.assertEquals("Создание задачи", taskCreationHeadline.getText());
    }

    private SelenideElement getVersionByText(String version){
        return $x("//select[@id='fixVersions']//option[contains(.,'" + version + "')]");
    }

    public void createTask(String summary, String description, String version){
        summaryInput.shouldBe(Condition.visible).sendKeys(summary);
        textButton.shouldBe(Condition.visible).click();
        descriptionInput.sendKeys(description);
        getVersionByText(version).shouldBe(Condition.visible).click();
        createButton.shouldBe(Condition.visible).click();
    }

    public void resolveTask (){
        resolveButton.shouldBe(Condition.visible).click();
    }
}
