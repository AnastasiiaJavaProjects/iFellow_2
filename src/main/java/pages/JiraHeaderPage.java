package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraHeaderPage {

    SelenideElement projects = $x("//a[@id='browse_link']");
    SelenideElement testProject = $x("//a[@id='admin_main_proj_link_lnk']");

    SelenideElement create = $x("//a[@id='create_link']");

    SelenideElement searchInput = $x("//input[@id='quickSearchInput']");

    public TestProjectPage clickTestProject(){
        projects.shouldBe(Condition.visible).click();
        testProject.shouldBe(Condition.visible).click();
        return new TestProjectPage();
    }

    public TaskPage searchTask(String summary){
        searchInput.shouldBe(Condition.visible).sendKeys(summary);
        searchInput.pressEnter();
        return new TaskPage();
    }

    public TaskCreationPage clickCreateButton(){
        create.shouldBe(Condition.visible).click();
        return new TaskCreationPage();
    }
}
