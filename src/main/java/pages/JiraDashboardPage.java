package pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class JiraDashboardPage {

    private final SelenideElement dashboardHeadline = $x("//h1[text()='System Dashboard']");

    public void verifyDashboardHeadline(){
        Assertions.assertEquals("System Dashboard", dashboardHeadline.getText());
    }
}
