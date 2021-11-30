package config.helpers;

import config.setup.TestBase;

import static io.restassured.RestAssured.given;

public class Browserstack extends TestBase {

    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(userName, mobileKey)
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response()
                .path("automation_session.video_url");
    }
}
