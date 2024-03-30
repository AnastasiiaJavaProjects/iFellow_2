package hooks;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class WebHooks {

    @BeforeAll
    public static void beforeAll(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide(Allure.getLifecycle()));
    }
}
