package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class TestProjectPage {

    private final SelenideElement testSummary = $x("//h1//a[@title='Test']");

    private final SelenideElement tasksHeadline = $x("//span[text()='Задачи']");

    private final SelenideElement numberOfTasks = $x("//div[@class='showing']//span");

    private final SelenideElement sortCriteria = $x("//div[@class='order-by-fields']");
    private final SelenideElement sortByCreationDate = $x("//div[@id='order-by-options-multi-select']//li[1]");

    public TestProjectPage(){
        tasksHeadline.shouldBe(Condition.visible).click();
    }

    public void verifyTestProject(){
        Assertions.assertEquals("https://edujira.ifellow.ru/projects/TEST/summary", testSummary.getAttribute("href"));
    }

    public void verifyTasksNumberIsPresent(){
        Assertions.assertTrue(numberOfTasks.getText().startsWith("1 из "));
    }

    public int readTasksNumber(){
        return Integer.parseInt(numberOfTasks.getText().substring(5));
    }
    public void checkTasksNumber(int oldTasksNumber) {
        String expected = "1 из " + (oldTasksNumber + 1);
        $x("//div[@class='showing']//span").shouldHave(text(expected), Duration.ofSeconds(5));
    }

    public TaskPage sortByCreatedDate(String summary){
        sortCriteria.shouldBe(Condition.visible).click();
        sortByCreationDate.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();
        $x("//span[.='" + summary + "']").click();
        return new TaskPage();
    }
}
