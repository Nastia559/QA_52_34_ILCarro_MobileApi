package utils;

import controllers.CarController;
import dto.CarsDto;
import dto.TokenDto;
import dto.User;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public interface IGetAllCars extends BaseApi {
    default int getCarsCount() {
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        TokenDto tokenDto = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + LOGIN_URL)
                .thenReturn()
                .getBody()
                .as(TokenDto.class);

        CarsDto cars = given()
                .contentType(ContentType.JSON)
                .header(AUTH, tokenDto.getAccessToken())
                .when()
                .get(BASE_URL + GET_ALL_CARS)
                .then()
                .statusCode(200)
                .extract().as(CarsDto.class);
        return cars.getCars().length;
    }
}
