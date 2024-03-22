package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class JiraLoginPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']").as("Поле для логина");

    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']").as("Поле для пароля");

    private final SelenideElement submitButton = $x("//input[@id='login-form-submit']").as("Кнопка подтверждения ввода логина и пароля");

    @Step("Вводим логин и пароль")
    public JiraDashboardPage fillForm(String login, String password){
        loginInput.shouldBe(Condition.visible).sendKeys(login);
        passwordInput.shouldBe(Condition.visible).sendKeys(password);
        submitButton.shouldBe(Condition.visible).click();
        return new JiraDashboardPage();
    }
}
