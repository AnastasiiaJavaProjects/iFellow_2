package tests;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static steps.JsonSteps.getJsonObject;
import static steps.JsonSteps.postRequest;
import static util.TestProperties.getProperty;

public class JsonTest {

    @Test
    public void test2() throws IOException {

        JSONObject body = getJsonObject(getProperty("jsonobject.path"));
        body.put(getProperty("key1"), getProperty("value1"));
        body.put(getProperty("key2"), getProperty("value2"));

        Response response = postRequest(body);
        Assertions.assertEquals(getProperty("value1"), response.body().path(getProperty("key1")));
        Assertions.assertEquals(getProperty("value2"), response.body().path(getProperty("key2")));
    }
}
