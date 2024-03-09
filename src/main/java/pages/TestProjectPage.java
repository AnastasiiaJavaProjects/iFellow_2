package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class TestProjectPage {

    private final SelenideElement testSummary = $x("//h1//a[@title='Test']");

    private final SelenideElement tasksHeadline = $x("//span[text()='Задачи']");

    private final SelenideElement numberOfTasks = $x("//div[@class='showing']//span");

    public TestProjectPage(){
        tasksHeadline.shouldBe(Condition.visible).click();
    }

    public void verifyTestProject(){
        Assertions.assertEquals("https://edujira.ifellow.ru/projects/TEST/summary", testSummary.getAttribute("href"));
    }

    public void verifyTasksNumberIsPresent(){
        Assertions.assertTrue(numberOfTasks.getText().startsWith("1 из "));
    }

    public TaskPage clickTask(String summary){
        getTaskByText(summary).click();
        return new TaskPage();
    }

    private SelenideElement getTaskByText(String summary){
        return $x("//div[contains(.,'Запрос')]/a[contains(.,'" + summary + "')]");
    }
}