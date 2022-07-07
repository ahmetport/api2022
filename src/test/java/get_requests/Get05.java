package get_requests;

import Base_Urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerokuappBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Aaron" and last name is "Chen"
     */

    @Test
    public void get01() {
        //1 step set the Url
        spec.pathParam("first","booking").queryParams("firstname","Aaron","lastname","Chen");
        //postmanda params kısmında oluşturduk artık url bu oldu
        //https://restful-booker.herokuapp.com/booking?firstname=Aaron&lastname&=Chen

        //3.adım: send the request and get the response
        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4 adım Do assertıon
        response.then().assertThat().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));


    }
}
