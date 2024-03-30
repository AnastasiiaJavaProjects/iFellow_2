package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static helpers.RestHelpers.postApi;
import static util.TestProperties.getProperty;

public class JsonSteps {

    private Response response;
    private JSONObject body;

    @Дано("объект из файла .json")
    public void getJsonObject() throws IOException {
        body = new JSONObject(new String(Files.readAllBytes(Paths.get(getProperty("jsonobject.path")))));
    }

    @Когда("отправляем POST запрос с новыми полями объекта")
    public void postRequest(){
        body.put(getProperty("key1"), getProperty("value1"));
        body.put(getProperty("key2"), getProperty("value2"));
        response = postApi(getProperty("json.baseurl"), getProperty("json.posturl"), body.toString(), 201);
    }

    @Тогда("проверяем, что полученный response имеет валидные данные по значениям key и value")
    public void verifyValues(){
        Assertions.assertEquals(getProperty("value1"), response.body().path(getProperty("key1")));
        Assertions.assertEquals(getProperty("value2"), response.body().path(getProperty("key2")));
    }
}
