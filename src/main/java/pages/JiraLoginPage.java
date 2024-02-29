package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraLoginPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']");

    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']");

    private final SelenideElement submitButton = $x("//input[@id='login-form-submit']");

    public JiraDashboardPage fillForm(String login, String password){
        loginInput.shouldBe(Condition.visible).sendKeys(login);
        passwordInput.shouldBe(Condition.visible).sendKeys(password);
        submitButton.shouldBe(Condition.visible).click();
        return new JiraDashboardPage();
    }
}
