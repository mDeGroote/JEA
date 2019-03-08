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
    KwetterUser kwetterUser = new KwetterUser("name", null, null, null, "bio", "website", "locatie", null);
    List<Kwetter> kwettersToDelete = new ArrayList();

    public KwetterRestTest() {
    }

    @Before
    public void setUp() {
        kwetterUser = given()
                .contentType(ContentType.JSON)
                .body(kwetterUser)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/create")
                .then()
                .extract()
                .body()
                .as(KwetterUser.class); 
    }

    @Test
    public void testCreate() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/kwetters")
                .then()
                .statusCode(200);
        kwettersToDelete.add(kwetter);
    }

    @Test
    public void testKwetterByID() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        kwetter = given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/kwetters")
                .then()
                .extract()
                .body()
                .as(Kwetter.class);
        kwettersToDelete.add(kwetter);
        given()
                .when()
                .get("http://localhost:8080/Kwetter/webapi/kwetters/" + kwetter.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void testSearch() {
        given()
                .when()
                .get("http://localhost:8080/Kwetter/webapi/kwetters/search/title")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDelete() {
        Kwetter kwetter = new Kwetter("title", "content", kwetterUser);
        kwetter = given()
                .contentType(ContentType.JSON)
                .body(kwetter)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/kwetters")
                .then()
                .extract()
                .body()
                .as(Kwetter.class);
        given()
                .when()
                .delete("http://localhost:8080/Kwetter/webapi/kwetters/" + kwetter.getId())
                .then()
                .statusCode(200);
    }

    @After
    public void deleteTestItems() {
        given()
                .when()
                .delete("http://localhost:8080/Kwetter/webapi/users/" + kwetterUser.getId());
        for(Kwetter k : kwettersToDelete) {
            given()
                .when()
                .delete("http://localhost:8080/Kwetter/webapi/kwetters/" + k.getId());
        }
    }
}
