import config.TestConfig;
import constants.Constants;
import helpers.GetAllFilter;
import org.testng.annotations.Test;
import pojo.Root;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class FistTest extends TestConfig {
    protected String getAll = Constants.Actions.getAll;
    protected String getById = Constants.Actions.getId;
    protected String patch = Constants.Actions.patch;
    protected  String delete = Constants.Actions.delete;
    protected String post = Constants.Actions.create;

    @Test
    public void getAll(){
        GetAllFilter filter = new GetAllFilter("", true, 1, 1);


        given().log().uri().
                when().get(getAll).
                then().spec(responseSpecForGet).log().body().statusCode(200);
    }

    @Test
    public void getById(){
        int id = 3;
        given().log().uri().
                when().get(getById + id).
                then().log().all().statusCode(200);
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
    }

    @Test
    public void Delete(){
        int id = 4;
        given().log().uri().
                when().delete(delete + id).
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

        var recordId = given().body(pojoPost).log().uri()
                .when().post(post)
                .then().spec(responseSpecForPost).log().body().statusCode(200);
                //.extract().path("title");
    }
}
