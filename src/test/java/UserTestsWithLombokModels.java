import io.restassured.RestAssured;
import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.UserTestsSpec.*;
import static io.qameta.allure.Allure.step;


public class UserTestsWithLombokModels {
    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    void successGetUsersListTest() {
        int desiredStatusCode = 200;

        UserTestsListResponseModel response = step("Send request", () -> given(standartRequestSpec)
                .when()
                .queryParam("page", "1")
                .get("/users")
                .then()
                .spec(standartResponseSpec(desiredStatusCode))
                .extract().as(UserTestsListResponseModel.class));
        step("Check response", () -> {
            UserTestsListResponseModel.UserData firstUser = response.getData().get(0);
            assertNotNull(firstUser.getId());
        });
    }

    @Test
    void successCreateUserTest() {
        UserTestsRequestModel userTestsRequestModel = new UserTestsRequestModel();
        userTestsRequestModel.setName("kir");
        userTestsRequestModel.setJob("leader");
        int desiredStatusCode = 201;


        UserTestsCreateResponseModel response = step("Send request", () -> given(standartRequestSpec)
                .body(userTestsRequestModel)
                .when()
                .post("/users")
                .then()
                .spec(standartResponseSpec(desiredStatusCode))
                .extract().as(UserTestsCreateResponseModel.class));
        step("Check response", () -> {
            assertEquals("kir", response.getName());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    void successPostUpdateUserTest() {
        UserTestsRequestModel userTestsRequestModel = new UserTestsRequestModel();
        userTestsRequestModel.setName("kir");
        userTestsRequestModel.setJob("qa-leader");
        int desiredStatusCode = 200;

        UserTestsUpdateResponseModel response = step("Send request", () -> given(standartRequestSpec)
                .body(userTestsRequestModel)
                .when()
                .put("/users/413")
                .then()
                .spec(standartResponseSpec(desiredStatusCode))
                .extract().as(UserTestsUpdateResponseModel.class));
        step("Check response", () -> {
            assertEquals("kir", response.getName());
        });
    }

    @Test
    void successPutUpdateUserTest() {
        UserTestsRequestModel userTestsRequestModel = new UserTestsRequestModel();
        userTestsRequestModel.setName("nik");
        int desiredStatusCode = 200;
        UserTestsUpdateResponseModel response = step("Send request", () -> given(standartRequestSpec)
                .body(userTestsRequestModel)
                .when()
                .put("/users/413")
                .then()
                .spec(standartResponseSpec(desiredStatusCode))
                .extract().as(UserTestsUpdateResponseModel.class));
        step("Check response", () -> {
            assertEquals("nik", response.getName());
        });
    }


    @Test
    void successGetUserInfoTest() {
        int desiredStatusCode = 200;

        UserTestsGetInfoResponseModel userTestsGetInfoResponseModel = step("Send request", () -> given(standartRequestSpec)
                .when()
                .get("/users/4")
                .then()
                .spec(standartResponseSpec(desiredStatusCode))
                .extract().as(UserTestsGetInfoResponseModel.class));
        step("Check response", () -> {
            assertNotNull(userTestsGetInfoResponseModel.getData().getEmail());
        });
    }

    @Test
    void successDeleteUserTest() {
        int desiredStatusCode = 204;
        step("Send request", () -> given(standartRequestSpec)
                .delete("/users/5")
                .then()
                .spec(standartResponseSpec(desiredStatusCode)));

    }
}
