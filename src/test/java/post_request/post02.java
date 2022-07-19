package post_request;

import Base_Urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.herOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class post02 extends HerokuappBaseUrl {
    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
        "firstname": "John",
                "lastname": "Doe",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
            "checkin": "2021-09-09",
                    "checkout": "2021-09-21"
        }
    }
    When
    I send POST Request to the Url
            Then
    Status code is 200
    And response body should be like {
        "bookingid": 5315,
                "booking": {
            "firstname": "John",
                    "lastname": "Doe",
                    "totalprice": 11111,
                    "depositpaid": true,
                    "bookingdates": {
                "checkin": "2020-09-09",
                        "checkout": "2020-09-21"

     */


    @Test

    public void post01() {
        //1. Step: Set the Url
        spec.pathParam("first", "booking");

        //2. Step: Set the expected Data
        herOkuAppTestData herokuapp = new herOkuAppTestData();
        Map<String, String> bookingdatesMap = herokuapp.bookingdatesSetUp("2021-09-09", "2021-09-21");
        Map<String, Object> expectedDataMap = herokuapp.expectedDataSetUp("John", "Doe", 11111, true, bookingdatesMap);

        //3. Step: Send the Post Request get the Response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion

        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("firstname"), ((Map) actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map) actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map) actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map) actualDataMap.get("booking")).get("depositpaid"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map) actualDataMap.get("booking")).get("depositpaid"));

        assertEquals(bookingdatesMap.get("checkin"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }


}