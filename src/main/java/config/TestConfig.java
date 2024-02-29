package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static constants.Constants.Path.testApiPath;
import static constants.Constants.Servers.testApiUrl;

public class TestConfig {

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
