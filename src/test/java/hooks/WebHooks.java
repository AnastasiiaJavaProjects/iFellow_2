package hooks;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.CustomAllureSelenide;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;
import pages.JiraDashboardPage;
import pages.JiraLoginPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static util.TestProperties.getProperty;

public class WebHooks {

    @BeforeEach
    public void loginToJira(){
        openPage(getProperty("url"));
        JiraLoginPage jiraLoginPage = new JiraLoginPage();
        JiraDashboardPage jiraDashboardPage = jiraLoginPage.fillForm(getProperty("login"), getProperty("password"));
        jiraDashboardPage.verifyDashboardHeadline();
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide(Allure.getLifecycle()));
    }

    private void openPage(String url) {
        open(url);
        getWebDriver().manage().window().maximize();
    }
}
