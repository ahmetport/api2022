import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends BaseUrls{



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

        //step Do assertıon
        response.then().assertThat().statusCode(200).contentType("application/json").
        body("completed", equalTo(false)).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                 body("userId",equalTo(2));
    }
}
