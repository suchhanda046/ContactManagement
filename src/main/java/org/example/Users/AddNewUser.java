package org.example.Users;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class AddNewUser {
    public static String addNewUser(){
        baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        basePath = "/users";
        String body = "{\n" +
                "    \"firstName\": \"Test12\",\n" +
                "    \"lastName\": \"User12\",\n" +
                "    \"email\": \"surpriseTest12@fake.com\",\n" +
                "    \"password\": \"myPassword\"\n" +
                "}";
        RequestSpecification request = given();
        request.contentType("application/json").body(body);
        Response res = request.post();
        int statuscode = res.then().extract().statusCode();
        System.out.println(statuscode);
        System.out.println(res.then().extract().response().body().asPrettyString());
        String token = res.then().extract().body().jsonPath().get("token").toString();
        System.out.println("Token :"+token);
    return token.trim();
    }

}
