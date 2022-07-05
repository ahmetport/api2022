import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

   /*
POSTMAN, manuel olarak API testi yapmak için kullanılır.
API otomasyonda ise REST Assured Library kullanacağız
REST API kullanmamızın sebebi hem Json hem de Xml olarak kullanılabiliyor olmasıdır.
Otomasyon kodlarının yazımı için şu adımları izliyoruz;

a) -->  Gereksinimleri anlama
b) -->  Test case'leri yazma
_______ Test case yazımı için Gherkin plugin kullanılacaktır.
_______ Given, Then, And, But gibi keywodler kullanılacaktır.
_______ Given (ön koşullar)
________ When (aksiyonlar)
________ Then (dönütler, doğrulama, response gibi)
________ And (çoklu işlemeler için)

c) -->  Otomasyonda test kodunun yazımı yapılacak
        1) Set the URL (URl'yi kurmak)
        2) Set the expected Data (beklenen datanın oluşturulması)   """POST, PUT PATCH"""
        3) Type code to send request (talep göndermek için kod yazma)
        4) Do Assertion (doğrulama yapma)
 */

    /*

    Given
            https://restful-booker.herokuapp.com/booking/27
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */
    @Test
    public void get01(){
       // 1) Set the URL (URl'yi kurmak)
        String url="https://restful-booker.herokuapp.com/booking/27";
        //2) Set the expected Data (beklenen datanın oluşturulması)   """POST, PUT PATCH"""
        // 3) Type code to send request (talep göndermek için kod yazma)
       Response response= given().when().get(url);
       response.prettyPrint();
       // System.out.println(response);
       // 4) Do Assertion (doğrulama yapma)


        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //status code nasıl yazdırılır
        System.out.println(response.statusCode());
        System.out.println(response.contentType());
        System.out.println(response.statusLine());



        //header nasıl yazdırılır
        System.out.println(response.header("Connection"));//keep-alive
        /*
        //"time" nasıl yazdırılır
        System.out.println(response.getTime());
        //headers yazdırma
        System.out.println("Headers: \n"+ response.prettyPrint());

        System.out.println("agent:" + response.header("User-Agent"));//

         */





    }



}
