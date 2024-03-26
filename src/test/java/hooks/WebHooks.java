package hooks;

import org.junit.jupiter.api.BeforeEach;
import util.TestProperties;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static util.TestProperties.getProperty;

public class WebHooks {

    @BeforeEach
    public void loginToJira(){
        openPage(getProperty("url"));
        TestProperties.login();
    }

    private void openPage(String url) {
        open(url);
        getWebDriver().manage().window().maximize();
    }
}
