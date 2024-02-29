package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static constants.Constants.Path.testApiPath;
import static constants.Constants.Servers.testApiUrl;

public class TestConfig {

    protected ResponseSpecification responseSpecForGet = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    protected ResponseSpecification responseSpecForPost = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = testApiUrl;
        RestAssured.basePath = testApiPath;

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .addHeader("Content-Type","application/json")
                .addCookie("testCookie")
                .build();

        RestAssured.requestSpecification = requestSpec;
    }
}
