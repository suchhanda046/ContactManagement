package contacts;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.Users.AddNewUser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContacts {
    public String token;

    @BeforeClass
    public void getToken() {
        token = AddNewUser.addNewUser();
    }

    @Test
    public void verifyAddNewContacts() {
        System.out.println("test.....");
        baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        basePath = "/contacts";
        String body = "{\n" +
                "    \"firstName\": \"Test9\",\n" +
                "    \"lastName\": \"User9\",\n" +
                "    \"birthdate\": \"1970-01-01\",\n" +
                "    \"email\": \"surpriseTest9@fake.com\",\n" +
                "    \"phone\": \"8005555555\",\n" +
                "    \"street1\": \"1 Main St.\",\n" +
                "    \"street2\": \"Apartment A\",\n" +
                "    \"city\": \"Anytown\",\n" +
                "    \"stateProvince\": \"KS\",\n" +
                "    \"postalCode\": \"12345\",\n" +
                "    \"country\": \"USA\"\n" +
                "}";

        RequestSpecification request = given();
        request.header("Authorization", "Bearer " + token);
        request.accept(ContentType.JSON).contentType("application/json").body(body);
        Response res = request.post();
        int statuscode = res.then().extract().response().statusCode();
        System.out.println(statuscode);
        System.out.println(res.then().extract().response().getBody().toString());
        System.out.println(res.then().extract().body().asPrettyString());
    }

}

