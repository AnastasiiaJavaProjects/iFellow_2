package hooks;

import org.junit.jupiter.api.BeforeEach;
import pages.JiraLoginPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static util.TestProperties.getProperty;

public class WebHooks {

    @BeforeEach
    public void loginToJira(){
        openPage(getProperty("url"));
        JiraLoginPage jiraLoginPage = new JiraLoginPage();
        jiraLoginPage.fillForm(getProperty("login"), getProperty("password"));
    }

    private void openPage(String url) {
        open(url);
        getWebDriver().manage().window().maximize();
    }
}
