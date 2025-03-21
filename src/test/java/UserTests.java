import org.junit.jupiter.api.*;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class UserTests {
    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    void successGetUsersListTest() {
        given()
                .log().uri()
                .when()
                .get("/users?page=1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void successCreateUserTest() {
        String userData = """
                {
                    "name": "kir",
                    "job": "leader"
                }
                """;
        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

    }

    @Test
    void successPostUpdateUserTest() {
        String userData = """
                {
                    "name": "kir",
                    "job": "qa-leader"
                }
                """;
        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("/users/413")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void successPutUpdateUserTest() {
        String userData = """
                {
                    "name": "kirVel"
                }
                """;
        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("/users/413")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void successGetUserInfoTest() {
        given()
                .log().uri()
                .when()
                .get("/users/4")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void successDeleteUserTest() {
        given()
                .log().uri()
                .delete("/users/5")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

}


