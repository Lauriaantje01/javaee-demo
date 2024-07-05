package com.infosupport;

import com.google.gson.Gson;
import com.infosupport.domein.Aangifte;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jupiter.MicroShedTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicroShedTest
@SharedContainerConfig(AppDeploymentConfig.class)
public class AppIT {

    @Test
    void whenGetAangiftesItSucceeds() {
        given().contentType("application/json")
                .when().get("/api/aangiftes")
                .then().statusCode(200);
    }

    @Test
    void whenPostAangifteSucceeds() {
        String jsonAangifte = """
                {
                  "bsn": "1258945",
                  "bedrag": 1200
                }""";

        Response response = given().contentType("application/json")
                .body(jsonAangifte)
                .when().post("/api/aangiftes")
                .then().statusCode(200)
                .extract().response();

        Aangifte aangifte = new Gson().fromJson(response.getBody().asString(), Aangifte.class);

        Response getResponse = given().contentType("application/json")
                .when().get("/api/aangiftes/{id}", aangifte.getId())
                .then().statusCode(200)
                .extract().response();

        Aangifte aangifteGet = new Gson().fromJson(getResponse.getBody().asString(), Aangifte.class);

        assertEquals(aangifte.getId(), aangifteGet.getId());
    }
}
