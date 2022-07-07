package get_requests;

import Base_Urls.JsonplaceholderBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends JsonplaceholderBaseUrls {



    /*
    Given
        https://jsonplaceholder.typicode.com/todos/23
    When
        User send GET Request to the URL
    Then
        HTTP Status Code should be 200
And
  Response format should be "application/json"
And
  "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
And
  "completed" is false
And
  "userId" is 2
 */

    @Test
    public void test(){

        spec.pathParams("first","todos","second",23);

        //step:send the request and get the Response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //step Do assertıon
        /*
        response.
                then().
                assertThat().
                statusCode(200).contentType("application/json").
                body("completed", equalTo(false)).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("userId",equalTo(2));

         */

             response.
                then().
                assertThat().
                statusCode(200).contentType(ContentType.JSON).body("title",
                             equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                 "completed",equalTo(false),"userId",equalTo(2));



                 /*  not 1: Assertion yaparken Java calismayi durdurdugunda hata sonra kodlar calismaz.
            boylece hata sonrasi assertion lar hakkinda bilgi sahibi olanayiz
            fakat hata oncesi assertion lar gecmistir

     Not 2: Eğer kodumuzu hata noktasında duracak şekilde yazarsak "Hard Assertion" yapmış oluyoruz.

     Not 3: Eğer kodumuzu hata noktasında duramayacak şekilde yazarsak "soft Assertion" yapmış oluyoruz.

     Not 4: Eğer çoklu body() methodu içinde assert yaparsak "Hard Assert",
            tek body() methodu içinde assert yaparsak "Soft Assert"  yapmış oluyoruz.

         */
    }
}













