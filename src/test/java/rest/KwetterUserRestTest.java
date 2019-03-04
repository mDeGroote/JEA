/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import Models.KwetterUser;
import Models.account;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class KwetterUserRestTest {
    List<KwetterUser> usersToDelete = new ArrayList();
 
 
    public KwetterUserRestTest() {
    }
    
    @Test
    public void testCreate() {
        KwetterUser kwetteruser = new KwetterUser("UserRestTest", null, null, null, "bio", "website", "locatie", null);
        kwetteruser = given()
                .contentType(ContentType.JSON)
                .body(kwetteruser)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/create")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(KwetterUser.class);       
        Assert.assertEquals("UserRestTest", kwetteruser.getName());
        usersToDelete.add(kwetteruser);
    }
    
    @Test
    public void testUpdate() {
        KwetterUser kwetteruser = new KwetterUser("UserRestTest", null, null, null, "bio", "website", "locatie", null);
        given()
                .contentType(ContentType.JSON)
                .body(kwetteruser)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/create")
                .then()
                .statusCode(200); 
        kwetteruser.setName("updateTest");
         given()
                .contentType(ContentType.JSON)
                .body(kwetteruser)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/update")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(KwetterUser.class); 
        Assert.assertEquals("updateTest", kwetteruser.getName());
        usersToDelete.add(kwetteruser);
    }
    
    @Test
    public void testGetAllUsers() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/Kwetter/webapi/users")
                .then()
                .statusCode(200);
    }
    
    @Test
    public void testGetKwetterUser() {
        KwetterUser kwetteruser = new KwetterUser("name", null, null, null, "bio", "website", "locatie", null);
        kwetteruser = given()
                .contentType(ContentType.JSON)
                .body(kwetteruser)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/create")
                .then()
                .extract()
                .body()
                .as(KwetterUser.class); 
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/Kwetter/webapi/users/" + kwetteruser.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(KwetterUser.class)
                .getName().equals("name");
        usersToDelete.add(kwetteruser);
    }
    
    @Test
    public void testDelete() {
        KwetterUser kwetteruser = new KwetterUser("name", null, null, null, "bio", "website", "locatie", null);
        kwetteruser = given()
                .contentType(ContentType.JSON)
                .body(kwetteruser)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/create")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(KwetterUser.class); 
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("http://localhost:8080/Kwetter/webapi/users/" + kwetteruser.getId())
                .then()
                .statusCode(200);
    }
    
    //Verwijder eerst de account instanties in de database om deze methode te laten werken
    @Test
    public void testRegister() {
        account account = new account("RegisterTest", "RegisterTest");
        given()
                .contentType(ContentType.JSON)
                .body(account)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/Register")
                .then()
                .statusCode(200);
    }
    
    //Verwijder eerst de account instanties in de database om deze methode te laten werken
    @Test
    public void testLogin() {
        account account = new account("LoginTest", "LoginTest");
        given()
                .contentType(ContentType.JSON)
                .body(account)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/Register")
                .then()
                .statusCode(200);
        given()
                .contentType(ContentType.JSON)
                .body(account)
                .when()
                .post("http://localhost:8080/Kwetter/webapi/users/Login")
                .then()
                .statusCode(200);
    }
    
    @After
    public void deleteUsers() {
        for(KwetterUser ku : usersToDelete) {
            given()
                .contentType(ContentType.JSON)
                .when()
                .delete("http://localhost:8080/Kwetter/webapi/users/" + ku.getId());
        }
    }
}
