package helpers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestHelpers {

    public static Response getApi(String baseUrl, int statusCode){
        return given().baseUri(baseUrl)
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public static Response postApi(String baseUrl, String postUrl, String body, int statusCode){
        return given()
                .header("Content-type", "Application/json")
                .header("charset", "utf-8")
                .baseUri(baseUrl)
                .body(body)
                .when()
                .post(postUrl)
                .then()
                .statusCode(statusCode)
                .log().body()
                .extract()
                .response();
    }
}
