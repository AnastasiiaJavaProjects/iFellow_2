package util;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.CustomAllureSelenide;
import io.qameta.allure.Allure;
import pages.JiraDashboardPage;
import pages.JiraLoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private static Properties PROPERTIES;

    static{
        try(FileInputStream fileInputStream = new FileInputStream("src/test/resources/test.properties")){
           PROPERTIES = new Properties();
           PROPERTIES.load(fileInputStream);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return PROPERTIES.getProperty(key);
    }

    public static void login(){
        JiraLoginPage jiraLoginPage = new JiraLoginPage();
        JiraDashboardPage jiraDashboardPage = jiraLoginPage.fillForm(getProperty("login"), getProperty("password"));
        jiraDashboardPage.verifyDashboardHeadline();
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide(Allure.getLifecycle()));
    }
}
