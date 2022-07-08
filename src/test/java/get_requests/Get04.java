package get_requests;

import Base_Urls.JsonplaceholderBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonplaceholderBaseUrls {



    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    I send a GET request to the Url
	    And
	        Accept type is “application/json”
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     */

    @Test
    public void get01() {
     //1 step url set the url
     spec.pathParam("first","todos");
     //2 step set the expected data
     //3 step: set the reguest and get response
     Response response= given().spec(spec).accept(ContentType.JSON).when().get("/{first}");//soruda when ve then arkaarkaya geldigi için
     response.prettyPrint();


     //4 STEP Do assertıons
        response.then().
                assertThat().statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200)
                        ,"title",hasItem("quis eius est sint explicabo"),"userId",hasItems(2,7,9));







    }
}