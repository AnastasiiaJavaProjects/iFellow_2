package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class JiraDashboardPage {

    private final SelenideElement dashboardHeadline = $x("//h1[text()='System Dashboard']").as("Заголовок \"System Dashboard\"");

    @Step("Проверка заголовка \"System Dashboard\"")
    public void verifyDashboardHeadline(){
        dashboardHeadline.shouldHave(text("System Dashboard"));
    }
}
