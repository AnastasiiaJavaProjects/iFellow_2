package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraHeaderPage {

    SelenideElement create = $x("//a[@id='create_link']").as("Кнопка открытия формы создания бага");

    public void clickCreateButton(){
        create.shouldBe(Condition.visible).click();
    }
}
