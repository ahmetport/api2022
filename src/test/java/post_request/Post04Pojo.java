package post_request;

import Base_Urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerokuappBaseUrl {

     /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */
    //De-Serialization: JSON formatından Java objesine çevirme.
//Serialization: Java objesini JSON formatına çevirme.
// De-Serialization ve Serialization iki türlü yapılır:
//Gson: Goole tarafından üretiliştir.
//Object Mapper: Daha popüler ***

    @Test
    public void PostPojo01 () {
        //set the url
        spec.pathParam("1","booking");

        //set the expecteddata
        BookingDatesPojo bookıngDatas=new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo bookıngPojo=new BookingPojo("Ali","Can",999,true,
                bookıngDatas,"Breakfast with white tea, Dragon juice");

        //send POST request and get the Response
       Response response = given().spec(spec).contentType(ContentType.JSON).body(bookıngPojo).when().post("{1}");

       //Do Assertıon

       BookingResponseBodyPojo actualPojo= response.as(BookingResponseBodyPojo.class);//response pojo data tipine çevirdik
        assertEquals(200,response.getStatusCode());
        assertEquals(bookıngPojo.getFirstname(),actualPojo.getBooking().getFirstname());
        assertEquals(bookıngPojo.getLastname(),actualPojo.getBooking().getLastname());
        assertEquals(bookıngPojo.getTotalprice(),actualPojo.getBooking().getTotalprice());
        assertEquals(bookıngPojo.getDepositpaid(),actualPojo.getBooking().getDepositpaid());


        // BİRİNCİ YOL
        assertEquals(bookıngPojo.getBookingdates().getCheckin(),actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookıngPojo.getBookingdates().getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());
        assertEquals(bookıngPojo.getAdditionalneeds(),actualPojo.getBooking().getAdditionalneeds());
        //İKİNCİ YOL

        assertEquals(bookıngDatas.getCheckin(),actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookıngDatas.getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());
























    }
}

