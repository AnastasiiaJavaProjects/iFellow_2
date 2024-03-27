package steps;

import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static helpers.RestHelpers.postApi;

public class JsonSteps {

    public static JSONObject getJsonObject(String path) throws IOException {
        return new JSONObject(new String(Files.readAllBytes(Paths.get(path))));
    }

    public static Response postRequest(JSONObject body){
        return postApi("https://reqres.in/", "/api/users", body.toString(), 201);
    }
}
