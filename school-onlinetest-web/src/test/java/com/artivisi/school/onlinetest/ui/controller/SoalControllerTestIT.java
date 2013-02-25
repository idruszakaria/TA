/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.onlinetest.ui.controller;

import com.artivisi.school.onlinetest.domain.Role;
import com.artivisi.school.onlinetest.domain.Soal;
import com.artivisi.school.onlinetest.domain.Topik;
import com.artivisi.school.onlinetest.domain.User;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import com.jayway.restassured.authentication.FormAuthConfig;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nikko
 */
public class SoalControllerTestIT {
    private String target = "http://localhost:10000/master/soal";
    private String login = "http://localhost:10000/j_spring_security_check";
    private String username = "endy";
    private String password = "123";

    @Test
    public void testSaveInvalid(){
        User u = new User();
        
        given()
            .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
            .contentType("application/json")
            .body(u)
            .expect().statusCode(400).when().post(target);
    }

    @Test
    public void testSaveUpdateDelete() {

        Topik r = new Topik();
        r.setId("1");

        Soal soal = new Soal();
        soal.setTopik(r);
        soal.setJumlahPertanyaan(2);
        soal.setKode("2");
        soal.setNama("Ekosistem Alam Purba");
        soal.setNilaiLulusMinimum(100);
        soal.setTingkatKesulitan(10);

        String id = testSave(target, soal);
        System.out.println("Id : " + id);
        testGetExistingById(id, soal);
        
        soal.setNama("Ekosistem Alam Purba");
        soal.setId("2");
        
        testUpdateExisting(id, soal);
        testGetExistingById(id, soal);
        testDeleteExistingById(id);
    }

    private String testSave(String target, Soal x) {
        String location = given()
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .contentType("application/json")
                .body(x)
                .expect().statusCode(201).when().post(target)
                .getHeader("Location");

        assertNotNull(location);
        assertTrue(location.startsWith(target));

        String[] locationSplit = location.split("/");
        String id = locationSplit[locationSplit.length - 1];

        return id;
    }

    private void testGetExistingById(String id, Soal x) {
        with().header("Accept", "application/json")
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect()
                .statusCode(200)
                .body("nama", equalTo(x.getNama()), "id", equalTo(x.getId()))
                .when().get(target + "/" + id);
    }

    private void testUpdateExisting(String id, Soal x) {

        given()
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .contentType("application/json")
                .body(x)
                .expect()
                .statusCode(200).when().put(target + "/" + id);
    }

    private void testDeleteExistingById(String id) {
        given().auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect().statusCode(200).when().delete(target + "/" + id);

        given().auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect().statusCode(404).when().get(target + "/" + id);
    }

    @Test
    public void testGetExistingById() {
        with().header("Accept", "application/json")
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect()
                .statusCode(200)
                .body("id", equalTo("1"),
                "nama", equalTo("Ekosistem"),
                "kode", equalTo("1")).when()
                .get(target + "/" + "1");
    }

    @Test
    public void testGetNonExistentById() {
        with()
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .expect().statusCode(404).when().get(target + "/" + "/blable");
    }

    @Test
    public void testFindAll() {
        with()
                .auth().form(username, password, new FormAuthConfig(login, "j_username", "j_password"))
                .header("Accept", "application/json").expect().statusCode(200)
                .body("id", hasItems("1")).when().get(target);
    }
}
