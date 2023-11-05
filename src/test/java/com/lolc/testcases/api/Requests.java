package com.lolc.testcases.api;

import com.lolc.base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Requests extends TestBase {

    String getListOfAllObjectsEndPoint = "/objects";
    String addObjectEndPoint = "/objects";
    String updateObjectEndPoint = "/objects/7";
    String deleteObjectEndPoint = "/objects/6";

    // get request
    @Test(priority = 1)
    public void getListOfAllObjects() throws IOException {

        baseURI = getUrl();

        given().contentType(ContentType.JSON)
                .when()
                .get(getListOfAllObjectsEndPoint)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .and()
                .body("[0].name", equalTo("Google Pixel 6 Pro"));

    }

    // post request
    @Test(priority = 2)
    public void addObject() throws IOException {

        baseURI = getUrl();

        given().contentType(ContentType.JSON)
                .when()
                .body(getPayloadString("add-object.json"))
                .post(addObjectEndPoint)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .and()
                .body("name", equalTo("Apple MacBook Pro 16"));

    }

    // update request
    @Test(priority = 3)
    public void updateObject() throws IOException {

        baseURI = getUrl();

        given().contentType(ContentType.JSON)
                .when()
                .body(getPayloadString("update-object.json"))
                .put(updateObjectEndPoint)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .and()
                .body("name", equalTo("Apple MacBook Pro 16"));

    }

    // delete request
    @Test(priority = 4)
    public void deleteObject() throws IOException {

        baseURI = getUrl();

        given().contentType(ContentType.JSON)
                .when()
                .delete(deleteObjectEndPoint)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .and()
                .assertThat().statusLine("HTTP/1.1 200 OK");

    }
}
