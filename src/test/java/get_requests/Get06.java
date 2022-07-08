package get_requests;

import Base_Urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerokuappBaseUrl {

        /*
            Given
                https://restful-booker.herokuapp.com/booking/101
            When
                User send a GET request to the URL
            Then
                HTTP Status Code should be 200
            And
                Response content type is "application/json"
            And
                Response body should be like;
              {
                "firstname": "GGS",
                "lastname": "FINCH",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                }

            }
         */
        @Test
        public void get01(){
            //1. Step: Set the Url

            spec.pathParams("first","booking", "second", 101);
            //2. Set the expected data

            //3. Step: Send the request and get the response
            Response response = given().spec(spec).when().get("/{first}/{second}");
            response.prettyPrint();

            //4. Step: Do Assertion
            //1 YOL
            response.
                    then().
                    assertThat().statusCode(200).
                    contentType(ContentType.JSON).
                    body("firstname",equalTo("GGS"),
                            "lastname",equalTo("FINCH"),
                            "totalprice", equalTo(111),
                            "depositpaid", equalTo(true),
                            "bookingdates.checkin",equalTo("2018-01-01"),
                            "bookingdates.checkout",equalTo("2019-01-01"));

            //2 YOL:JsonPath class kullanıllır
            JsonPath jsonPath= response.jsonPath();
            assertEquals("GGS",jsonPath.getString("firstname"));
            assertEquals("FINCH",jsonPath.getString("lastname"));
            assertEquals(111,jsonPath.getInt("totalprice"));
            assertEquals(true,jsonPath.getBoolean("depositpaid"));
            assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
            assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));

            //3 yol:Soft Assertıon
            //1 yol:soft assert objesi oluşturur
            SoftAssert softAssert= new SoftAssert();

            //obje aracılıgı ile assertıon yapacagım
            softAssert.assertEquals(jsonPath.getString("firstname"),"GGS","ALTIN PORTAKAL");
            softAssert.assertEquals(jsonPath.getString("lastname"),"FINCH","GÜLŞAH PORTAKAL");

            //3 ADIM:AssertAll() methodu kullanılır.aksi takdirde kodumuz çalışmaz
            softAssert.assertAll();




















        }





    }

