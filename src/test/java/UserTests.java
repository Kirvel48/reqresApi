import org.junit.jupiter.api.*;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class UserTests {
    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    void successGetUsersListTest() {
        given()
                .log().uri()
                .when()
                .queryParam("page", "1")
                .get("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[0].id", notNullValue());
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
                .statusCode(201)
                .body("createdAt", notNullValue());

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
                .statusCode(200)
                .body("name", is("kir"));
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
                .statusCode(200)
                .body("updatedAt", notNullValue());
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
                .statusCode(200)
                .body("data.email", notNullValue());
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
