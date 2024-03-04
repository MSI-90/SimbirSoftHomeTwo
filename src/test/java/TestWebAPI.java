import config.TestConfig;
import constants.Constants;
import helpers.GetAllFilter;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.Response;
import pojo.Root;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class TestWebAPI extends TestConfig {
    protected String getAll = Constants.Actions.getAll;
    protected String getById = Constants.Actions.getId;
    protected String patch = Constants.Actions.patch;
    protected  String delete = Constants.Actions.delete;
    protected String post = Constants.Actions.create;
    public int recordId = 0;

    @Test
    public void getAll() {
        List<Response> responces = given().log().uri().
                when().get(getAll).
                then().spec(responseSpecForGet).log().body().statusCode(200)
                .extract().response().jsonPath().getList("entity", Response.class);

        if (responces.size() > 0){
            Assert.assertTrue(true, "ok");
        }
    }

    @Test
    public void getAllWithParams() throws UnsupportedEncodingException {
        String sortByTitle = "Title of new Entity";
        boolean verified = true;
        Map<String, String> parametrs = new HashMap<>();
        parametrs.put("title", sortByTitle);
        parametrs.put("verified", String.valueOf(verified));
        parametrs.put("page", "1");
        parametrs.put("perPage", "1");

        var responce = given().params(parametrs).log().uri().
                when().get(getAll).
                then().log().body().statusCode(200)
                .extract().response().getBody();

        String responceAsString = responce.asString();
        SoftAssert ass = new SoftAssert();
        /*
        ass.assertEquals(responceAsString.contains(parametrs.get("title")), parametrs.get("title"));
        ass.assertEquals(responceAsString.contains(parametrs.get("verified")), parametrs.get("verified"));
        ass.assertEquals(responceAsString.contains(parametrs.get("page")), parametrs.get("page"));
        ass.assertEquals(responceAsString.contains(parametrs.get("perPage")), parametrs.get("perPage"));
        ass.assertAll();
        */

    }

    @Test
    public void getById(){
        recordId = 19;
        var responce = given().log().uri().
                when().get(getById + recordId).
                then().body("id", equalTo(recordId)).log().all().statusCode(200)
                .extract().as(Response.class, ObjectMapperType.GSON);

        Assert.assertEquals(responce.id, recordId);
    }

    @Test
    public void patch(){
        int id = 3;

        Root pojoPut = new Root();
        pojoPut.addition = pojoPut.new Addition();
        pojoPut.addition.additional_info = "new Record";
        pojoPut.addition.additional_number = 440;
        pojoPut.important_numbers = new ArrayList<Integer>(){};
        pojoPut.important_numbers.add(9);
        pojoPut.important_numbers.add(67);
        pojoPut.important_numbers.add(34);
        pojoPut.title = "Title of new Entity";
        pojoPut.verified = false;

        var recordId = given().body(pojoPut).log().uri()
                .when().patch(patch + id)
                .then().log().body().statusCode(204);

        var responce = given().log().uri().
                when().get(getById + id).
                then().body("id", equalTo(id)).log().all().statusCode(200)
                .extract().as(Response.class, ObjectMapperType.GSON);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(pojoPut.title, responce.title);
        soft.assertEquals(pojoPut.important_numbers.get(0), responce.important_numbers.get(0));
        soft.assertEquals(pojoPut.important_numbers.get(1), responce.important_numbers.get(1));
        soft.assertEquals(pojoPut.important_numbers.get(2), responce.important_numbers.get(2));
        soft.assertEquals(pojoPut.addition.additional_number, responce.addition.additional_number);
        soft.assertEquals(pojoPut.addition.additional_info, responce.addition.additional_info);
        soft.assertEquals(pojoPut.verified, responce.verified);
        soft.assertAll();
    }

    @Test
    public void Delete(){
        recordId = 4;
        given().log().uri().
                when().delete(delete + recordId).
                then().log().body().statusCode(204)
                .extract().body().toString();
    }

    @Test
    public void Create(){
        Root pojoPost = new Root();
        pojoPost.addition = pojoPost.new Addition();
        pojoPost.addition.additional_info = "new Record";
        pojoPost.addition.additional_number = 440;
        pojoPost.important_numbers = new ArrayList<Integer>(){};
        pojoPost.important_numbers.add(90);
        pojoPost.important_numbers.add(7);
        pojoPost.important_numbers.add(3);
        pojoPost.title = "Title of new Entity";
        pojoPost.verified = false;

        var newRecordId = given().body(pojoPost).log().uri()
                .when().post(post)
                .then().spec(responseSpecForPost).log().body().statusCode(200);

    }
}
