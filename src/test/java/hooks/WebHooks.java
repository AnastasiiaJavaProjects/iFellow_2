package hooks;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.CustomAllureSelenide;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;

public class WebHooks {

    @BeforeEach
    public void beforeEach(){
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide(Allure.getLifecycle()));
    }
}
