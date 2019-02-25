/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import Models.Kwetter;
import Models.KwetterUser;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

public class KwetterRestTest {
    KwetterUser kwetterUser = new KwetterUser("name", null, null, null, "bio", "website", "locatie");
    List<Kwetter> kwettersToDelete = new ArrayList();

    public KwetterRestTest() {
    }

    @Before
    public void setUp() {
        given()
                .contentType(ContentType.JSON)
                .body(kwetterUser)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/UserResource/create");
        KwetterUser[] users = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/Kwetter/webapi/UserResource")
                .as(KwetterUser[].class);
        kwetterUser = users[users.length - 1];
    }

    @Test
    public void testCreate() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/KwetterResource")
                .then()
                .statusCode(200);
        kwettersToDelete.add(kwetter);
    }

    @Test
    public void testKwetterByID() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/KwetterResource");
        kwettersToDelete.add(kwetter);
        given()
                .when()
                .get("http://localhost:8080/Kwetter/webapi/KwetterResource/" + kwetter.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void testTimeline() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/KwetterResource");
        kwettersToDelete.add(kwetter);
        given()
                .contentType(ContentType.JSON)
                .body(kwetterUser)
                .when()
                .get("http://localhost:8080/Kwetter/webapi/KwetterResource/timeline")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllKwettersFromUser() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/KwetterResource");
        kwettersToDelete.add(kwetter);
        given()
                .contentType(ContentType.JSON)
                .body(kwetterUser)
                .when()
                .get("http://localhost:8080/Kwetter/webapi/KwetterResource/all")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetLast10Kwetters() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/KwetterResource");
        kwettersToDelete.add(kwetter);
        given()
                .contentType(ContentType.JSON)
                .body(kwetterUser)
                .when()
                .get("http://localhost:8080/Kwetter/webapi/KwetterResource/last10/" + kwetterUser.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void testSearch() {
        given()
                .when()
                .get("http://localhost:8080/Kwetter/webapi/KwetterResource/search/title")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDelete() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/KwetterResource");
        kwettersToDelete.add(kwetter);
        given()
                .when()
                .delete("http://localhost:8080/Kwetter/webapi/KwetterResource/" + kwetter.getId())
                .then()
                .statusCode(200);
    }

    @After
    public void deleteTestItems() {
        given()
                .when()
                .delete("http://localhost:8080/Kwetter/webapi/UserResource/" + kwetterUser.getId());
        for(Kwetter k : kwettersToDelete) {
            given()
                .when()
                .delete("http://localhost:8080/Kwetter/webapi/KwetterResource/" + k.getId());
        }
    }
}
