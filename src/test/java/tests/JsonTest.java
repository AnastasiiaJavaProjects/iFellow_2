package tests;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static steps.JsonSteps.getJsonObject;
import static steps.JsonSteps.postRequest;

public class JsonTest {

    String path = "src/test/resources/json/user.json";

    @Test
    public void test2() throws IOException {

        JSONObject body = getJsonObject(path);
        body.put("name", "Tomato");
        body.put("job", "Eat maket");

        Response response = postRequest(body);
        Assertions.assertEquals("Tomato", response.body().path("name"));
        Assertions.assertEquals("Eat maket", response.body().path("job"));
    }
}
