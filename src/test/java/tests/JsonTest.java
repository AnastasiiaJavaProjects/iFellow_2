package tests;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static helpers.RestHelpers.postApi;

public class JsonTest {

    @Test
    public void test2() throws IOException {

        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/user.json"))));
        body.put("name", "Tomato");
        body.put("job", "Eat maket");

        Response response = postApi("https://reqres.in/", "/api/users", body.toString(), 201);
        Assertions.assertEquals("Tomato", response.body().path("name"));
        Assertions.assertEquals("Eat maket", response.body().path("job"));
    }
}
