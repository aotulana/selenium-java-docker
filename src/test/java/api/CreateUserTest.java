package api;

import api.requestModel.User;
import api.responseModel.CreateUserResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.ExtentReporter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static utilities.JsonFileUtil.getTestUsersFromJsonFile;

@Listeners(ExtentReporter.class)

public class CreateUserTest {
    static final String BASE_URI = "https://reqres.in";
    static final String BASE_PATH = "/api";
    static final String USERS_ENDPOINT = "/users";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    @Test (dataProvider = "getTestData")
    public void should_CreateUser_When_RequestIsValid(User testUser) throws IOException {
        Response response =
                given().
                        contentType(ContentType.JSON).
                        body(testUser).
                when().
                        post(USERS_ENDPOINT).
                then().
                        log().body().
                        extract().
                        response();

        CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);

        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 2, "Response Time took longer than 2 seconds");
        Assert.assertEquals(response.getStatusCode(), 201, "Incorrect Status Code");
        Assert.assertEquals(createUserResponse.getName(), testUser.getName());
        Assert.assertEquals(createUserResponse.getJob(), testUser.getJob());
    }

    @DataProvider
    public static User[] getTestData() throws IOException {
        return getTestUsersFromJsonFile();
    }
}
