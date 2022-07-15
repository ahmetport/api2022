package get_requests;

import Base_Urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
    https://gorest.co.in/public/v1/users
    When
    User send GET Request
    Then
    The value of "pagination limit" is 10
    And
    The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
    The number of users should  be 10
    And
    We have at least one "active" status
            And
            "Indra Ganaka", "Sarada Mehrotra", "Jagathi Chopra" are among the users
    And
    The female users are more than male users

     */

    @Test
    public void get01() {
        //set the url
        spec.pathParam("first","users");
        //set the expecteddata(atladık)
        //send the request get the response
       Response response=given().spec(spec).when().get("/{first}");
       response.prettyPrint();

       //Do assertıons
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current",
                        equalTo("https://gorest.co.in/public/v1/users?page=1"),"data.id",hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Aalok Acharya DDS", "Acharyasuta Chattopadhyay DC", "Shresth Nehru"));


        //Bayan ve erkek sayısını karşılaştıralım
        //1. Yol: Tüm cinisyetli çekip bayan sayısı ile karşılaştıralım.
        JsonPath json = response.jsonPath();
        List<String> genders = json.getList("data.gender");
        System.out.println(genders);

        int numOfFemales=0;
        for(String w: genders){
            if(w.equalsIgnoreCase("female")){
                numOfFemales++;
            }
        }
        System.out.println(numOfFemales);//6
        assertTrue(numOfFemales > genders.size()-numOfFemales);

        //2. Yol:  Tüm bayan ve bayları ayrı ayrı Groovy ile çekelim.
        List<String> femaleList = json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String> maleList = json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size()>maleList.size());

    }
}
























