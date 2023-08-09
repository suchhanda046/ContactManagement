package contacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.Contacts.AddContactPayload;
import org.example.Users.AddNewUser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class AddContactUsingPOJO {
    public String token;


    @BeforeClass
    public void getToken() {
        token = AddNewUser.addNewUser();
    }

    @Test
    public void verifyAddNewContacts() throws JsonProcessingException {
        AddContactPayload payload = new AddContactPayload();

        baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        basePath = "/contacts";
        payload.setBirthdate("1970-01-01");
        payload.setCity("Bangalore");
        payload.setCountry("India");
        payload.setEmail("abc@fake.com");
        payload.setPhone("1234567890");
        payload.setFirstName("TestFirstname");
        payload.setLastName("TestLastname");
        payload.setPostalCode("12345");
        payload.setStreet1("3rd cross");
        payload.setStreet2("4th main");
        payload.setStateProvince("KA");
        ObjectMapper mapper = new ObjectMapper();
        String payloadString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
        System.out.println(payloadString);

        RequestSpecification request = given();
        request.header("Authorization", "Bearer " + token);
        request.accept(ContentType.JSON).contentType("application/json").body(payloadString);

        Response res = request.post();
        int statuscode = res.then().extract().response().statusCode();
        System.out.println(statuscode);
        System.out.println(res.then().extract().response().getBody().toString());
        System.out.println(res.then().extract().body().asPrettyString());
    }

}
